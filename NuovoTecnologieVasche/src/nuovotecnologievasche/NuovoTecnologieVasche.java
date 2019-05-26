/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovotecnologievasche;

import processing.core.PApplet;

/**
 *
 * @author Tommaso
 */
public class NuovoTecnologieVasche extends PApplet{

    private static datiCondivisi dati = null;
    /**
     * @param args the command line arguments
     */
    
    public NuovoTecnologieVasche()
    {
        dati = new datiCondivisi(150,150,2,this);
        
    }
    public static void main(String[] args) {
        PApplet.main(new String[]{"nuovotecnologievasche.NuovoTecnologieVasche"});//Imposto questo come main principale
        // TODO code application logic here
        
    }
    

    public void settings()
    {
        size(900, 500);
    }
    public void setup() {
        
        frameRate(30);
        ellipseMode(RADIUS);
    }

    public void draw() {//richiamato ogni frame
        background(255,255,255);

        dati.drawTutto();

    }
    
}
