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
    private Acqua acqua = null;
    private Barca barca = null;

    private PApplet processingSketch = null;
    private thInput in = null;

    private Point posDraw = new Point(150, 75);

    private int incX;//<0 inclinato sx
    private int incY;//<0 inclinato up

    private int l;
    private int la;
    
    private static final int spostamentoAcqua = 20;

    public datiCondivisi(int lunghezza, int larghezza, PApplet processingSketch) {
        
        l=lunghezza;
        la=larghezza;

        vasca1 = new Vasca(this, processingSketch, l, la, posDraw);
        
        Point posiz = new Point(posDraw.x+l,posDraw.y);
        
        vasca2 = new Vasca(this, processingSketch, l, la, posiz);

        acqua = new Acqua(this, processingSketch, l, la, posDraw);

        Point posBarca = new Point(posDraw.x + (l / 2), posDraw.y + (la / 2));

        barca = new Barca(this, processingSketch, posBarca);

        incX = 0;
        incY = 0;
        in = new thInput(this);

        in.start();
    }
    
    public int getSpostamentoAcqua()
    {
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
                acqua.inclinaSx();
                barca.spostaSx();
                break;
            case 'd':
            case 'D':
                acqua.inclinaDx();
                barca.spostaDx();
                break;
            case 'w':
            case 'W':
                acqua.inclinaUp();
                barca.spostaUp();
                break;
            case 's':
            case 'S':
                acqua.inclinaDw();
                barca.spostaDw();
                break;
            default:
                break;
        }

    }

    public Acqua getAcqua() {
        return acqua;
    }

    public void drawTutto() {
        acqua.draw();
        vasca1.draw();
        vasca2.draw();
        barca.draw();

    }
}
