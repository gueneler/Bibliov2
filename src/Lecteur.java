import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

// Classe de gestion de Lecteur
// test

public class Lecteur implements Serializable 
{
	
	private static final long serialVersionUID = 422L;
	
	// -----------------------------------------------
		//Attributs
	// -----------------------------------------------
	
		private Integer numLecteur;
		private String nomLecteur;
		private String prenomLecteur;
		private String adresseLecteur;
		private String numTelLecteur;
		private GregorianCalendar dateNaiss;
        private ArrayList<Emprunt> empruntsEnCours;
        private Boolean sature;
			
	
	
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
		
		public Lecteur(String nom, String prenom, Integer numLecteur, GregorianCalendar dateNaiss, String adresse, String tel)
		{
			this.setNom(nom);
			this.setPrenom(prenom);
			this.setNumLecteur(numLecteur);
			this.setDateNaiss(dateNaiss);
			this.setAdresse(adresse);
			this.setTel(tel);
            empruntsEnCours = new ArrayList<>();
            sature = false;
		}
		
// -----------------------------------------------
	// Public
// -----------------------------------------------
		
		// -----------------------------------------------
			//Getters
		// -----------------------------------------------
	
		public String getNom() {
			return nomLecteur;
		}

		public String getPrenom() {
			return prenomLecteur;
		}

		public Integer getNumLecteur() {
			return numLecteur;
		}
		
		public GregorianCalendar getDateNaiss() {
			return dateNaiss;
		}

		public String getAdresse() {
			return adresseLecteur;
		}

		public String getTel() {
			return numTelLecteur;
		}
		// -----------------------------------------------
			// Methodes
		// -----------------------------------------------
		
		/*
		 * La méthode afficherLecteur affiche l'ensemble des informations relatives à un lecteur.
		 */
		public void afficherLecteur()
		{
			System.out.println("Numero lecteur : " + this.getNumLecteur());
			System.out.println("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());
			System.out.println("Age : " + this.calculAge() + " ans");
			System.out.println("Adresse : " + this.getAdresse());
			System.out.println("Telephone : " + this.getTel());
			EntreesSorties.afficherMessage("");
		}
		
		
		/*
		 * la méthode calculAge permet de déterminer l'age des lecteurs grace a leur date de naissance
		 * et la date actuelle. De cette façon, il n'y a pas de mise a jour a faire sur l'age des lecteurs.
		 */
		public Integer calculAge() {
			Integer age;
			GregorianCalendar dateNaissComp;
			GregorianCalendar dateActuelle = new GregorianCalendar();
			dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
			if(dateNaissComp.before(dateActuelle)){
				age=dateActuelle.get(GregorianCalendar.YEAR)-dateNaiss.get(GregorianCalendar.YEAR);
			}
			else{
				age=dateActuelle.get(GregorianCalendar.YEAR)-dateNaiss.get(GregorianCalendar.YEAR)-1;
			}
			return age;
		}
                //Affiche les informations sur tous les emprunts en cours du Lecteur
                public void afficheInfosEmprunts(){
                    EntreesSorties.afficherMessage(" ========================================================");
                    for(Emprunt e : empruntsEnCours){
                        e.afficheInfos();
                        EntreesSorties.afficherMessage("");
                    }
                }

                //Renvoie la catégorie de public dans laquelle est le lecteur (Enfant, Ado, Adulte)
                public String getPublic() {
                    int age = calculAge();
                    String blic;
                    if(age < 10){
                        blic = "enfant";
                    }
                    else if(age >= 10 && age <= 16){
                        blic = "adolescent";
                    }
                    else {
                        blic = "adulte";
                    }
                    return blic;
                }

                //Supprime l'emprunt du dictionnaire d'emprunts en cours de ce Lecteur
                public void delierEmprunt(Emprunt emp) {
                	empruntsEnCours.remove(emp);
				}

				//Ajoute l'emprunt au dictionnaire d'emprunts en cours de ce Lecteur
                public void lierEmprunt(Emprunt emp) {
                    this.empruntsEnCours.add(emp);
                }

                //Renvoie le nombre d'emprunts en cours de ce Lecteur
                public int verifNombreEmprunts(){
                    return empruntsEnCours.size();
                }

                //Fait passer le lecteur dans l'état saturé (ne peut plus emprunter d'ouvrages)
                public void setEtatSature(){
                    this.sature = true;
                }
       
		
	
	
// -----------------------------------------------
	// Private
// -----------------------------------------------

		// -----------------------------------------------
			//Setters
		// -----------------------------------------------

		private void setNom(String nom) {
			this.nomLecteur = nom;
		}

		private void setPrenom(String prenom) {
			this.prenomLecteur = prenom;
		}
		
		private void setNumLecteur(Integer numLecteur) {
			this.numLecteur = numLecteur;
		}

		private void setDateNaiss(GregorianCalendar dateNaiss) {
			this.dateNaiss = dateNaiss;
		}

		private void setAdresse(String adresse) {
			this.adresseLecteur = adresse;
		}

		private void setTel(String tel) {
                        this.numTelLecteur = tel;
		}
                
                
		
		
}
