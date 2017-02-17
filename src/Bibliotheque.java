import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


// Classe de gestion de la Bibliotheque

public class Bibliotheque implements Serializable 
{
	
	private static final long serialVersionUID = 262L;

	// -----------------------------------------------
        //Attributs
	// -----------------------------------------------
	
		private HashMap<Integer, Lecteur> _dicoLecteur;
		private HashMap<String, Ouvrage> _dicoOuvrage;
		private ArrayList<Emprunt> _historiqueEmprunts;
                		
		/*
		 * Le dictionnaire de lecteur permet à bibliotheque de 
		 * garantir l'unicité de ces derniers, et facilitent les recherches et créations.
		 */
	
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
	

		public Bibliotheque() {
			this.setLecteurs(new HashMap<Integer, Lecteur>());
			this.setOuvrages(new HashMap<String, Ouvrage>());
			this.setEmprunts(new ArrayList<Emprunt>());
		}
	
// -----------------------------------------------
	// Public
// -----------------------------------------------	
		
		// -----------------------------------------------
			// Mï¿½thodes
		// -----------------------------------------------
	
		/*
		 * La méthode nouveauLecteur permet de créé un lecteur en demandant la saisie de son numéro
		 * nom, prénom, date de naissance, adresse et numéro de téléphone.
		 * L'age doit être compris entre 3 et 110 ans
		 * Le lecteur est identifié par son numéro, si celui ci existe déjà dans le dictionnaire
		 * de bibliothèque, un message d'erreur est affiché.
		 * Une fois le nouveau lecteur créé, il est ajouté au dictionnaire de lecteur
		 * afin de garantir la cohérence des données.
		 */
	public void nouveauLecteur()
	{   int numLecteur = PropNumLecteur();

		EntreesSorties.afficherMessage("Le lecteur aura pour numéro : "+numLecteur);
		
		Lecteur L = unLecteur(numLecteur);
		
		if (L == null) 
		{
			String nom = EntreesSorties.lireChaine("Entrez le nom :");
			String prenom = EntreesSorties.lireChaine("Entrez le prenom :");
			Integer age;
			GregorianCalendar dateNaiss, dateNaissComp;
			GregorianCalendar dateActuelle = new GregorianCalendar();
			do {
				dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur :");
				dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
				if(dateNaissComp.before(dateActuelle)){
					age=dateActuelle.get(GregorianCalendar.YEAR)-dateNaiss.get(GregorianCalendar.YEAR);
				}
				else{
					age=dateActuelle.get(GregorianCalendar.YEAR)-dateNaiss.get(GregorianCalendar.YEAR)-1;
				}
				if ((age<=3) | (age>=110)){
					EntreesSorties.afficherMessage("Age incorrecte ("+age+"), veuillez recommencer.");
				}
				else {
					EntreesSorties.afficherMessage("Age du lecteur : " + age + " ans");
				}
			} while ((age<=3) | (age>=110));
			String adresse = EntreesSorties.lireChaine("Entrez l'adresse :");
			String tel = EntreesSorties.lireChaine("Entrez le numero de telephone :");
                        while (tel.length() != 10){
                            EntreesSorties.afficherMessage("Le numéro de téléphone est erroné. Il doit contenir 10 caratères. Merci de le ressaisir :");
                            tel = EntreesSorties.lireChaine();
                        }
			EntreesSorties.afficherMessage("Fin de saisie");
			
			L = new Lecteur(nom, prenom, numLecteur, dateNaiss, adresse, tel);
			lierLecteur(L, numLecteur);
		}
		else {
			EntreesSorties.afficherMessage("Ce numero de lecteur existe deja.");
		}
		
	}

	public void nouvelOuvrage()
	{
		String numISBN = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage : ");
                
                Ouvrage O = getOuvrage(numISBN);
                
                EntreesSorties.afficherMessage(numISBN);

		if (O == null)
		{
			String titreOuvrage = EntreesSorties.lireChaine("Entrez le titre de l'ouvrage :");
			String nomAuteur = EntreesSorties.lireChaine("Entrez le nom de l'auteur :");
			String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur :");
			GregorianCalendar dateParution = EntreesSorties.lireDate("Entrez la date de parution :");
			String publicCible;
			do {
				publicCible = EntreesSorties.lireChaine("Entrez le public cible (Enfant - Ado - Adulte):");
			} while (publicCible.toLowerCase().compareTo("enfant") != 0 && publicCible.toLowerCase().compareTo("ado") != 0 && publicCible.toLowerCase().compareTo("adulte") != 0);
			EntreesSorties.afficherMessage("Fin de saisie");

			O = new Ouvrage(numISBN,titreOuvrage,nomEditeur,nomAuteur,dateParution,publicCible);
			lierOuvrage(O, numISBN);
		}
		else {
			EntreesSorties.afficherMessage("Cet ouvrage existe déjà.");
		}

	}

