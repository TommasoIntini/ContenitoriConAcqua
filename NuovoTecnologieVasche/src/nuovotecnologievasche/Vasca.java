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

    private int h = 15;    //dimesnioni
    private int lunghezza = 0;
    private int larghezza = 0;

    private Acqua acqua;

    private PApplet processingSketch;
    private datiCondivisi ptrDati;

    private Point posi;

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
            this.acqua = new Acqua(process, lunghezza, larghezza, pos);
        } else {
            int x = pos.x;
            this.acqua = new Acqua(process, 0, 0, new Point(x + lunghezza, pos.y));
        }

    }

    public void inclinaSx() {
        
        
    }

    public void inclinaDx() {

    }

    public void inclinaUp() {

    }

    public void inclinaDw() {

    }

        
    public boolean isVascaPiena()
    {
        if(acqua.getLarghezza() == larghezza)
            if(acqua.getLunghezza() == lunghezza)
                return true;
        return false;
    }
    
    public void draw() {

        acqua.draw();
        
        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posi.x, posi.y, lunghezza, larghezza);

        
    }

}
