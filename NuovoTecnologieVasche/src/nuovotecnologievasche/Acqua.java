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
        spostamentoX = pos.x;
        spostamentoY = pos.y;

        pos.x -= lunghezza;

    }

    public Acqua(datiCondivisi ptrDati, PApplet process, int lunghezza,
            int larghezza, Point pos) {
        this.ptrDati = ptrDati;
        this.processingSketch = process;
        this.lunghezza = lunghezza;
        this.larghezza = larghezza;

        this.pos = pos;
        spostamentoX = pos.x;
        spostamentoY = pos.y;

    }

    public int getSpostamentoX() {
        if (ptrDati.getInclinazioneX() < 0) {
            return ptrDati.getInclinazioneX() * -1;
        } else {
            return ptrDati.getInclinazioneX();
        }

    }

    public void spawnDx(int lVasca) {

        int spostamentoTemp = getSpostamentoX();

        spostamentoX -= spostamentoTemp;
        lunghezza += spostamentoTemp;

        if ((pos.x + lunghezza - spostamentoTemp) < pos.x) {
            lunghezza = lVasca;
        }

    }

    public void spawnSx(int lVasca) {

        int spostamentoTemp = getSpostamentoX();

        lunghezza += spostamentoTemp;

    }


    public void inclinaSx() {
        int spostamentoTemp = getSpostamentoX();

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
        int spostamentoTemp = 0;
        if (ptrDati.getInclinazioneX() < 0) {
            spostamentoTemp = ptrDati.getInclinazioneX() * -1;
        } else if (ptrDati.getInclinazioneX() > 0) {
            spostamentoTemp = ptrDati.getInclinazioneX();
        }

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

    public void inclinaUp() {

        int spostamentoTemp = 0;
        if (ptrDati.getInclinazioneY() < 0) {
            spostamentoTemp = ptrDati.getInclinazioneY() * -1;
        } else if (ptrDati.getInclinazioneY() > 0) {
            spostamentoTemp = ptrDati.getInclinazioneY();
        }

        if (ptrDati.getInclinazioneY() > 0) {
            spostamentoY -= spostamentoTemp;
            larghezza += spostamentoTemp;
        } else if ((pos.y + larghezza - spostamentoTemp) < pos.y) {
            larghezza = 0;
        } else {
            larghezza -= spostamentoTemp;
        }
    }

    public void inclinaDw() {
        int spostamentoTemp = 0;
        if (ptrDati.getInclinazioneY() < 0) {
            spostamentoTemp = ptrDati.getInclinazioneY() * -1;
        } else if (ptrDati.getInclinazioneY() > 0) {
            spostamentoTemp = ptrDati.getInclinazioneY();
        }

        if (ptrDati.getInclinazioneY() < 0) {
            larghezza += spostamentoTemp;
        } else if ((spostamentoY + spostamentoTemp) > (pos.y + ptrDati.getAltezzaVasca())) {
            spostamentoY = pos.y + ptrDati.getAltezzaVasca();
            larghezza = 0;
        } else {
            spostamentoY += spostamentoTemp;
            larghezza -= spostamentoTemp;
        }
    }

    public void draw() {
        processingSketch.fill(15, 125, 255);
        processingSketch.noStroke();

        processingSketch.rect(spostamentoX, spostamentoY, lunghezza, larghezza);
    }
}
