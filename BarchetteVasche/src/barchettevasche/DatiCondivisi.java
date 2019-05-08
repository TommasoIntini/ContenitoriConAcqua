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
    private int inclinazioneVascaX;
    private int inclinazioneVascaY;
    private int numV;
    private int LarghezzaSchermoX;
    private int AltezzaSchermoY;
    private Semaphore finito;


    public DatiCondivisi(int numVasche, PApplet processingSketch) {
        vasche = new Vector<>();
        numv = numVasche;

        //Aggiungo le vasche al vettore
        for (int i = 0; i < numV; i++)
        {
            vasche.add(new Vasca());
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
    public int getLarghezzaSchermoX() {
        return LarghezzaSchermoX;
    }

    public void setLarghezzaSchermoX(int LarghezzaSchermoX) {
        this.LarghezzaSchermoX = LarghezzaSchermoX;
    }

    public int getAltezzaSchermoY() {
        return AltezzaSchermoY;

    }

    public void setAltezzaSchermoY(int AltezzaSchermoY) {

        this.AltezzaSchermoY = AltezzaSchermoY;
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

    public int getinclinazioneVascaX() {
        return inclinazioneVascaX;
    }

    public void setinclinazioneVascaX(int inclinazioneVascaX) {
        this.inclinazioneVascaX = inclinazioneVascaX;
    }

    public int getinclinazioneVascaY() {
        return inclinazioneVascaY;
    }

    public void setinclinazioneVascaY(int inclinazioneVascaY) {
        this.inclinazioneVascaY = inclinazioneVascaY;
    }
    //--
}
