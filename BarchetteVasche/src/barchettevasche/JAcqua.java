import java.awt.Point;
import javafx.scene.shape.Box;
import javax.swing.text.Position;
import processing.core.PApplet;

/**
 @author  Buccheri Federico
 @version 1.0
 @brief La classe viene utilizzata per la gestione dell'acqua presente all'interno di una vasca
 */
public class JAcqua {
    /**
     @brief L'attributo rappresenta la direzione di inclinazione del piano 
     L'attributo puo' assumere il valore: 
        - "+1" quando l'inclinazione e' positiva e quindi l'acqua si muove da DX verso SX;
        - "-1" quando l'inclinazione e' negativa e quindi l'acqua si muove da SX verso DX.
     
     @author  Buccheri Federico
     @version 1.0
    */
    private float movimentoX;
    
    /**
     @brief L'attributo rappresenta l'altezza massima raggiunta dall' acqua sul lato maggiore
     L'attributo indica il valore raggiunto massimo (in cm) dell' acqua sul "lato di appoggio" superiore. 
     
     @author  Buccheri Federico
     @version 1.0
    */
    private float altezzaLatoMaggiore;
    
    /**
     @brief L'attributo rappresenta l'altezza minima raggiunta dall' acqua sulla lato minore
     L'attributo indica il valore raggiunto minimo (in cm) dell' acqua sulla "lato di appoggio" inferiore. 
     
     @author  Buccheri Federico
     @version 1.0
    */
    private float altezzaLatoMinore;
    
    /**
     @brief L'attributo rappresenta la quantità di acqua presente all'interno della vasca
     
     @author  Buccheri Federico
     @version 1.0
    */
    private float quantitaacqua;//QUANTITA MAX 100
    
    /**
     @brief L'attributo rappresenta la velocita' di spostamento dell' acqua
      
     @author  Buccheri Federico
     @version 1.0
    */
    private float velocita;
    
    /**
     @brief L'attributo rappresenta "la tavola da disegno" utilizzata dal main per la visualizzazione dell'interfaccia grafica
      Viene utilizzato per utilizzare i metodi di disegno utilizzati da processing
      
     @author  Buccheri Federico
     @version 1.0
    */
    private PApplet processingSketch;
            
    /**
     @brief Costruttore senza parametri della classe
     Il Costruttore vuoto della classe permette di avviare la fase di setup ed impostare a valori 
     di default gli attributi della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public JAcqua(){
        this.quantitaacqua=10;
        setup();
    }
    
    /**
     @brief Costruttore con parametri della classe
     Il Costruttore con parametri della classe permette di avviare la fase di setup ed impostare a valori 
     di default gli attributi della classe, inoltre gli attributi.
     
	 quantita e processingSketch assumeranno i valori passati come parametro
      
     @param quantita quantita' presente di acqua
     @param disegno puntatore che identifica quale "tavola da disegno" dovra' essere utilizzata per la visualizzazione
     della parte grafica della acqua
      
     @author  Buccheri Federico
     @version 1.0
    */
    public JAcqua(float quantita, PApplet disegno){
        this.quantitaacqua=quantita;
        processingSketch=disegno;
        setup();
    }
    
    /**
     @brief Metodo che imposta gli attributi movimentoX e velocita a valori di default
     Il metodo viene richiamato da ogni costruttore per inizializzare gli attributi
     
     @author  Buccheri Federico
     @version 1.0
    */
    private void setup(){
        movimentoX=0;
        //movimentoY=0;
        velocita=0;
    }
    
    /**
     @brief Il metodo permette di aumentare il volume dell' acqua presente
      
     @paran aggiunta la quantita' di acqua che dovra' essere aggiunta all'attributo quantitaacqua della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public void aggiungiacqua(float aggiunta){
        quantitaacqua+=aggiunta;
    }
    
    /**
     @brief Il metodo permette di diminuire il volume dell' acqua presente
      
     @paran rimossa la quantita' di acqua che dovra' essere rimossa all'attributo quantitaacqua della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public void rimuoviacqua(float rimossa){
        quantitaacqua-=rimossa;
    }
    
    /**
     @brief Il metodo permette di calcolare i valori degli attributi MovimentoX, altezzaLatoMaggiore e altezzaLatoMinore
      Il metodo richiama alcune funzioni che calcolano, in base ad alcuni parametri, i valori che gli attributi dovranno assumere
      
     @paran inclinazioneX l'inclinazione dell' acqua
     @param dimensioni le dimensioni della vasca dove e' presente l' acqua
     
     @author  Buccheri Federico
     @version 1.0
    */
    public void aggiornati(float inclinazioneX, Box dimensioni){
        impostaMovimentoX(inclinazioneX);
        
        calcolaLivelloDiRaggiungimentoMaggiore(inclinazioneX, dimensioni);
    }
      
