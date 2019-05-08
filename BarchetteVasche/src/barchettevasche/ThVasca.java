/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barchettevasche;

import java.awt.*;

/**
 * @author Mirko Ghislanzoni
 */
public class ThVasca extends Thread {

    private final DatiCondivisi ptrDati;
    private final int idVasca;

    public ThVasca(DatiCondivisi ptrDati, int idVasca) {
        this.ptrDati = ptrDati;
        this.idVasca = idVasca;
    }

    @Override
    public void run() {
        final Vasca vasca = ptrDati.getVasca(idVasca);

        while (!isInterrupted()) {
            vasca.movimento();
            final Direzioni direzioneUscita = vasca.getAcquaPresente().direzioneDiUscitaAcqua((int) vasca.getDimensioni().getHeight());

            if (direzioneUscita != Direzioni.NONE) {
                System.out.println("L'acqua sta uscendo!");
                final Vasca ricevente = ptrDati.getVascaAdiacente(idVasca, direzioneUscita);
                vasca.spostaAcqua(1, ricevente);
                if (vasca.getBarca().isPresente()) {
                    final Direzioni dirBarca = vasca.isBarcaControBordi();
                    if (dirBarca != Direzioni.NONE) {
                        final Vasca v = ptrDati.getVascaAdiacente(idVasca, dirBarca);
                        vasca.spostaBarca(v);
                        Point nuovaPos = v.getBarca().getPosizione();
                        switch (dirBarca) {
                            case DESTRA:
                            case SINISTRA:
                                nuovaPos.x = -nuovaPos.x;
                                break;
                                
                            case SOPRA:
                            case SOTTO:
                                nuovaPos.y = -nuovaPos.y;
                                break;

                        }
                        v.getBarca().sposta(nuovaPos);
                    }
                }
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
