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
        this.setDateRetour(null);
        dateRetourPrevue.setTime(new Date());
        dateRetourPrevue.add((GregorianCalendar.DAY_OF_MONTH), 8);
        this.setOuvrage(o);
        this.setLecteur(l);
        this.setExemplaire(e);
    }
    
   //méthodes publiques

    public GregorianCalendar getDateRetour() {
        return dateRetour;
    }

    
    public void setDateRetour(GregorianCalendar dateRetour) {
        this.dateRetour = dateRetour;
    }

    
    public GregorianCalendar getDateRetourPrevue() {
        return dateRetourPrevue;
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

    //Affiche les infos relatives à un emprunt.
    public void afficheInfos(){
        EntreesSorties.afficherMessage("Titre de l'ouvrage : "+getOuvrage().getTitreOuvrage());
        EntreesSorties.afficherMessage("Numéro ISBN : "+getOuvrage().getNumISBN());
        EntreesSorties.afficherMessage("Numéro de l'exemplaire : "+getExemplaire().getNumExemplaire());
        EntreesSorties.afficherMessage("Date d'emprunt : "+ EntreesSorties.ecrireDate(dateEmprunt));
        EntreesSorties.afficherMessage("Date de retour prévu : "+ EntreesSorties.ecrireDate(dateRetourPrevue));
        EntreesSorties.afficherMessage("Date de retour effectif : "+ EntreesSorties.ecrireDate(dateRetour));
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
