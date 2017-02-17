
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
//test
public class Ouvrage implements Serializable  {

    private String numISBN;
    private String titreOuvrage;
    private String nomEditeur;
    private String nomAuteur;
    private GregorianCalendar dateParution;
    private String publicCible;
    private HashMap<Integer, Exemplaire> _dicoExemplaire;

    Ouvrage(String numISBN, String titreOuvrage, String nomEditeur, String nomAuteur, GregorianCalendar dateParution, String publicCible) {
        this.setNumISBN(numISBN);
        this.setTitreOuvrage(titreOuvrage);
        this.setNomEditeur(nomEditeur);
        this.setNomAuteur(nomAuteur);
        this.setDateParution(dateParution);
        this.setPublicCible(publicCible);
        this.setExemplaires(new HashMap<Integer, Exemplaire>());

    }

    private void setNumISBN(String numISBN) {
        this.numISBN = numISBN;
    }

    public String getNumISBN() {
        return numISBN;
    }

    private void setTitreOuvrage(String titreOuvrage) {
        this.titreOuvrage = titreOuvrage;
    }

    public String getTitreOuvrage() {
        return titreOuvrage;
    }

    private void setNomEditeur(String nomEditeur) {
        this.nomEditeur = nomEditeur;
    }

    public String getNomEditeur() {
        return nomEditeur;
    }

    private Exemplaire getExemplaire(Integer numExemplaire){
        return _dicoExemplaire.get(numExemplaire);
    }

    private void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    private void setDateParution(GregorianCalendar dateParution) {
        this.dateParution = dateParution;
    }

    public GregorianCalendar getDateParution() {
        return dateParution;
    }

    private void setPublicCible(String publicCible) {
        this.publicCible = publicCible;
    }

    public String getPublicCible() {
        return publicCible;
    }

    public HashMap<Integer, Exemplaire> getExemplaires(){
        return _dicoExemplaire;
    }

    private void setExemplaires(HashMap<Integer, Exemplaire> dicoExemplaire) {
        _dicoExemplaire = dicoExemplaire;
    }

    public void afficheInfosOuvrage() {
        System.out.println("Numéro ISBN :"+getNumISBN());
        System.out.println("Titre : "+getTitreOuvrage());
        System.out.println("Nom de l'auteur : "+getNomAuteur());
        System.out.println("Nom de l'éditeur : "+getNomEditeur());
        System.out.println("Date de parution : "+dateParution.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(dateParution.get(GregorianCalendar.MONTH)+1)+"/"+dateParution.get(GregorianCalendar.YEAR));
        System.out.println("Public cible : "+getPublicCible());
        System.out.println("Nombre d'exemplaires : "+_dicoExemplaire.size());
    }
    
         /*
         * Propose le premier numéro d'exemplaire disponible
         */
        public int PropNumExemplaire(){
            int numProp = 0;
            Exemplaire E;
            do {
                numProp ++;
                E = getExemplaire(numProp);
            }while (E != null);
            
            return numProp;
        }
        
        public void lierExemplaire(Exemplaire E){
            _dicoExemplaire.put(E.getNumExemplaire(),E);
        }
    
    public void afficheExemplaires(){
       
      Set<Map.Entry<Integer, Exemplaire>> set = _dicoExemplaire.entrySet();
      
      EntreesSorties.afficherMessage("Numéro ISBN : "+getNumISBN());
      EntreesSorties.afficherMessage("Titre de l'ouvrage : "+getTitreOuvrage());
      EntreesSorties.afficherMessage("========================================================");
      for (Map.Entry<Integer, Exemplaire> ex : set) {
        GregorianCalendar date = ex.getValue().getDateReception();
        System.out.print("Numéro exemplaire : " + ex.getKey() +"\nDate de réception : "+ date.get(GregorianCalendar.DAY_OF_MONTH) + "/" +date.get(GregorianCalendar.MONTH)+1 + "/" +date.get(GregorianCalendar.YEAR) +"\nEmpruntable : " + ex.getValue().isEmpruntable()+"\n\n");
      }
                   
    }
    
    public boolean verifPublic(String publicAVerifier){
        return publicAVerifier.equalsIgnoreCase(this.publicCible);
    }
    
    
}
