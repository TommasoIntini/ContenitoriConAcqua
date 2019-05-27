/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovotecnologievasche;

import java.awt.Point;
import processing.core.PApplet;

/**
 *
 * @author Tommaso
 */
public class Vasca {

    private boolean vascaPiena;

    private int h = 15;    //dimesnioni
    private int lunghezza = 0;
    private int larghezza = 0;

    private Acqua acqua;

    private PApplet processingSketch;
    private datiCondivisi ptrDati;

    private Point posi;

    private int elementoCorrente = 0;

    public Vasca()//dato che le vasche sono collegate tra di loro ne utilizzo una per rappresentarle tutte
    {

    }

    public Vasca(datiCondivisi ptrDati, PApplet process,
            int lunghezza, int larghezza, Point pos, int nVasca) {
        this.lunghezza = lunghezza;
        this.larghezza = larghezza;

        this.ptrDati = ptrDati;
        this.processingSketch = process;

        this.posi = pos;

        if (nVasca == 0) {                                              //se
            this.acqua = new Acqua(ptrDati, process, lunghezza, larghezza, pos);
            vascaPiena = true;
        } else {
            int x = pos.x;
            this.acqua = new Acqua(ptrDati, process, 0, larghezza, new Point(x + lunghezza, pos.y), lunghezza);
        }

    }

    public void inclinaSx() {
        acqua.inclinaSx();
    }

    public void spawnDx() {
        acqua.spawnDx(lunghezza);
    }

    public void spawnSx() {
        acqua.spawnSx(lunghezza);
    }
//
//    public void spawnDw() {
//        acqua.spawnDw(larghezza);
//    }
//
//    public void spawnUp() {
//        acqua.spawnUp(larghezza);
//    }

    public void inclinaDx() {
        acqua.inclinaDx();
    }

    public void inclinaUp() {
        acqua.inclinaUp();

    }

    public void inclinaDw() {
        acqua.inclinaDw();
    }

    public void setAcqua(boolean ris) {
        this.vascaPiena = ris;
    }

    public boolean acquaPresente() {
        if (acqua.getLunghezza() != 0 && acqua.getLarghezza() != 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean vascaPiena() {
        if (acqua.getLunghezza() == lunghezza && acqua.getLarghezza() == larghezza) {
            return true;
        }
        return false;
    }

    public boolean getStatoAcqua() {
        return vascaPiena;
    }

    public void draw() {

        acqua.draw();

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posi.x, posi.y, lunghezza, larghezza);

    }

}
