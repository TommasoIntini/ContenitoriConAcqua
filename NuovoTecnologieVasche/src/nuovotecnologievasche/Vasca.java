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
    
    private PApplet processingSketch;
    private datiCondivisi ptrDati;

    private Point posi;
    
    public Vasca()//dato che le vasche sono collegate tra di loro ne utilizzo una per rappresentarle tutte
    {
        
    }
    
    public Vasca(datiCondivisi ptrDati,PApplet process,
            int lunghezza,int laghezza,Point pos)
    {
        this.lunghezza = lunghezza;
        this.larghezza = laghezza;
        
        this.ptrDati = ptrDati;      
        this.processingSketch = process;
        
        this.posi = pos;
        
    }
    
    public void draw()
    {
        
        processingSketch.noFill();
        processingSketch.stroke(0,0,0);
        processingSketch.rect(posi.x,posi.y,lunghezza,larghezza);
    }
    
    
    
}
