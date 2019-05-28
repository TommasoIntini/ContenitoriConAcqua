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


    public Vasca()//dato che le vasche sono collegate tra di loro ne utilizzo una per rappresentarle tutte
    {

    }

    public Vasca(datiCondivisi ptrDati, PApplet process,                    //la classe serve quasi solo per la comunicazione tra il vettore di vasca e l'acqua di ogni vasca
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

    public void inclinaSx() {
        acqua.inclinaSx();
    }

    public void spawnDx() {  //spawna acqua dal margine destro della vasca
        acqua.spawnDx();
    }

    public void spawnSx() {  //spawna acqua dal margine sinistro della vasca
        acqua.spawnSx();
    }

    public void inclinaDx() {
        acqua.inclinaDx();
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
        if (acqua.getLunghezza() != 0 ) {
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

    
    public void appiattisciAcquaDaDx()
    {
        
    }
    
    public void appiattisciAcquaDaSx()
    {
        
    }
    public void draw() {

        acqua.draw();

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posi.x, posi.y, lunghezza, larghezza);

    }

}
