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
public class Vasca {

    private boolean vascaPiena;  //rappresenta la vasca da cui scorre l'acqua ser è true

    private int lunghezza = 0;  //di ogni vasca
    private int larghezza = 0;

    private Acqua acqua;

    private PApplet processingSketch;
    private datiCondivisi ptrDati;

    private Point posi;   //angolo altoSx della vasca

    public Vasca(PApplet processingSketch, datiCondivisi ptrDati)//dato che le vasche sono collegate tra di loro ne utilizzo una per rappresentarle tutte
    {
        vascaPiena = false;  //rappresenta la vasca da cui scorre l'acqua ser è true

        lunghezza = 0;  //di ogni vasca
        larghezza = 0;

        acqua = null;

        this.processingSketch = processingSketch;
        this.ptrDati = ptrDati;

        posi = null;

        Point posi;   //angolo altoSx della vasca
    }

    public Vasca(datiCondivisi ptrDati, PApplet process, //la classe serve quasi solo per la comunicazione tra il vettore di vasca e l'acqua di ogni vasca
            int lunghezza, int larghezza, Point pos, int nVasca) {
        this.lunghezza = lunghezza;
        this.larghezza = larghezza;

        this.ptrDati = ptrDati;
        this.processingSketch = process;

        this.posi = pos;

        if (nVasca == 0) {                                              //se vuoi impostare un vasca iniziale diversa piena cambia il numero, con 0, il primo el del vettore viene riempito di acqua
            this.acqua = new Acqua(lunghezza, ptrDati, process, lunghezza, larghezza, pos);
            vascaPiena = true;
        } else {
            this.acqua = new Acqua(ptrDati, process, 0, larghezza, new Point(pos.x, pos.y), lunghezza);
        }

    }

    public Vasca copyVasca() {
        Vasca temp = new Vasca(ptrDati, processingSketch, lunghezza, larghezza, posi, 1);
        return temp;
    }

    public Acqua getAcqua() {
        return acqua;
    }

    public void spostaAcquaDx() {
        acqua.spostaAcquaDx();
    }

    public void spostaAcquaSx() {
        acqua.spostaAcquaSx();
    }

    public void inclinaSx() {
        try {
            acqua.inclinaSx();
        } catch (Exception e) {

        }

    }

    public void spawnDx() {  //spawna acqua dal margine destro della vasca
        acqua.spawnDx();
    }

    public void spawnSx() {  //spawna acqua dal margine sinistro della vasca
        acqua.spawnSx();
    }

    public void spawnDx(int s) {  //spawna acqua dal margine destro della vasca passando il valore di cui si deve spostare l'acqua
        if (acqua.spawnDx(s)) {
            vascaPiena = true;
        }
    }

    public void spawnSx(int s) {  //spawna acqua dal margine sinistro della vascapassando il valore di cui si deve spostare l'acqua
        if (acqua.spawnSx(s)) {
            vascaPiena = true;
        }
    }

    public void inclinaDx() {
        try {
            acqua.inclinaDx();
        } catch (Exception e) {

        }

    }

    public int getSpostamentoAcquaX() {
        return acqua.getSpostamentoX();
    }

    public Point getPosAcqua() {
        return acqua.getPos();
    }

    public void setAcqua(boolean ris) { //setta se la vasca indicata dal vettore deve diventare quella da cui l'acqua dever defluire
        this.vascaPiena = ris;
    }

    public boolean acquaPresente() {   //se è presente una qualunque quantità di acqua
        if (acqua.getLunghezza() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void spostaAcquaSullaDestra() {   //non ancora usati, forse inutili
        acqua.spostaDx();
    }

    public void spostaAcquaSullaSinistra() {
        acqua.spostaSx();
    }

    public boolean vascaPiena() {   //se la vasca è piena ritorna true
        if (acqua.getLunghezza() == lunghezza && acqua.getLarghezza() == larghezza) {
            return true;
        }
        return false;
    }

    public boolean getStatoAcqua() {
        return vascaPiena;
    }

    public void appiattisciAcquaDaDx() {

    }

    public void appiattisciAcquaDaSx() {

    }

    public void draw() {

        acqua.draw();

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posi.x, posi.y, lunghezza, larghezza);

    }

}