    /**
     @brief Il metodo permette di calcolare i valori che altezzaLatoMaggiore e altezzaLatoMinore assumeranno
      Tenendo conto dell'inclinazione e delle dimensioni della vasca, il metodo applica alcune formule matematiche 
      (seno, coseno, somma di angoli, etc.) e permette il calcolo dell'altezza dell' acqua sulle due sponde della vasca.
      
     @paran inclinazioneX l'inclinazione dell' acqua
     @param dimensioni le dimensioni della vasca dove e' presente l' acqua
      
     @author  Buccheri Federico
     @version 1.0
    */
    private void calcolaLivelloDiRaggiungimentoMaggiore(float inclinazioneX,Box dimensioni){
        inclinazioneX=Math.abs(inclinazioneX);//La rendo sempre positiva
        
        double altezzavasca = dimensioni.getHeight();
        double lato = dimensioni.getDepth();
        double VolumeIniziale =  ((quantitaacqua*lato)*lato);
        
        
        double angoloAdiacente = 180-(90+inclinazioneX);
        double risCosAngA=  Math.cos(trasformaARadianti(angoloAdiacente))*altezzavasca;
        double risSinAngA=  Math.sin(trasformaARadianti(angoloAdiacente))*altezzavasca;
        
        double angoloSuperioreSX= Math.abs((180-(angoloAdiacente+90))-90);
        double angoloSuperioreDX= 180-(angoloSuperioreSX+90);
        
        double lunghezzaDiagonale = lato/(Math.cos(trasformaARadianti(angoloSuperioreDX)));
        
        
        double latoTriangolo =  (Math.sin(trasformaARadianti(angoloSuperioreDX))*lunghezzaDiagonale);
        double volumeTriangolo = ((lato*latoTriangolo)/2)*lato;
        

        double altezzaRett = -((volumeTriangolo-VolumeIniziale)/(lato*lato));
        double volumeRettangolo = (altezzaRett*lato)*lato;
        
        double volumeTrapezio = volumeRettangolo+volumeTriangolo;
        

        altezzaLatoMaggiore= (float)( altezzaRett+latoTriangolo);
        altezzaLatoMinore=(float) Math.abs(altezzaRett);
    }
    
    /**
     @brief Il metodo permette di convertire i gradi passati da parametro a radianti
      
     @paran gradi i gradi da convertire
     @return double valore corrispondente in radianti in base ai gradi passati da parametro
     
     @author  Buccheri Federico
     @version 1.0
    */
    private double trasformaARadianti(double gradi){
        //180:gradi=pi:rad
        return ((gradi*Math.PI)/180);
    }
    
    /**
     @brief Il metodo permette di impostare l'attributo movimentoX della classe
     Il metodo imposta in base al parametro passato, l'attributo movimentoX della classe.
     Quando il parametro e' positivo (acqua che si muove da DX a SX), il movimentoX viene impostato a +1;
     se negativo (acqua che si muove da SX a DX), movimentoX viene impostato a -1
     
     @paran inclinazioneX l'inclinazione della acqua
     
     @author  Buccheri Federico
     @version 1.0
    */
    private void impostaMovimentoX(float inclinazioneX){
        //IMPOSTO IL VERSO DI MOVIMENTO/ORIENTAMENTO DELL'ACQUA (VERSO DX O VERSO SX)
        
        //Se l'inclinazione e' positiva, la acqua si muove verso SX quindi il livello maggiore
        //registrato sara' sulla lato SX della vasca
        if(inclinazioneX>0){
            movimentoX=1;
        }else{
           //Se l'inclinazione e' negativa, la acqua si muove verso DX quindi il livello maggiore
            //registrato sara' sulla lato DX della vasca
            movimentoX=-1; 
        }
    }
    
