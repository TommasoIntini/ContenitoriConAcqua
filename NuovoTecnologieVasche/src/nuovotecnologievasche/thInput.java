/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovotecnologievasche;

import java.util.Scanner;

/**
 *
 * @author Tommaso
 */
public class thInput extends Thread {

    private datiCondivisi ptrDati;
    private Scanner sc = new Scanner(System.in);
    private char inserito;

    public thInput(datiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
        inserito = ' ';
    }

    public void run() {
        while (!isInterrupted()) {
            System.out.println("Premere 'A' per inclinare a sinistra e 'D' per inclinare a destra");//attende un input
            inserito = sc.next().charAt(0);
            ptrDati.hoInclinato(inserito);
            

               
        }

        
        
    }

}
