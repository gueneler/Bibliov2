import java.util.GregorianCalendar;


//Classe de gestion d'Emprunt

public class Emprunt {
    
   //attributs
    
    private GregorianCalendar dateEmprunt;
    private GregorianCalendar dateRetour;
    private GregorianCalendar dateRetourPrevue;
    private Ouvrage ouvrage;
    private Lecteur lecteur;
    private Exemplaire exemplaire;
    
   //constructeur
    
    public Emprunt(Ouvrage o, Lecteur l, Exemplaire e){
        
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

    
    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    
    public Lecteur getLecteur() {
        return lecteur;
    }

    
    public void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }

    
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
    
   //méthodes privées

    

}