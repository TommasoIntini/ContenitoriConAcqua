package barchettevasche;

import java.awt.Color;
import java.awt.Point;
import processing.core.PApplet;

public final class JBarca {
    
    
    private Point posvasca; //angolo sx vasca
    
    private PApplet processingSketch;
    
    private float velocitaSpostamento; //valore tra 0 e 1
    private Point Posizione; //Indicante le coordinate all'interno della vasca dell'oggetto
    private boolean Presente; //Indicante la presenza della barca o viceversa
   
    private float Dimensioni; //Indicante la lunghezza della barca
    
    private Color Colore; //Colore della barca 
    


    public JBarca(float velocitaSpostamento, Point Posizione, boolean Presente, float Dimensioni, Color Colore) {
        this.velocitaSpostamento = velocitaSpostamento;
        this.Posizione = Posizione; //La posizione andrà impostata al centro della vasca
        this.Presente = Presente;
        this.Dimensioni = Dimensioni;
        this.Colore = Colore;
    }

    
    public JBarca(PApplet processingSketch, Point posvasca) {
        
        this.processingSketch = processingSketch;
        this.posvasca = posvasca;
        reset();
    }
    
    
    public void reset() {
        
        velocitaSpostamento = 0f;
        Presente = false; 
        Dimensioni = 10.0f;
        Posizione = new Point(); //La posizione andrà impostata al centro della vasca
        Colore = new Color(0,0, 240);
        
    }
    
    public void visualizza() {
    }
    
    public void rimuoviBarca() { //La barca viene rimossa
        
        Presente = false;
    }
    
    public void mostraBarca() { //Viene aggiunta la barca
        
        Presente = true;
    }
    
    public void sposta(Point nuovaPos) { 
        Posizione = nuovaPos;
    }
    
    public void spostaX(float inclinazioneVascaX) {
        
        velocitaSpostamento = Math.abs(inclinazioneVascaX); //La velocità è direttamente proporzionale all'inclinazione della vasca

        if(inclinazioneVascaX > 0) //Se la vasca è inclinata verso destra la vasca si spostaX verso destra
            Posizione.x += (0.5 * velocitaSpostamento);
        else
            Posizione.x += (-0.5 * velocitaSpostamento);
    }
    
    public void spostaY(float inclinazioneVascaY) {
        
        velocitaSpostamento = Math.abs(inclinazioneVascaY); //La velocità è direttamente proporzionale all'inclinazione della vasca

        if(inclinazioneVascaY > 0) //Se la vasca è inclinata verso destra la vasca si spostaX verso destra
            Posizione.y += (0.5 * velocitaSpostamento);
        else
            Posizione.y += (-0.5 * velocitaSpostamento);
    }
    
    public void draw() {
        
        if (Presente) {
            processingSketch.fill(Colore.getRGB());
            processingSketch.rect(Posizione.x + posvasca.x, Posizione.y + posvasca.y, Dimensioni, Dimensioni);
        }


    }

    public boolean isPresente() {
        return Presente;
    }

    public Point getPosizione() {
        return Posizione;
    }

    public float getDimensioni() {
        return Dimensioni;
    }

    public Color getColore() {
        return Colore;
    }
}
