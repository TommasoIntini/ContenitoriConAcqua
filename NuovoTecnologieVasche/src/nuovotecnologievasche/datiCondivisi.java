/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovotecnologievasche;

import java.awt.Point;
import java.util.Vector;
import processing.core.PApplet;

/**
 *
 * @author Tommaso
 */
public class datiCondivisi {

    private Vector<Vasca> vasche;
    private Barca barca = null;

    private Vasca[] vascheFinte;//per quando si spalma l'acqua, l'acqua in realtyà rimane la stessa, viene solo disegnata nuovamente

    private PApplet processingSketch = null;

    private thInput in = null;
    private thAcqua ac = null;

    private Point posDraw = new Point(150, 75);

    private int incX;//<0 inclinato sx
    private int incY;//<0 inclinato up

    private int l;
    private int la;

    private static final int spostamentoAcqua = 2;

    public datiCondivisi(int lunghezza, int larghezza, int nVasche, PApplet processingSketch) {

        ac = new thAcqua(this);
        int x = posDraw.x;
        int y = posDraw.y;

        vasche = new Vector<Vasca>();
        l = lunghezza;
        la = larghezza;

        for (int i = 0; i < nVasche; i++) {
            vasche.add(new Vasca(this, processingSketch, l, la, new Point(x, y), i));
            x += l;
        }

        Point posiz = new Point(posDraw.x + l, posDraw.y);

        Point posBarca = new Point(posDraw.x + (l / 2), posDraw.y + (la / 2));

        barca = new Barca(this, processingSketch, posBarca);

        incX = 0;
        incY = 0;
        in = new thInput(this);

        startTh();

        vascheFinte = new Vasca[2];

        vascheFinte[0] = null;
        vascheFinte[1] = null;

    }

    public void startTh() {
        ac.start();
        in.start();
    }

    public int getSpostamentoAcqua() {
        return spostamentoAcqua;
    }

    public int getLarghezzaVasca() {
        return l;
    }

    public void incIncX() {
        incX++;
    }

    public void incIncY() {
        incY++;
    }

    public void decIncX() {
        incX--;
    }

    public void decIncY() {
        incY--;
    }

    public int getAltezzaVasca() {
        return la;
    }

    public Point getPosDraw() {
        return posDraw;
    }

    public int getInclinazioneX() {
        return incX;
    }

    public int getInclinazioneY() {
        return incY;
    }

    public synchronized void hoInclinato(char direzione)//incrementa l'inclinazione, fa in modo che non si superi inclinazione 4
    {
        switch (direzione) {                        //in base all'inclinazione l'acqua diminuisce durante l'inclinazione
            case 'a':
            case 'A':
                if (incX >= -4) {
                    incX--;
                }
                break;
            case 'd':
            case 'D':
                if (incX <= 4) {
                    incX++;
                }
                break;
            default:
                break;
        }

    }

