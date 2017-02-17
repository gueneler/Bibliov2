import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;


//Classe de gestion d'Emprunt

public class Emprunt implements Serializable  {
    
   //attributs
    
    private GregorianCalendar dateEmprunt;
    private GregorianCalendar dateRetour;
    private GregorianCalendar dateRetourPrevue;
    private Ouvrage ouvrage;
    private Lecteur lecteur;
    private Exemplaire exemplaire;
    
   //constructeur
    
    public Emprunt(Ouvrage o, Lecteur l, Exemplaire e){
        dateEmprunt = new GregorianCalendar();
        dateRetour = new GregorianCalendar();
        dateRetourPrevue = new GregorianCalendar();
        dateEmprunt.setTime(new Date());
        dateRetour = null;
        dateRetourPrevue = dateEmprunt;
        dateRetourPrevue.add((GregorianCalendar.DAY_OF_MONTH), 8);
        setOuvrage(o);
        setLecteur(l);
        setExemplaire(e);
    }
    
   //méthodes publiques
    
    public GregorianCalendar getDateEmprunt() {
        return dateEmprunt;
    }

    
    public void setDateEmprunt(GregorianCalendar dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    
    public GregorianCalendar getDateRetour() {
        return dateRetour;
    }

    
    public void setDateRetour(GregorianCalendar dateRetour) {
        this.dateRetour = dateRetour;
    }

    
    public GregorianCalendar getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    
    public void setDateRetourPrevue(GregorianCalendar dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    
    public Ouvrage getOuvrage() {
        return ouvrage;
    }

        
    public Lecteur getLecteur() {
        return lecteur;
    }
   
        
    public Exemplaire getExemplaire() {
        return exemplaire;
    }
       
   //méthodes privées
    
    private void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    private void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }
    
    private void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

}
