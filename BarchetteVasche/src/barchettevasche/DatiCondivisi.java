//package;

import processing.core.PApplet;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Vector;
import java.util.concurrent.Semaphore;


/**
 * @author Maffei Michele
 */
public class DatiCondivisi
{


    private Vector<Vasca> vasche;
    private int numV;
    private int dimensioneSchermoX;
    private int dimensioneSchermoY;
    private int inclinazioneTavoloDiGiocoX;
    private int inclinazioneTavoloDiGiocoY;
    private Semaphore finito;


    public DatiCondivisi(int numVasche, PApplet processingSketch) {
        vasche = new Vector<>();
        numv = numVasche;

        //Aggiungo le vasche al vettore
        for (int i = 0; i < numV; i++)
        {
            vasche.add(new Vasca(processingSketch, new Point(200 * i + 100, 200)));
        }
    }

    //Metodi Semaforo
    public Semaphore getFinito() {
        return finito;
    }

    public void setFinito(Semaphore finito) {
        this.finito = finito;
    }

    public synchronized void dicoCheHoFinito throws InterruptedException()
    {
        finito.release();
    }

    public synchronized void aspettoCheFiniscono throws InterruptedException() {
        finito.acquire();
    }
    //--

    //Metodi schermo
    public int getDimensioneSchermoX() {
        return dimensioneSchermoX;
    }

    public void setDimensioneSchermoX(int dimensioneSchermoX) {
        this.dimensioneSchermoX = dimensioneSchermoX;
    }

    public int getDimensioneSchermoY() {
        return dimensioneSchermoY;

    }

    public void setDimensioneSchermoY(int dimensioneSchermoY) {

        this.dimensioneSchermoY = dimensioneSchermoY;
    }
    //--

    //Metodi quantità vasche
    public int getNumV() {
        return numV;
    }

    public void setNumV(int numV) {
        this.numV = numV;
    }
    //--


    //Metodi gestione vasche


    public void addVasca(Vasca vasca) {
        vasche.add(vasca);
    }

    public Scatola getVasca(int id) {
        return (Vasca) vasche.get(id);
    }

    public Vasca getVascaAdiacente(int id, int direzioneDellaVascaDaSapere) {
        return null;
    }

    public int getNumVasche() {
        return numV;
    }

    public Vector<Vasca> getVasche() {
        return vasche;
    }
    //---

    public void returnVascheVuote() {
        return vasca.;
    }

    //Metodi inclinazione

    public int getInclinazioneTavoloDiGiocoX() {
        return inclinazioneTavoloDiGiocoX;
    }

    public void setInclinazioneTavoloDiGiocoX(int inclinazioneTavoloDiGiocoX) {
        this.inclinazioneTavoloDiGiocoX = inclinazioneTavoloDiGiocoX;
    }

    public int getInclinazioneTavoloDiGiocoY() {
        return inclinazioneTavoloDiGiocoY;
    }

    public void setInclinazioneTavoloDiGiocoY(int inclinazioneTavoloDiGiocoY) {
        this.inclinazioneTavoloDiGiocoY = inclinazioneTavoloDiGiocoY;
    }
    //--
}
