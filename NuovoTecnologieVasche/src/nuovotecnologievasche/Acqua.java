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
public class Acqua {

    private datiCondivisi ptrDati;
    private PApplet processingSketch;

    //private int rosso = 15;
    //private int verde = 125;
    //private int blu = 255;
    private int lunghezza;
    private int larghezza;

    private int spostamentoX;
    private int spostamentoY;

    private Point pos;

    private int lVasca;

//    public Acqua(datiCondivisi ptrDati,PApplet process, int lunghezza,
//            int larghezza, Point pos) {
//        this.ptrDati = ptrDati;
//        this.processingSketch = process;
//        this.lunghezza = lunghezza;
//        this.larghezza = larghezza;
//        this.pos = pos;
//        spostamentoX = pos.x;
//        spostamentoY = pos.y;
//    }
    public int getLunghezza() {
        return lunghezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public Acqua(datiCondivisi ptrDati, PApplet process, int lunghezza,
            int larghezza, Point pos, int lVasca) {
        this.ptrDati = ptrDati;
        this.processingSketch = process;
        this.lunghezza = lunghezza;
        this.larghezza = larghezza;

        this.pos = pos;

        spostamentoX = pos.x + lVasca;
        spostamentoY = pos.y;

        this.lVasca = lVasca;

    }

    public Acqua(int lVasca, datiCondivisi ptrDati, PApplet process, int lunghezza,
            int larghezza, Point pos) {
        this.ptrDati = ptrDati;
        this.processingSketch = process;
        this.lunghezza = lunghezza;
        this.larghezza = larghezza;

        this.pos = pos;
        spostamentoX = pos.x;
        spostamentoY = pos.y;

        this.lVasca = lVasca;

    }

    public void appiattisci()
    {       
        lunghezza = lVasca;
        spostamentoX = pos.x;
    }
    public int getSpostamentoX() {
        return spostamentoX;
    }

    public Point getPos() {
        return pos;
    }

    public void spostaDx() {
        int temp = (pos.x+lVasca)-lunghezza;
        spostamentoX+= temp;
    }

    public void spostaSx() {
        spostamentoX = pos.x;
    }

    public void spawnDx(int lVasca) {

        int spostamentoTemp = ptrDati.getSpostamentoX();

        if (spostamentoX == pos.x) {
            spostamentoX = pos.x + lVasca;
            spostamentoX -= spostamentoTemp;
            lunghezza += spostamentoTemp;
        } else {
            spostamentoX -= spostamentoTemp;
            lunghezza += spostamentoTemp;
        }

        if ((pos.x + lunghezza - spostamentoTemp) < pos.x) {
            lunghezza = lVasca;
        }

    }

    public void spawnSx(int lVasca) {
        int spostamentoTemp = ptrDati.getSpostamentoX();
        if (spostamentoX == pos.x + lVasca) {

            spostamentoX = pos.x;
            lunghezza += spostamentoTemp;
        } else {
            lunghezza += spostamentoTemp;
        }

        if (pos.x + lunghezza > pos.x + lVasca) {
            lunghezza = lVasca;
        }

    }

    public void inclinaSx() {
        int spostamentoTemp = ptrDati.getSpostamentoX();

        if (ptrDati.getInclinazioneX() > 0) {
            spostamentoX -= spostamentoTemp;
            lunghezza += spostamentoTemp;
        } else if ((pos.x + lunghezza - spostamentoTemp) < pos.x) {
            lunghezza = 0;
        } else {
            lunghezza -= spostamentoTemp;
        }
    }

    public void inclinaDx() {
        int spostamentoTemp = ptrDati.getSpostamentoX();

        if (ptrDati.getInclinazioneX() < 0) {
            lunghezza += spostamentoTemp;
        } else if ((spostamentoX + spostamentoTemp) >= (pos.x + ptrDati.getLarghezzaVasca())) {
            spostamentoX = pos.x + ptrDati.getLarghezzaVasca();
            lunghezza = 0;
        } else {
            spostamentoX += spostamentoTemp;
            lunghezza -= spostamentoTemp;
        }

    }

    public void draw() {
        processingSketch.fill(15, 125, 255);
        processingSketch.noStroke();

        processingSketch.rect(spostamentoX, spostamentoY, lunghezza, larghezza);
    }
}
