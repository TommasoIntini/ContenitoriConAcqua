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
    private thAcqua ac = null;

    private Point posDraw = new Point(150, 75);

    private int incX;//<0 inclinato sx
    private int incY;//<0 inclinato up

    private int l;
    private int la;

    private static final int spostamentoAcqua = 2;

    public datiCondivisi(int lunghezza, int larghezza, int nVasche, PApplet processingSketch) {

        ac = new thAcqua(this);
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

        ac.start();
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

    public synchronized void hoInclinato(char direzione)//per semplificare si muove solo l'acqua, il contenitore sta fermo ma è come se si muovesse
    {
        switch (direzione) {
            case 'a':
            case 'A':
                if (incX >= -4) {
                    incX--;
                }
                break;
            case 'd':
            case 'D':
                if (incX <= 4) {
                    incX++;
                }
                break;
            case 'w':
            case 'W':
                if (incY >= -4) {
                    incY--;
                }
                break;
            case 's':
            case 'S':
                if (incY <= 4) {
                    incY++;
                }
                break;
            default:
                break;
        }

    }

//    public void setPienaPrimaVascaOrizzontaleConAcqua() {
//        for (int i = 0; i < vasche.size(); i++) {
//            if (vasche.elementAt(i).acquaPresente()) {
//                vasche.elementAt(i).setAcqua(true);
//            } else {
//                vasche.elementAt(i).setAcqua(false);
//            }
//        }
//    }
    public int getIndexPrimaPiena() {
        int temp = 0;
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).getStatoAcqua()) {
                temp = i;
            }
        }
        return temp;
    }

    public boolean stavoInclinandoASinistra() {
        int index = getIndexPrimaPiena();
        if (vasche.elementAt(index).getSpostamentoAcquaX() == vasche.elementAt(index).getPosAcqua().x) {
            return true;
        } else {
            return false;
        }

    }

    public boolean stavoInclinandoADestra() {
        int index = getIndexPrimaPiena();
        if (vasche.elementAt(index).getSpostamentoAcquaX() >= vasche.elementAt(index).getPosAcqua().x) {
            return true;
        } else {
            return false;
        }

    }

    public synchronized void inclinazioneX() {
        try {
            if (incX > 0)//inclina a dx
            {
                if (stavoInclinandoADestra()) {
                    for (int i = 0; i < vasche.size(); i++) {
                        if (vasche.elementAt(i).getStatoAcqua()) {
                            vasche.elementAt(i).inclinaDx();

                            vasche.elementAt(i + 1).spawnDx();

                            if (vasche.elementAt(i + 1).vascaPiena()) { //se la vasca successiva è piena 
                                vasche.elementAt(i).setAcqua(false);
                                vasche.elementAt(i + 1).setAcqua(true);
                            }

                        }

                        barca.spostaDx();
                    }
//                } else {
//                    for (int i = 0; i < vasche.size(); i++) {
//                        if (vasche.elementAt(i).acquaPresente()) {
//                            vasche.elementAt(i).spostaAcquaSullaDestra();
//                        }
//
//                        int index = getIndexPrimaPiena();
//                        vasche.elementAt(index).setAcqua(true);
//
//                        while (vasche.elementAt(index).acquaPresente()) {
//                            vasche.elementAt(index).inclinaDx();
//                            Thread.sleep(100);
//                        }
//                        vasche.elementAt(index).setAcqua(false);
//                        vasche.elementAt(index + 1).setAcqua(true);
//                    }
//
//                    vasche.elementAt(getIndexPrimaPiena()).setAcqua(true);
//                }

                } else if (incX < 0) {

                    if (stavoInclinandoASinistra()) {
                        for (int i = 0; i < vasche.size(); i++) {
                            if (vasche.elementAt(i).getStatoAcqua()) {
                                vasche.elementAt(i).inclinaSx();

                                vasche.elementAt(i - 1).spawnSx();
                                //vasche.elementAt(i+1).spawnSx();
                                if (vasche.elementAt(i - 1).vascaPiena()) { //se la vasca successiva è piena 
                                    vasche.elementAt(i).setAcqua(false);
                                    vasche.elementAt(i - 1).setAcqua(true);
                                }

                            }
                            barca.spostaSx();
                        }
//                } else {
//                    for (int i = 0; i < vasche.size(); i++) {
//                        if (vasche.elementAt(i).acquaPresente()) {
//                            vasche.elementAt(i).spostaAcquaSullaSinistra();
//                        }
//                    }
//                    int index = getIndexPrimaPiena() + 1;
//                    vasche.elementAt(index).setAcqua(true);
//                    vasche.elementAt(index - 1).setAcqua(false);
//
//                    while (vasche.elementAt(index).acquaPresente()) {
//                        vasche.elementAt(index).inclinaSx();
//                        Thread.sleep(100);
//                    }
//                    vasche.elementAt(index).setAcqua(false);
//                    vasche.elementAt(index - 1).setAcqua(true);
//                }

                    }
                }
            }
        } catch (Exception e) {

        }

    }

    public int getSpostamentoX() {
        if (incX < 0) {
            return incX * -1;
        } else {
            return incX;
        }

    }

    public boolean nessunaPiena() {
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).vascaPiena()) {
                return false;
            }
        }
        return true;
    }

    public void drawTutto() {
        for (int i = 0; i < vasche.size(); i++) {
            vasche.elementAt(i).draw();
        }
        barca.draw();

    }

    public int getSizeVasche() {
        return vasche.size();
    }

}
