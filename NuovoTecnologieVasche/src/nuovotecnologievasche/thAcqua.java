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
    private int inclinazionePrec;

    public thAcqua(datiCondivisi ptrDati) //thread sempre in esecuzione, ogni mezzo secondo modifica i valori dell'acqua(larghezza e lunghezza)
    {
        this.ptrDati = ptrDati;
        tempoAttesa = 100;
        inclinazionePrec = 0;
    }

    public void run() {
        try {

            while (!isInterrupted()) {
                
                if (ptrDati.getInclinazioneX() != 0) {  //se è stata inserita un inclinazione da thInput
                    ptrDati.inclinazioneX();            
                    if (inclinazionePrec == 0) {  //rappresenta l'inclinazione precendente, in modo che quando l'inclinazione effettiva della vasca vada a 0 rimane o positivo o negativo
                        if (ptrDati.getInclinazioneX() > 0) {       //in modo che si capische se l'acqua si deve espandere sul piano da sinistra o da destra
                            inclinazionePrec = 1;
                        } else {
                            inclinazionePrec = -1;
                        }
                    }

                }

                if (ptrDati.getInclinazioneX() == 0) {  //quando no è piu inclinato
                    if (inclinazionePrec == 1) {  //si  spalma l'acqua
                        ptrDati.spalmaAcquaSx();   //e si azzera l'inclinazione precedente in modo che possa nuovamente essere utilizzata
                        inclinazionePrec = 0;
                    } else if (inclinazionePrec == -1) {
                        ptrDati.spalmaAcquaDx();
                        inclinazionePrec = 0;
                    }

                }
                Thread.sleep(tempoAttesa);

            }
        } catch (Exception e) {

        }

    }

}
