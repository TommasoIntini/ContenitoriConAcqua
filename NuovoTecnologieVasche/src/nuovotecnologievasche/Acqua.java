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

    private int lunghezza;  //rapprensentano la lunghezza e l'altezza, al momento ,dell'acqua
    private int larghezza;

    private int spostamentoX;  //rappresenta la x reale dell'acqua(angolo altoSx) pos rappresenta l'angolo massimo raggiungibile, qindi l'angolo alto sinistro della vasca
    private int spostamentoY; //questo no si usa
    private Point pos;

    private int lVasca;   //rappresenta la lunghezza della vasca in cui deve stare l'acqua(lunghezza massima)

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

    public int getSpostamentoX() { //ritorna la x altaSx dell'acqua
        return spostamentoX;
    }

    public Point getPos() {
        return pos;
    }

    public void spostaDx() {            //non ancora usati, perche conettualmente abagliati
        int temp = (pos.x + lVasca) - lunghezza;
        spostamentoX += temp;
    }

    public void spostaSx() {
        spostamentoX = pos.x;
    }

    public void spawnDx() {   //spunta l'acqua da Dx verso Sx 

        int spostamentoTemp = ptrDati.getSpostamentoX();  //prende l'incX positiva

        if (spostamentoX == pos.x) { //se la posizione dell'acqua è in alto a sinistra della vasca
            spostamentoX = pos.x + lVasca;
            spostamentoX -= spostamentoTemp;    //la imposta sullo spigolo destro in modo
            lunghezza += spostamentoTemp;
        } else {                    // altimenti, se è sullo spigolo destro
            spostamentoX -= spostamentoTemp;   //sposta la x di spostamentoTemp(incX)
            lunghezza += spostamentoTemp;    //e incrementa la lunghezza dello stesso valore
        }

        if ((pos.x + lunghezza - spostamentoTemp) < pos.x) {  //Se raggiunge la lunghezza della vasca la lunghezza dell'acqua è uguale a quella della vasca
            lunghezza = lVasca;
        }

    }

    public void spawnSx() {   //come spawnDx ma da Sx
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

    public boolean spawnDx(int s) {   //spunta l'acqua da Dx verso Sx in base al valore passato      ritorna true se la vasca è piena
        // altimenti, se è sullo spigolo destro
        spostamentoX -= s;   //sposta la x di spostamentoTemp(incX)
        lunghezza += s;    //e incrementa la lunghezza dello stesso valore

        //if ((pos.x + lunghezza - s) < pos.x) {  //Se raggiunge la lunghezza della vasca la lunghezza dell'acqua è uguale a quella della vasca
        if (spostamentoX <= pos.x) {
            spostamentoX = pos.x;
            lunghezza = lVasca;

            return true;
        }

        return false;

    }

    public boolean spawnSx(int s) {   //come spawnDx ma da Sx in base al valore passato      ritorna true se la vasca è piena

        lunghezza += s;

        if (pos.x + lunghezza > pos.x + lVasca) {
            lunghezza = lVasca;
            return true;
        }
        return false;

    }

    public void spostaAcquaDx() {
        spostamentoX = pos.x + lVasca - lunghezza;
    }

    public void spostaAcquaSx() {
        spostamentoX = pos.x;
    }

    public void inclinaSx() {
        int spostamentoTemp = ptrDati.getSpostamentoX();

        if (ptrDati.getInclinazioneX() > 0) {  //se si stava inclinando a destra
            spostamentoX -= spostamentoTemp;  //non credo entri mai qua dentro       :))
            lunghezza += spostamentoTemp;
        } else if ((pos.x + lunghezza - spostamentoTemp) < pos.x) {  //se l'acqua va tutta a sinistra
            lunghezza = 0;              //la lunghezza diventa 0 ricorda che loSpostamentoX è l'effettiva x
        } else {
            lunghezza -= spostamentoTemp;     //se l'acqua deve andare a sinistra solosi diminusice
        }
    }

    public void inclinaDx() {
        int spostamentoTemp = ptrDati.getSpostamentoX();

        if (ptrDati.getInclinazioneX() < 0) { //se si stava inclinando a sinistra
            lunghezza += spostamentoTemp;
        } else if ((spostamentoX + spostamentoTemp) >= (pos.x + ptrDati.getLarghezzaVasca())) {
            spostamentoX = pos.x + ptrDati.getLarghezzaVasca();
            lunghezza = 0;
        } else {
            spostamentoX += spostamentoTemp;
            lunghezza -= spostamentoTemp; //se l'acqua deve andare a destra solosi diminusice
        }

    }

    public void draw() {
        processingSketch.fill(15, 125, 255);
        processingSketch.noStroke();

        processingSketch.rect(spostamentoX, spostamentoY, lunghezza, larghezza);
    }
}
