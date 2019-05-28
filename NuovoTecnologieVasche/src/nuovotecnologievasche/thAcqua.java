/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovotecnologievasche;

/**
 *
 * @author Tommaso
 */
public class thAcqua extends Thread {

    private datiCondivisi ptrDati;
    private int tempoAttesa;

    public thAcqua(datiCondivisi ptrDati) //thread sempre in esecuzione, ogni mezzo secondo modifica i valori dell'acqua(larghezza e lunghezza)
    {
        this.ptrDati = ptrDati;
        tempoAttesa = 100;
    }

    public void run() {
        try {

            while (!isInterrupted()) {
                if (ptrDati.getInclinazioneX() != 0) {
                    ptrDati.inclinazioneX();
                } 

                Thread.sleep(tempoAttesa);

            }
        } catch (Exception e) {

        }

    }

}
