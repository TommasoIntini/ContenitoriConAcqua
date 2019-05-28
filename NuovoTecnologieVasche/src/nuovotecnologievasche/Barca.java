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
 * @author Mirko Ghislanzoni
 */
public class Barca {

    private Point pos;
    private PApplet processingSketch;
    private datiCondivisi ptrDati;
    private int altezza;
    private int larghezza;

    private int spostamentoX; //rappresenta la posizione sull'asse x della barca
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
        
        int spostamentoTemp = ptrDati.getSpostamentoX();
        
        if ((pos.x - spostamentoTemp) < ptrDati.getPosDraw().x) {
            pos.x = ptrDati.getPosDraw().x;
        } else {
            pos.x -= spostamentoTemp;
        }

        if (ptrDati.getInclinazioneX() == 0) {
            pos.x = posCentrale.x;
        }
    }

    public void spostaDx() { //sposta la barca a destra
        int spostamentoTemp = ptrDati.getInclinazioneX();
        if ((pos.x + spostamentoTemp + larghezza) > ptrDati.getPosDraw().x + ptrDati.getLarghezzaVasca()) {
            pos.x = ptrDati.getPosDraw().x + ptrDati.getLarghezzaVasca() - larghezza;
        } else {
            pos.x += spostamentoTemp;
        }
        if (ptrDati.getInclinazioneX() == 0) {
            pos.x = posCentrale.x;
        }
    }

   
    public boolean fuoriAcqua(Acqua h20) {
        return true;
    }

   // public void spostaInAcqua() {

    //}
}
