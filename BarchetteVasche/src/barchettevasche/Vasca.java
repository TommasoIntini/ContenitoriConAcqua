/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barchettevasche;


import javafx.scene.shape.Box;
import processing.core.PApplet;

import java.awt.*;
import java.util.Random;


/**
 * @author Mirko Ghislanzoni
 */
public class Vasca {
    private final PApplet processingSketch;

    //Indica quanto è inclinata la vasca
    private float inclinazioneX;
    private float inclinazioneY;

    //Indica la dimensione della vasca (centimentri)
    private Box dimensioni;
    private Point posizione;

    //Rappresenta l'acqua presente nella scatola
    private JAcqua acquaPresente;

    //Rappresenta la barca presente nella vasca
    private JBarca barca;




    public Vasca(PApplet processingSketch) {
        this.processingSketch = processingSketch;
        dimensioni = new Box();
        dimensioni.setDepth(100);
        dimensioni.setHeight(500);

        posizione = new Point(200, 200);

        acquaPresente = new JAcqua();
        barca = new JBarca(this.processingSketch, this.posizione);
        barca.mostraBarca();
    }

    public Vasca(PApplet processingSketch, Point posizione) {
        this.processingSketch = processingSketch;
        dimensioni = new Box();
        dimensioni.setWidth(100);
        dimensioni.setDepth(100);
        dimensioni.setHeight(50);
        this.posizione = posizione;
        Random rn = new Random();
        acquaPresente = new JAcqua(rn.nextInt(40), processingSketch);
        barca = new JBarca(this.processingSketch, this.posizione);
        barca.mostraBarca();
        inclinazioneX = 0;
        inclinazioneY = 0;
    }

    public Vasca(PApplet processingSketch, Box dimensioni, Point posizione, JAcqua acquaPresente, JBarca barca) {
        this.processingSketch = processingSketch;
        this.dimensioni = dimensioni;
        this.posizione = posizione;
        this.acquaPresente = acquaPresente;
        this.barca = barca;
        inclinazioneX = 0;
        inclinazioneY = 0;
        barca = new JBarca(this.processingSketch, this.posizione);
        barca.mostraBarca();
    }


    //Metodo che visualizza la vasca, visualizzando anche l'acqua e la barca
    public void visualizzaVasca() {
    }

    /**
      * Metodo che simula un eventuale movimento della vasca 
      * richiamando aggiornaDistribuzioneVelocitaAcqua, aggiornaPosBarca e visualizzaVasca 
      */
    public void movimento() {
        aggiornaDistribuzioneVelocitaAcqua();
        aggiornaPosBarca();
        visualizzaVasca();
    }

    //Metodo che aggiorna la posizione della barca nella vasca in base alla velocità dell'acqua
    public void aggiornaPosBarca() {
        if (inclinazioneX < 0 && isBarcaControBordi() == Directions.SINISTRA)
            return;
        if (inclinazioneX > 0 && isBarcaControBordi() == Directions.DESTRA)
            return;
        
        barca.spostaX(inclinazioneX);
        
        if(inclinazioneY < 0 && isBarcaControBordi() == Directions.SOPRA)
            return;
        if(inclinazioneY > 0 && isBarcaControBordi() == Directions.SOTTO)
            return;

        barca.spostaY(inclinazioneY);
    }

    //Metodo che aggiorna l'altezza e la velocita dell'acqua in base all'inclinazione della vasca
    public void aggiornaDistribuzioneVelocitaAcqua() {
        acquaPresente.aggiornati(inclinazioneX, dimensioni);
    }

    /**
     * sposta l'acqua in un'altra vasca
     *
     * @param acqua acqua da spostare
     * @param altra   vasca in cui va spostata l'acqua
     */
    public void spostaAcqua(float acqua, Vasca altra) {
        acquaPresente.rimuoviAcqua(acqua);
        altra.acquaPresente.aggiungiAcqua(acqua);
    }

    /**
     * sposta la barca in un'altra vasca
     *
     * @param altra vasca in cui va spostata la barca
     */
    public void spostaBarca(Vasca altra) {

        this.barca.rimuoviBarca();
        altra.setBarca(barca);
        altra.barca.mostraBarca();
    }

    public JAcqua getAcquaPresente() {
        return acquaPresente;
    }

    public Box getDimensioni() {
        return dimensioni;
    }

    public void setBarca(JBarca barca) {
        this.barca = barca;
    }

    public JBarca getBarca() {
        return barca;
    }

    public Point getPosizioneCentrale() {
        return posizione;
    }


    public void draw() {
        processingSketch.fill(processingSketch.color(240, 0, 0));
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getWidth(), (float) dimensioni.getDepth());

        barca.draw();    
        
        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getWidth(), (float) dimensioni.getDepth());
        
        acquaPresente.visualizza(this);
    }

    public float getInclinazioneX() {
        return inclinazioneX;
    }

    public float getInclinazioneY() {
        return inclinazioneY;
    }

    public Directions isBarcaControBordi() {
        if (barca.getPosizione().x > dimensioni.getWidth())
            return Directions.DESTRA;
        else if (barca.getPosizione().x < 0)
            return Directions.SINISTRA;
        else if (barca.getPosizione().y < 0)
            return Directions.SOPRA;
        else if (barca.getPosizione().y > dimensioni.getDepth())
            return Directions.SOTTO;
        return Directions.NONE;
    }

    public void setInclinazioneX(float inclinazioneX) {
        this.inclinazioneX = inclinazioneX;
    }

    public void setInclinazioneY(float inclinazioneY) {
        this.inclinazioneY = inclinazioneY;
    }


}
