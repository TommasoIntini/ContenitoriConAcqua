package barchettevasche;

import java.util.Scanner;

/**
 * @author Intini Tommaso
 * @version 1.0
 * @brief La classe gestisce il thread dedicato all'input
 */
public class Input extends Thread {

    /**
     * @brief attributi della classe Input
     */
    private DatiCondivisi ptrDati;//puntatore i dati condivisi
    private Scanner sc = new Scanner(System.in);

    /**
     * @brief costruttore del thread input
     */
    public Input(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    /**
     * @brief Metodo per l'attivazione del thread
     *
     * finchè il thread non è interrorro, quando lo scanner riceve dei valori,
     * si entra in un ciclo for che setta e muove la posizione di tuttele
     * vasche;
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.print("Inclinazione X: ");
            float x = sc.nextFloat();
            System.out.print("Inclinazione Y: ");
            float y = sc.nextFloat();

            System.out.println("-------------\n");
            for (int i = 0; i < ptrDati.getNumScatole(); i++) {
                Vasca v = ptrDati.getVasca(i);

                v.setY(y);
                v.setX(x);

                v.muovi();
            }
        }
    }
}
