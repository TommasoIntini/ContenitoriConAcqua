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
public class Barca {

    private Point pos;
    private PApplet processingSketch;
    private datiCondivisi ptrDati;
    private int altezza;
    private int larghezza;

    private int spostamentoX;
    private int spostamentoY;

    private Point posCentrale;

    public Barca() {

    }

    public Barca(datiCondivisi ptrDati, PApplet process, Point pos) {
        this.ptrDati = ptrDati;
        this.processingSketch = process;
        altezza = 15;
        larghezza = 25;

        this.pos = pos;

        pos.x -= larghezza / 2;  //setta la posizione al centro
        pos.y -= altezza / 2;
        posCentrale = new Point(pos.x, pos.y);
    }

    public void draw() {
        processingSketch.fill(0, 0, 0);
        processingSketch.rect(pos.x, pos.y, larghezza, altezza);
    }

    public void spostaSx() {  //sposta la barca a sinistra
        if ((pos.x - 20) < ptrDati.getPosDraw().x) {
            pos.x = ptrDati.getPosDraw().x;
        } else {
            pos.x -= 20;
        }

        if (ptrDati.getInclinazioneX() == 0) {
            pos.x = posCentrale.x;
        }
    }

    public void spostaDx() {
        if ((pos.x + 20 + larghezza) > ptrDati.getPosDraw().x + ptrDati.getLarghezzaVasca()) {
            pos.x = ptrDati.getPosDraw().x + ptrDati.getLarghezzaVasca() - larghezza;
        } else {
            pos.x += 20;
        }
        if (ptrDati.getInclinazioneX() == 0) {
            pos.x = posCentrale.x;
        }
    }

    public void spostaUp() {
        if ((pos.y - 15) < ptrDati.getPosDraw().y) {
            pos.y = ptrDati.getPosDraw().y;
        } else {
            pos.y -= 15;
        }
        if (ptrDati.getInclinazioneY() == 0) {
            pos.y = posCentrale.y;
        }
    }

    public void spostaDw() {
        if ((pos.y + 15 + altezza) > ptrDati.getPosDraw().y + ptrDati.getAltezzaVasca()) {
            pos.y = ptrDati.getPosDraw().y + ptrDati.getAltezzaVasca() - altezza;
        } else {
            pos.y += 15;
        }
        if (ptrDati.getInclinazioneY() == 0) {
            pos.y = posCentrale.y;
        }
    }

    public boolean fuoriAcqua(Acqua h20) {
        return true;
    }

    public void spostaInAcqua() {

    }
}
