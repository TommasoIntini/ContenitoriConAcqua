/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovotecnologievasche;

import java.awt.Point;
import java.util.Vector;
import processing.core.PApplet;

/**
 *
 * @author Tommaso
 */
public class datiCondivisi {

    private Vasca vasca1, vasca2 = null;
    private Vector<Vasca> vasche;
    private Barca barca = null;

    private PApplet processingSketch = null;
    private thInput in = null;

    private Point posDraw = new Point(150, 75);

    private int incX;//<0 inclinato sx
    private int incY;//<0 inclinato up

    private int l;
    private int la;

    private static final int spostamentoAcqua = 20;

    public datiCondivisi(int lunghezza, int larghezza, int nVasche, PApplet processingSketch) {

        int x = posDraw.x;
        int y = posDraw.y;

        vasche = new Vector<Vasca>();
        l = lunghezza;
        la = larghezza;

        for (int i = 0; i < nVasche; i++) {
            vasche.add(new Vasca(this, processingSketch, l, la, new Point(x, y), i));
            x += l;
        }

        Point posiz = new Point(posDraw.x + l, posDraw.y);

        Point posBarca = new Point(posDraw.x + (l / 2), posDraw.y + (la / 2));

        barca = new Barca(this, processingSketch, posBarca);

        incX = 0;
        incY = 0;
        in = new thInput(this);

        in.start();
    }

    public int getSpostamentoAcqua() {
        return spostamentoAcqua;
    }

    public int getLarghezzaVasca() {
        return l;
    }

    public void incIncX() {
        incX++;
    }

    public void incIncY() {
        incY++;
    }

    public void decIncX() {
        incX--;
    }

    public void decIncY() {
        incY--;
    }

    public int getAltezzaVasca() {
        return la;
    }

    public Point getPosDraw() {
        return posDraw;
    }

    public int getInclinazioneX() {
        return incX;
    }

    public int getInclinazioneY() {
        return incY;
    }

    public void hoInclinato(char direzione)//per semplificare si muove solo l'acqua, il contenitore sta fermo ma è come se si muovesse
    {
        switch (direzione) {
            case 'a':
            case 'A':
                incX--;
                for (int i = 0; i < vasche.size(); i++) {
                    vasche.elementAt(i).inclinaSx();
                }

                barca.spostaSx();
                break;
            case 'd':
            case 'D':
                incX++;
                for (int i = 0; i < vasche.size(); i++) {
                    vasche.elementAt(i).inclinaDx();
                }
                barca.spostaDx();
                break;
            case 'w':
            case 'W':
                incY--;
                for (int i = 0; i < vasche.size(); i++) {
                    vasche.elementAt(i).inclinaUp();
                }
                barca.spostaUp();
                break;
            case 's':
            case 'S':
                incX++;
                for (int i = 0; i < vasche.size(); i++) {
                    vasche.elementAt(i).inclinaDw();
                }
                barca.spostaDw();
                break;
            default:
                break;
        }

    }


    public void drawTutto() {
        for (int i = 0; i < vasche.size(); i++) {
            vasche.elementAt(i).draw();
        }
        barca.draw();

    }
}