    /**
     @brief Il metodo dice se la acqua sta uscendo dalla vasca
     Il metodo controlla se l'altezza dell' acqua sulla sua lato maggiore (altezzaLatoMaggiore), 
     e' superiore rispetto all'altezza da superare; in tal caso la acqua esce altrimenti l' acqua 
     non ha un inclinazione tale da permettere la sua fuoriuscita.
     
     @paran altezzaDaSuperare l'altezza dopo la quale la acqua esce
     @return boolean True: esce la acqua  | False: la acqua non ha un inclinazione tale per uscire
     
     @author  Buccheri Federico
     @version 1.0
    */
    private boolean staUscendo(int altezzaDaSuperare){
        if(altezzaLatoMaggiore>altezzaDaSuperare){
            //Significa che la acqua sta' uscendo
            return true;
        }else{
            return false;//La acqua ha un altezza inferiore e quindi non esce dalla vasca
        }
        
    }

    /**
     @brief Il metodo ritorna la direzione di uscita della acqua
     Il metodo dice in che direzione (Destra,Dinistra,Sotto,Sopra,NONE) l' acqua sta uscendo.
     
     @paran altezzaDaSuperare l'altezza dopo la quale l' acqua esce
     @return Directions Rappresenta la direzione di uscita. Se e' uguale a NONE, significa che non sta uscendo.
     
     @author  Buccheri Federico
     @version 1.0
    */
    public Directions direzioneDiUscitaacqua(int altezzaDaSuperare){
        Directions direzione = Directions.NONE;//Inizialmente non esce niente
        
        //Controllo se sta uscendo la acqua
        if(staUscendo(altezzaDaSuperare)){
            
            //Sta uscendo, dico da dove esce
            if(movimentoX==1){
                direzione = Directions.SINISTRA;
            }else{
                //Movimento = -1
                direzione = Directions.DESTRA;
            }
            
        }else{
            //Non sta uscendo
            direzione = Directions.NONE;
        }
        
        return direzione;
    }
    
    /**
     @brief Il metodo permette di disegnare l' acqua sulla tavola da disegno
     Utilizzando l'attributo processingSketch, il metodo disegna in corrispondenza della vasca l' acqua presente.
     
     @paran vasca la vasca di appartenenza
     
     @author  Buccheri Federico
     @version 1.0
    */
    public void visualizza(vasca vasca){
        //TODO 
        processingSketch.fill(processingSketch.color(0, 255, 0));
        Box dim = vasca.getDimensioni();
        Point pos = vasca.getPosizioneCentrale();
        Float altezzavasca = (float)dim.getDepth();
        
        processingSketch.rect(pos.x,pos.y+altezzavasca, altezzavasca,-quantitaacqua);
    }
    
    //GET e SET DELLA CLASSE
    
    /**
     @brief Il metodo permette di ottenere il valore dell'attributo movimentoX
     
     @return movimentoX valore dell'attributo movimentoX della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public float getMovimentoX() {
        return movimentoX;
    }
    
    /**
     @brief Il metodo permette di ottenere il valore dell'attributo quantitaacqua
     
     @return quantitaacqua valore dell'attributo quantitaacqua della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public float getQuantita() {
        return quantitaacqua;
    }
    
    /**
     @brief Il metodo permette di ottenere il valore dell'attributo velocita
     
     @return velocita valore dell'attributo velocita della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public float getVelocita() {
        return velocita;
    }

    /**
     @brief Il metodo permette di ottenere il valore dell'attributo altezzaLatoMaggiore
     
     @return altezzaLatoMaggiore valore dell'attributo altezzaLatoMaggiore della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public float getAltezzaLatoMaggiore() {
        return altezzaLatoMaggiore;
    }
    
    /**
     @brief Il metodo permette di ottenere il valore dell'attributo altezzaLatoMinore
     
     @return altezzaLatoMinore valore dell'attributo altezzaLatoMinore della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public float getAltezzaLatoMinore() {
        return altezzaLatoMinore;
    }
    
    /**
     @brief Il metodo permette di ottenere il toString della classe
     
     @return string stringa contenenti alcune informazioni importanti della classe
     
     @author  Buccheri Federico
     @version 1.0
    */
    public String toString(){
        return "Lato maggiore: "+altezzaLatoMaggiore+"\nLato minore: "+altezzaLatoMinore+"\nQuantita' acqua: "+altezzaLatoMinore+"\nMovimentoX: "+movimentoX+"\n---------";
    }
}
