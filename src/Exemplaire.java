
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

    /**
     * @return the numExemplaire
     */
    public int getNumExemplaire() {
        return numExemplaire;
    }

    /**
     * @param numExemplaire the numExemplaire to set
     */
    private void setNumExemplaire(int numExemplaire) {
        this.numExemplaire = numExemplaire;
    }

    /**
     * @return the dateReception
     */
    public GregorianCalendar getDateReception() {
        return dateReception;
    }

    /**
     * @param dateReception the dateReception to set
     */
    private void setDateReception(GregorianCalendar dateReception) {
        this.dateReception = dateReception;
    }

    /**
     * @return the empruntable
     */
    public boolean isEmpruntable() {
        return empruntable;
    }

    /**
     * @param empruntable the empruntable to set
     */
    private void setEmpruntable(boolean empruntable) {
        this.empruntable = empruntable;
    }

    /**
     * @param numISBN the numISBN to set
     */
    private void setNumISBN(String numISBN) {
        this.numISBN = numISBN;
    }
    
    public void setEtatEmprunté(){
        this.emprunté = true;
    }
    
    public void setEtatDispo(){
        this.emprunté = false;
    }
    
    
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
        EntreesSorties.afficherMessage("Date de réception : ");
        EntreesSorties.ecrireDate(dateReception);
        EntreesSorties.afficherMessage("Empruntable : "+isEmpruntable());
    }    

    /**
     * @return the numISBN
     */
    public String getNumISBN() {
        return numISBN;
    }
}
