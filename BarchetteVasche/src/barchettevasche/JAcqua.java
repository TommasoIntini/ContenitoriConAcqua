import java.awt.Point;
import javafx.scene.shape.Box;
import javax.swing.text.Position;
import processing.core.PApplet;

/**
La classe viene utilizzata per la gestione dell'acqua presente all'interno di una vasca
 */
public class JAcqua {
    /**
  	L'attributo rappresenta la direzione di inclinazione del piano 
     L'attributo puo' assumere il valore: 
        - "+1" l'acqua si muove da DX verso SX;
        - "-1" l'acqua si muove da SX verso DX.
    */
    private float movimentoX;
    
    /**
     L'attributo rappresenta l'altezza massima raggiunta dall' acqua sul lato maggiore
     L'attributo indica il valore raggiunto massimo (in cm) dell' acqua sul "lato di appoggio" superiore. 
    */
    private float altezzaLatoMaggiore;
    
    /**
     L'attributo rappresenta l'altezza minima raggiunta dall' acqua sulla lato minore
     L'attributo indica il valore raggiunto minimo (in cm) dell' acqua sulla "lato di appoggio" inferiore.  
    */
    private float altezzaLatoMinore;
    
    /**
     L'attributo rappresenta la quantità di acqua presente all'interno della vasca
    */
    private float quantitaacqua;//QUANTITA MAX 100
    
    /**
     L'attributo rappresenta la velocita' di spostamento dell' acqua     
    */
    private float velocita;
    
    /**
     L'attributo rappresenta "la tavola" utilizzata dal main per la visualizzazione dell'interfaccia grafica
     Viene utilizzato per utilizzare i metodi di disegno utilizzati da processing      
    */
    private PApplet processingSketch;
            
    /**
     Costruttore senza parametri della classe
     Il Costruttore vuoto della classe permette di avviare la fase di setup ed impostare a valori 
     di default gli attributi della classe
    */
    public JAcqua(){
        this.quantitaacqua=10;
        setup();
    }
    
    /**
     Costruttore con parametri della classe
     Il Costruttore con parametri della classe permette di avviare la fase di setup ed impostare a valori 
     di default gli attributi della classe, inoltre gli attributi. 
    */
    public JAcqua(float quantita, PApplet disegno){
        this.quantitaacqua=quantita;
        processingSketch=disegno;
        setup();
    }
    
    /**
     Metodo che imposta gli attributi movimentoX e velocita a valori di default
     Il metodo viene richiamato da ogni costruttore per inizializzare gli attributi
    */
    private void setup(){
        movimentoX=0;
        //movimentoY=0;
        velocita=0;
    }
    
    /**
     Il metodo permette di aumentare il volume dell' acqua presente
      
     aggiunta la quantita' di acqua che dovra' essere aggiunta all'attributo quantitaacqua della classe.
    */
    public void aggiungiacqua(float aggiunta){
        quantitaacqua+=aggiunta;
    }
    
    /**
     Il metodo permette di diminuire il volume dell' acqua presente  
     rimossa la quantita' di acqua che dovra' essere rimossa all'attributo quantitaacqua della classe
    */
    public void rimuoviacqua(float rimossa){
        quantitaacqua-=rimossa;
    }
    
    /**
     Il metodo permette di calcolare i valori degli attributi MovimentoX, altezzaLatoMaggiore e altezzaLatoMinore
     Il metodo richiama alcune funzioni che calcolano, in base ad alcuni parametri, i valori che gli attributi dovranno assumere
    */
    public void aggiornati(float inclinazioneX, Box dimensioni){
        impostaMovimentoX(inclinazioneX);
        
        calcolaLivelloDiRaggiungimentoMaggiore(inclinazioneX, dimensioni);
    }
      
    /**
     Il metodo permette di calcolare i valori che altezzaLatoMaggiore e altezzaLatoMinore assumeranno
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
     Il metodo permette di impostare l'attributo movimentoX della classe
     Il metodo imposta in base al parametro passato, l'attributo movimentoX della classe.     
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
     Il metodo dice se la acqua sta uscendo dalla vasca
     Il metodo controlla se l'altezza dell' acqua sulla sua lato maggiore (altezzaLatoMaggiore), 
     e' superiore rispetto all'altezza da superare; in tal caso la acqua esce altrimenti l' acqua 
     non ha un inclinazione tale da permettere la sua fuoriuscita.
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
     Il metodo ritorna la direzione di uscita della acqua
     Il metodo dice in che direzione (Destra,Dinistra,Sotto,Sopra,NONE) l' acqua sta uscendo.
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
     Il metodo permette di disegnare l' acqua sulla tavola da disegno
     Utilizzando l'attributo processingSketch, il metodo disegna in corrispondenza della vasca l' acqua presente.
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
     Il metodo permette di ottenere il valore dell'attributo movimentoX 
     movimentoX valore dell'attributo movimentoX della classe
    */
    public float getMovimentoX() {
        return movimentoX;
    }
    
    /**
     Il metodo permette di ottenere il valore dell'attributo quantitaacqua
    */
    public float getQuantita() {
        return quantitaacqua;
    }
    
    /**
     Il metodo permette di ottenere il valore dell'attributo velocita
    */
    public float getVelocita() {
        return velocita;
    }

    /**
     Il metodo permette di ottenere il valore dell'attributo altezzaLatoMaggiore
    */
    public float getAltezzaLatoMaggiore() {
        return altezzaLatoMaggiore;
    }
    
    /**
     Il metodo permette di ottenere il valore dell'attributo altezzaLatoMinore
    */
    public float getAltezzaLatoMinore() {
        return altezzaLatoMinore;
    }
    
    /**
     Il metodo permette di ottenere il toString della classe
    */
    public String toString(){
        return "Lato maggiore: "+altezzaLatoMaggiore+"\nLato minore: "+altezzaLatoMinore+"\nQuantita' acqua: "+altezzaLatoMinore+"\nMovimentoX: "+movimentoX+"\n---------";
    }
}