    public int getIndexPrimaConAcqua() {   //ritorna l'indice della vasca da cui defluisce l'acqua
        int temp = -1;
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).getAcqua().getLunghezza() > 0 && temp == -1) {
                temp = i;
            }
        }
        return temp;
    }

    public boolean stavoInclinandoASinistra() {   //Se si stava inclinando a sinistra restituisce true
        int index = getIndexPrimaConAcqua();
        if (vasche.elementAt(index).getSpostamentoAcquaX() == vasche.elementAt(index).getPosAcqua().x) {
            return true;
        } else {
            return false;
        }

    }

    public boolean stavoInclinandoADestra() {   //Se si stava inclinando destra restituisce true
        int index = getIndexPrimaConAcqua();
        if (vasche.elementAt(index).getSpostamentoAcquaX()
                > vasche.elementAt(index).getPosAcqua().x) {
            return true;
        } else {
            return false;
        }

    }

    public synchronized void inclinazioneX() {   //metodo principale, viene richiamato dal thAcqua ogni volta che l'attributo incX è diverso da 0
        try {
            if (incX > 0)//inclina a dx
            {
                if (vascheFinte[0] == null && vascheFinte[1] == null) {
                    for (int i = 0; i < vasche.size(); i++) {    //per tutte le vasche
                        if (vasche.elementAt(i).getStatoAcqua()) {   //se la vasca i è quella che si deve svuotare
                            vasche.elementAt(i).inclinaDx();       //inclina a derstra

                            vasche.elementAt(i + 1).spawnDx();   //e spawna acqua sulla destra della vasca adiacente

                            if (vasche.elementAt(i + 1).vascaPiena()) { //se la vasca successiva è piena 
                                vasche.elementAt(i).setAcqua(false);   //setta la vasca successiva come quella da svuotare
                                vasche.elementAt(i + 1).setAcqua(true);
                            }

                        }

                        barca.spostaDx();

                    }

                } else {
                    if (stavoInclinandoADestra()) {
                        if (vascheFinte[0] != null) {
                            vascheFinte[0].inclinaDx();
                        }
                        if (vascheFinte[1] != null) {
                            vascheFinte[1].inclinaDx();
                        }
                        if (acquaFintaHaRaggiuntoAcquaVeraV1()) {
                            vascheFinte[0] = null;
                        }
                        if (acquaFintaHaRaggiuntoAcquaVeraV2()) {
                            vascheFinte[1] = null;
                        }
                        if (vascheFinte[0] == null && vascheFinte[1] == null) {
                            vasche.elementAt(getIndexPrimaConAcqua()).setAcqua(true);
                        }
                    } else {
                        spostaAcquaSullaDestra();
                        vascheFinte[0].inclinaDx();
                        vascheFinte[1].inclinaDx();
                    }

                }
            } else if (incX < 0) {

                if (vascheFinte[0] == null && vascheFinte[1] == null) {
                    for (int i = 0; i < vasche.size(); i++) {
                        if (vasche.elementAt(i).getStatoAcqua()) {
                            vasche.elementAt(i).inclinaSx();

                            vasche.elementAt(i - 1).spawnSx();
                            //vasche.elementAt(i+1).spawnSx();
                            if (vasche.elementAt(i - 1).vascaPiena()) { //se la vasca successiva è piena 
                                vasche.elementAt(i).setAcqua(false);
                                vasche.elementAt(i - 1).setAcqua(true);
                            }

                        }
                        barca.spostaSx();

                    }
                } else {
                    if (stavoInclinandoASinistra()) {
                        if (vascheFinte[0] != null) {
                            vascheFinte[0].inclinaSx();
                        }
                        if (vascheFinte[1] != null) {
                            vascheFinte[1].inclinaSx();
                        }
                        if (acquaFintaHaRaggiuntoAcquaVeraV1()) {
                            vascheFinte[0] = null;
                        }
                        if (acquaFintaHaRaggiuntoAcquaVeraV2()) {
                            vascheFinte[1] = null;
                        }

                        if (vascheFinte[0] == null && vascheFinte[1] == null) {
                            vasche.elementAt(getIndexPrimaConAcqua() + 1).setAcqua(true);
                        }
                    } else {
                        spostaAcquaSullaSinistra();
                        vascheFinte[0].inclinaSx();
                        vascheFinte[1].inclinaSx();
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    public boolean acquaFintaHaRaggiuntoAcquaVeraV1() {
        try {
            int index = getIndexPrimaConAcqua();
            if (vascheFinte[0].getAcqua().getLunghezza() <= vasche.elementAt(index).getAcqua().getLunghezza()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;        //se si verifica un eccezione vuol dire che è gia stato interrotto
        }

    }

    public boolean acquaFintaHaRaggiuntoAcquaVeraV2() {
        int index = getIndexPrimaConAcqua();
        if (vascheFinte[1].getAcqua().getLunghezza() <= vasche.elementAt(index + 1).getAcqua().getLunghezza()) {
            return true;
        }
        return false;
    }

    public void spostaAcquaSullaDestra() {
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).acquaPresente()) {
                vasche.elementAt(i).spostaAcquaDx();
            }
        }

    }

    public void spostaAcquaSullaSinistra() {
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).acquaPresente()) {
                vasche.elementAt(i).spostaAcquaSx();
            }
        }

    }

    public void faiVascheFinteSx() {    //crea le vasche finte per fare finta di spalmare l'acqua
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).acquaPresente()) {
                if (vascheFinte[0] == null) {
                    vascheFinte[0] = vasche.elementAt(i).copyVasca();
                    vascheFinte[0].setAcqua(false);
                    vascheFinte[1] = vasche.elementAt(i + 1).copyVasca();
                    vascheFinte[1].setAcqua(false);
                }

            }
        }
    }

    public void faiVascheFinteDx() {    //crea le vasche finte per fare finta di spalmare l'acqua
        for (int i = 0; i < vasche.size(); i++) {
            if (vasche.elementAt(i).acquaPresente()) {
                if (vascheFinte[0] == null) {
                    vascheFinte[0] = vasche.elementAt(i -1).copyVasca();
                    vascheFinte[0].setAcqua(false);
                    vascheFinte[1] = vasche.elementAt(i ).copyVasca();
                    vascheFinte[1].setAcqua(false);
                }

            }
        }
    }

    public void spalmaAcquaSx() {  //non credo servano e sono vuoti, dovrebbero servire per spalmare l'acqua quando viene messa in orizzontale

        if (vascheFinte[0] == null && vascheFinte[1] == null) {
            faiVascheFinteSx();
        }

        if (!vascheFinte[0].getStatoAcqua()) {
            vascheFinte[0].spawnDx(spostamentoAcqua);
        }
        if (!vascheFinte[1].getStatoAcqua()) {
            vascheFinte[1].spawnDx(spostamentoAcqua);
        }

    }

    public boolean vascheFintePiene() {
        if (vascheFinte[0].getStatoAcqua() && vascheFinte[1].getStatoAcqua()) {
            return true;
        } else {
            return false;
        }
    }

    public void spalmaAcquaDx() {

        if (vascheFinte[0] == null && vascheFinte[1] == null) {
            faiVascheFinteDx();
        }

        vascheFinte[0].spawnSx(spostamentoAcqua);

        vascheFinte[1].spawnSx(spostamentoAcqua);
    }

    public int getSpostamentoX() {  //ritorna lo spostamento che effettua l'acqua(la sua inclinazione) positivo in modo che si possano usare per fare i calcoli sulla lunghezza
        if (incX < 0) {
            return incX * -1;
        } else {
            return incX;
        }

    }

    public void drawTutto() {   //metodo che viene richiamto ogni frame non toccare
        try {
            vascheFinte[1].draw();
        } catch (Exception e) {

        }

        try {
            vascheFinte[0].draw();
        } catch (Exception e) {

        }

        for (int i = 0; i < vasche.size(); i++) {
            vasche.elementAt(i).draw();
        }
        barca.draw();

    }

    public int getSizeVasche() {  //ritorna la dimensione del vetor
        return vasche.size();
    }

}