	public void nouvelExemplaire()
	{
		String numISBN = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage : ");

		Ouvrage O = getOuvrage(numISBN);
		Exemplaire E;
                int numExemplaire;

		if (O != null)
		{
			numExemplaire = O.PropNumExemplaire();
                        EntreesSorties.afficherMessage("L'exemplaire aura pour numéro : "+numExemplaire);
                        GregorianCalendar dateReception;
                        do{
                            dateReception = EntreesSorties.lireDate("Entrez la date de reception, celle-ci doit être supérieure à la date de parution ("+O.getDateParution().get(GregorianCalendar.DAY_OF_MONTH)+"/"+(O.getDateParution().get(GregorianCalendar.MONTH)+1)+"/"+O.getDateParution().get(GregorianCalendar.YEAR)+") :");
                        }while(O.getDateParution().after(dateReception));
			Boolean empruntable = true;
			String emp;
			do {
				emp = EntreesSorties.lireChaine("Le livre est-il empruntable ? (y/n) :");
				switch (emp){
				case "y":
					empruntable = true;
					break;
				case "n":
					empruntable = false;
					break;
				default:                                        
					emp = "error";
					break;
                                }
			} while (emp.compareToIgnoreCase("error") == 0);
			EntreesSorties.afficherMessage("Fin de saisie");

			E = new Exemplaire(numISBN, numExemplaire, dateReception, empruntable);
			O.lierExemplaire(E);
		}
		else {
			EntreesSorties.afficherMessage("Cet ouvrage n'existe pas, il faut le créer avant de pouvoir ajouter un exemplaire.");
		}

	}
	
	
	/*
	 * La méthode consulterLecteur permet d'afficher l'ensemble des informations relatives à
	 * un lecteur, par la saisie de son identifiant (numéro de lecteur).
	 * Si le numéro de lecteur n'est pas dans la base de données de bibliotheque un message d'erreur est
	 * renvoyé a l'utilisateur.
	 */
	public void consulterLecteur()
	{
		Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");
		
		Lecteur L = unLecteur(numLecteur);
		
		if (L!=null){
			L.afficherLecteur();
		}
		else {
			EntreesSorties.afficherMessage("Aucun lecteur n'est associe a ce numero.");
		}
	}
        
        public void consulterOuvrage(){
                String numOuvrage = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'ouvrage à consulter : ");
                
                Ouvrage O = getOuvrage(numOuvrage);
                if (O !=null){
                        O.afficheInfosOuvrage();
                }
                else {
                        EntreesSorties.afficherMessage("Aucun ouvrage n'est associé à ce numéro ISBN.");
                }
        }
        
        public void consulterExemplaire(){
            String numOuvrage = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'ouvrage à consulter : ");
            
            Ouvrage O = getOuvrage(numOuvrage);
            if (O != null){
            
                O.afficheExemplaires();
            }
            
            else {
                EntreesSorties.afficherMessage("Ce numéro d'ISBN ne correspond à aucun ouvrage. Il faut d'abord l'enregistrer.");
            }
        }
        
        public void consulterEmpruntsLecteur(){
            int numLecteur = EntreesSorties.lireEntier("Entrez le numéro de lecteur dont vous voulez vérifier les emprunts en cours : ");
            Lecteur l = unLecteur(numLecteur);
            if (l != null){
                l.afficheInfosEmprunts();
            }
            else{
                EntreesSorties.afficherMessage("Ce numéro de lecteur n'est pas attribué.");
            }
        }



        public void relancerLecteur() {
            int compteur = afficherRetardataires();
            System.out.println("Fin d'affichage des "+compteur+" emprunts en retard.");
        }
	
// -----------------------------------------------
	// Private
// -----------------------------------------------
	
	// -----------------------------------------------
		// Setters
	// -----------------------------------------------
	
	private void setLecteurs(HashMap<Integer, Lecteur> dicoLecteur) {
		_dicoLecteur = dicoLecteur;
	}
        
        private void setOuvrages(HashMap<String, Ouvrage> dicoOuvrage) {
		_dicoOuvrage = dicoOuvrage;
	}

	private void setEmprunts(ArrayList<Emprunt> histoEmprunts) { _historiqueEmprunts = histoEmprunts; }
        
	
	
	// -----------------------------------------------
		// Mï¿½thodes
	// -----------------------------------------------
	
	/*
	 * La méthode unLecteur permet de rechercher dans la base de donnée de bibliotheque un objet 
	 * lecteur identifié par son numéro, et de renvoyer l'objet. (ou la donnée null s'il n'est pas trouvé)
	 */
	private Lecteur unLecteur(Integer numLecteur)
	{
		return _dicoLecteur.get(numLecteur);
	}
        
	private Ouvrage getOuvrage(String numISBN){
		return _dicoOuvrage.get(numISBN);
	}
        
	
	/*
	 * La méthode lierLecteur permet d'ajouter un lecteur a la base de donnée de bibliotheque.
	 */
	private void lierLecteur(Lecteur L, Integer numLecteur)
	{
		_dicoLecteur.put(numLecteur, L);
	}

	private void lierOuvrage(Ouvrage O, String numISBN) { _dicoOuvrage.put(numISBN, O); }

	
	
	/*
	 * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans le but de les parcourir
	 * pour eventuellement les relancer.
	 */
	private Iterator<Lecteur> lesLecteurs() {
		return _dicoLecteur.values().iterator();
	}
        
        private Iterator<Ouvrage> lesOuvrages() {
		return _dicoOuvrage.values().iterator();
	}
        
       
        /*
         * Propose le premier numéro de lecteur disponible
         */        
        private int PropNumLecteur(){
            int numProp = 0;
            Lecteur L;
            do {
                numProp ++;
                L = unLecteur(numProp);
            }while (L != null);
            
            return numProp;
        }

        private int afficherRetardataires() {
            int compteur;
            GregorianCalendar dateActuelle;
            dateActuelle.setTime(new Date());
            for (Emprunt e : _historiqueEmprunts) {
                GregorianCalendar dateRetourPrevueProvisoire = e.getDateRetourPrevue();
                dateRetourPrevueProvisoire.add(GregorianCalendar.DAY_OF_MONTH, 8);
                if (e.getDateRetour() == null && !(dateRetourPrevueProvisoire.before(dateActuelle))) {
                    e.afficheInfos();
                    compteur++;
                }
            }
            return compteur;
        }
        
        
}