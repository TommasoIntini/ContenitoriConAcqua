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

    private int rosso = 15;
    private int verde = 125;
    private int blu = 255;
    private int lunghezza;
    private int larghezza;

    private int spostamentoX;
    private int spostamentoY;

    private Point pos;

    public Acqua() {

    }

    public boolean fuoriTutta() {
        if (rosso == 255 && verde == 255) {
            return true;
        } else if (lunghezza == 0 || larghezza == 0) {
            return true;
        } else {
            return false;
        }
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

    public void inclinaSx() {
        if (ptrDati.getInclinazioneX() > 0) {
            spostamentoX -= ptrDati.getSpostamentoAcqua();
            lunghezza += ptrDati.getSpostamentoAcqua();
            ptrDati.decIncX();
        } else if ((pos.x + lunghezza - ptrDati.getSpostamentoAcqua()) < pos.x) {
            lunghezza = 0;
        } else {
            cambiaProfondita();
            lunghezza -= ptrDati.getSpostamentoAcqua();
            ptrDati.decIncX();
        }
    }

    public void cambiaProfondita() {
        if (rosso < 255) {
            rosso += 10;

        }

        if (verde < 255) {
            verde += 10;
        }
    }

    public void inclinaDx() {
        if (ptrDati.getInclinazioneX() < 0) {
            lunghezza += ptrDati.getSpostamentoAcqua();
            ptrDati.incIncX();
        } else if ((spostamentoX + ptrDati.getSpostamentoAcqua()) >= (pos.x + ptrDati.getLarghezzaVasca())) {
            spostamentoX = pos.x + ptrDati.getLarghezzaVasca();
            lunghezza = 0;
        } else {
            cambiaProfondita();
            spostamentoX += ptrDati.getSpostamentoAcqua();
            lunghezza -= ptrDati.getSpostamentoAcqua();
            ptrDati.incIncX();
        }

    }

    public void inclinaUp() {
        if (ptrDati.getInclinazioneY() > 0) {
            spostamentoY -= ptrDati.getSpostamentoAcqua();
            larghezza += ptrDati.getSpostamentoAcqua();
            ptrDati.decIncY();
        } else if ((pos.y + larghezza - ptrDati.getSpostamentoAcqua()) < pos.y) {
            larghezza = 0;
        } else {
            cambiaProfondita();
            larghezza -= ptrDati.getSpostamentoAcqua();
            ptrDati.decIncY();
        }
    }

    public void inclinaDw() {
        if (ptrDati.getInclinazioneY() < 0) {
            larghezza += ptrDati.getSpostamentoAcqua();
            ptrDati.incIncY();
        } else if ((spostamentoY + ptrDati.getSpostamentoAcqua()) > (pos.y + ptrDati.getAltezzaVasca())) {
            spostamentoY = pos.y + ptrDati.getAltezzaVasca();
            larghezza = 0;
        } else {
            cambiaProfondita();
            ptrDati.incIncY();
            spostamentoY += ptrDati.getSpostamentoAcqua();
            larghezza -= ptrDati.getSpostamentoAcqua();
        }
    }

    public void draw() {
        processingSketch.fill(rosso, verde, blu);
        processingSketch.noStroke();

        processingSketch.rect(spostamentoX, spostamentoY, lunghezza, larghezza);
    }
}
