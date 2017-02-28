
import java.io.Serializable;
import java.util.GregorianCalendar;


public class Exemplaire implements Serializable {
    
    
    //attributs
    
    private int numExemplaire;
    private GregorianCalendar dateReception;
    private boolean empruntable;
    private boolean emprunté;
    private String numISBN;
    
    //constructeur
    
    public Exemplaire(String numISBN, int numExemplaire, GregorianCalendar dateReception, Boolean empruntable){
        this.setNumISBN(numISBN);
        this.setNumExemplaire(numExemplaire);
        this.setDateReception(dateReception);
        this.setEmpruntable(empruntable);
        this.emprunté = false;
    }
    
    //methodes

    public int getNumExemplaire() {
        return numExemplaire;
    }

    private void setNumExemplaire(int numExemplaire) {
        this.numExemplaire = numExemplaire;
    }
    
    public GregorianCalendar getDateReception() {
        return dateReception;
    }
    
    private void setDateReception(GregorianCalendar dateReception) {
        this.dateReception = dateReception;
    }
    
    public boolean isEmpruntable() {
        return empruntable;
    }
    
    private void setEmpruntable(boolean empruntable) {
        this.empruntable = empruntable;
    }
    
    public String getNumISBN() {
        return numISBN;
    }
    
    private void setNumISBN(String numISBN) {
        this.numISBN = numISBN;
    }
    
    /* permet de passer l'exemplaire à l'état emprunté*/
    public void setEtatEmprunté(){
        this.emprunté = true;
    }
    
    /* permet de passer l'exemplaire à l'état disponible*/
    public void setEtatDispo(){
        this.emprunté = false;
    }
    
    /* vérifie l'état de l'exemplaire : emprunté, disponible, non empruntable*/
    public String verifEtat(){
        String etat;
        
        if(this.emprunté == true){
            etat = "exemplaire emprunté";
        }
        else if(this.empruntable == false){
            etat = "exemplaire non empruntable";
        }
        else {
            etat = "disponible";
        }
        
        return etat;
    }
    
    public void afficheInfosExemplaire(){
        EntreesSorties.afficherMessage("Numéro ISBN : "+getNumISBN());
        EntreesSorties.afficherMessage("Numéro d'exemplaire : "+getNumExemplaire());
        EntreesSorties.afficherMessage("Date de réception : "+EntreesSorties.ecrireDate(dateReception));
        EntreesSorties.afficherMessage("Empruntable : "+isEmpruntable());
        EntreesSorties.afficherMessage("Etat : "+verifEtat());
    }    


   
}
