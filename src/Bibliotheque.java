import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


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
                        
                        EntreesSorties.afficherMessage("========================================================");
                        EntreesSorties.afficherMessage("Voci les informations du lecteur créé : ");
                        L.afficherLecteur();
		}
		else {
			EntreesSorties.afficherMessage("Ce numero de lecteur existe deja.");
		}
		
	}

        // Création d'un ouvrage
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
                       
                       EntreesSorties.afficherMessage("========================================================");
                       EntreesSorties.afficherMessage("Voici les informations du nouvel ouvrage : ");
                       O.afficheInfosOuvrage();
                        
		}
		else {
			EntreesSorties.afficherMessage("Cet ouvrage existe déjà.");
		}

	}

        // Création d'un exemplaire pour un ouvrage donné
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
                        
                        EntreesSorties.afficherMessage("========================================================");
                        EntreesSorties.afficherMessage("Voci les informations du nouvel exemplaire : ");
                        E.afficheInfosExemplaire();
                        
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
        
        // Affiche toutes les infos d'un ouvrage donné (numISBN, titre, nom de l'auteur, de l'éditeur, date de parution, le public cible et le nombre d'exemplaires)
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
        
        //Affiche le numéro ISBN, le titre, et les infos de chaque exemplaires d'un ouvrrage donné
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
        
        //Affiche les emprunts en cours pour un lecteur donné
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


        // Affiche les emprunts dont non retournés 15 jours après leur date de retour prévue
        public void relancerLecteur() {
            int compteur = afficherRetardataires();
            System.out.println("Fin d'affichage des "+compteur+" emprunts en retard.");
        }
        
        // Un lecteur emprunte un exemplaire d'un ouvrage
        public void emprunterExemplaire(){
            String numISBN = EntreesSorties.lireChaine("Entrez un numéro ISBN : ");
            int numExemplaire = EntreesSorties.lireEntier("Entrez le numéro d'exemplaire à emprunter : ");
            int numLecteur = EntreesSorties.lireEntier("Entrez le numéro de lecteur : ");
            Ouvrage o = getOuvrage(numISBN);
            Lecteur l = unLecteur(numLecteur);
            if (l == null){
                EntreesSorties.afficherMessage("Le lecteur n'est pas répertorié");
            }
            if (o != null){
                Exemplaire e = o.getExemplaire(numExemplaire);
                if (e != null){
                    String blic = l.getPublic();
                    boolean possible = o.verifPublic(blic);
                    if(possible){
                        String etat = e.verifEtat();
                        int nbEmp = l.verifNombreEmprunts();
                        if(etat == "disponible"){
                            if(nbEmp < 5) {
                            Emprunt emp = new Emprunt(o, l, e);
                            e.setEtatEmprunté();
                            l.lierEmprunt(emp);
                            if(l.verifNombreEmprunts() == 5){
                                l.setEtatSature();
                                EntreesSorties.afficherMessage("Le lecteur a emprunté cinq ouvrages. Le lecteur a maintenant le statut saturé.");
                            }
                            ajouterEmpruntDico(emp);
                            EntreesSorties.afficherMessage("========================================================");
                            EntreesSorties.afficherMessage("Voici les informations du nouvel emprunt : ");
                            EntreesSorties.afficherMessage("========================================================");
                            EntreesSorties.afficherMessage("Ouvrage emprunté :");
                            o.afficheInfosOuvrage();
                            EntreesSorties.afficherMessage("========================================================");
                            EntreesSorties.afficherMessage("Exemplaire emprunté :");
                            e.afficheInfosExemplaire();
                            EntreesSorties.afficherMessage("========================================================");
                            EntreesSorties.afficherMessage("Lecteur emprunteur :");
                            l.afficherLecteur();
                            }
                            else {
                               EntreesSorties.afficherMessage("Le lecteur a déjà emprunté cinq ouvrages. Le lecteur ne peut pas en emprunter plus." ); 
                            }
                            
                        }
                        else if (etat == "exemplaire emprunté") {
                            EntreesSorties.afficherMessage("Ce livre n'est pas disponible.");
                        }
                        else {
                            EntreesSorties.afficherMessage("Ce livre n'est pas empruntable.");
                        }
                    }
                    else {
                        EntreesSorties.afficherMessage("Ce livre n'est pas adapté à votre âge.");
                    }
                }
                else {
                    EntreesSorties.afficherMessage("Cet exemplaire n'est pas répertorié.");
                }
            }
            else {
                EntreesSorties.afficherMessage("L'ouvrage n'est pas répertorié.");
            }
            
    
            
            
        }
        
        // Un lecteur retourne un exemplaire emprunté 
        public void rendreExemplaire(){
            String numISBN = EntreesSorties.lireChaine("Entrez un numéro ISBN : ");
            int numExemplaire = EntreesSorties.lireEntier("Entrez le numéro d'exemplaire rendu : ");
            Ouvrage o = getOuvrage(numISBN);
            if (o != null){
                Exemplaire e = o.getExemplaire(numExemplaire);
                if (e != null){
                    Emprunt emp = getEmprunt(numISBN, numExemplaire);
                    if (emp != null){
                        Lecteur l = emp.getLecteur();
                        String etat = e.verifEtat();
                        if (etat == "exemplaire emprunté"){
                            l.delierEmprunt(emp);
                            GregorianCalendar dateActuelle;
                            dateActuelle = new GregorianCalendar();
                            dateActuelle.setTime(new Date());
                            emp.setDateRetour(dateActuelle);
                            e.setEtatDispo();
                            EntreesSorties.afficherMessage("========================================================");
                            EntreesSorties.afficherMessage("Voici les informations de l'exemplaire rendu : ");
                            e.afficheInfosExemplaire();
                        }
                        else{
                            EntreesSorties.afficherMessage("Aucun emprunt n'est lié à cet exemplaire.");
                        }
                    }
                    else {
                        EntreesSorties.afficherMessage("Aucun emprunt n'est lié à cet exemplaire.");
                    }
                }
                else {
                    EntreesSorties.afficherMessage("Ce numéro ne correspond à aucun exemplaire de cet ouvrage.");
                }
            }
            else {
                EntreesSorties.afficherMessage("Ce numéro d'ISBN n'est pas enregistré");
            }
        }
        
        // Affiche l'historique des emprunts pour un lecteur donné
        public void histoLecteurs(){
            int numLecteur;
            Lecteur l; 
            do{
                numLecteur = EntreesSorties.lireEntier("Entrer le numéro de lecteur dont vous voulez l'historique : ");
                l = unLecteur(numLecteur);
            }while (l == null);
            EntreesSorties.afficherMessage("");
            int compteur = 0;
            for (Emprunt e: _historiqueEmprunts){
                if (e.getLecteur() == l){
                    compteur += 1;
                    e.afficheInfos();
                    EntreesSorties.afficherMessage("\n***************************\n");
                }
            }
            EntreesSorties.afficherMessage("Fin d'affichage des "+compteur+" emprunts de ce lecteur.\n");
        }
        
        // Affiche le nombre de fois qu'un ouvrage donné a été emprunté (tout exemplaire confondu)
        public void histoOuvrage(){
            String numISBN;
            Ouvrage o;
            do{
                numISBN = EntreesSorties.lireChaine("Entrer le numéro ISBN de l'ouvrage dont vous voulez l'historique : ");
                o = getOuvrage(numISBN);
            }while( o == null);
            int compteur = 0;
            for (Emprunt e: _historiqueEmprunts){
                if (e.getOuvrage() == o){
                    compteur +=1;
                }
            }
            EntreesSorties.afficherMessage("Cet ouvrage a été emprunté "+compteur+" fois !\n");
        }
        
        // Affiche l'ouvrage le plus emprunté, et le nombre de fois où celui-ci a été emprunté
        public void topOuvrage(){
            Ouvrage top=null;
            int topActuel = 0;
            Set<Map.Entry<String, Ouvrage>> set = _dicoOuvrage.entrySet();
            for (Map.Entry<String, Ouvrage> o : set){
                int compteur = 0;
                for (Emprunt e: _historiqueEmprunts){
                    if (e.getOuvrage() == o.getValue()){
                        compteur +=1;
                    }
                }
                if(compteur >= topActuel){
                    top = o.getValue();
                    topActuel = compteur;
                }    
                
            }
            
            EntreesSorties.afficherMessage("\nL'ouvrage le plus emprunté est \""+top.getTitreOuvrage()+"\", avec "+topActuel+" emprunts.\n");
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
        
        // Renvoi l'ouvrage correspondant au numISBN donné.
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
        
        // Ajoute l'ouvrage à la base de donnée de bibliotheque
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
         * Propose le premier numéro de lecteur disponible. Ne pas passer par la taille du dictionnaire de lecteur permettra
         * de prendre en compte les éventuelles suppressions de lecteur. 
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

        //Utilisée par relancerLecteurs(). Renvoi le nombre d'emprunts en cours pour lesquels le lecteur est en retard
        private int afficherRetardataires() {
            int compteur = 0;
            GregorianCalendar dateActuelle;
            dateActuelle = new GregorianCalendar();
            dateActuelle.setTime(new Date());
            for (Emprunt e : _historiqueEmprunts) {
                GregorianCalendar dateRetourPrevueProvisoire = (GregorianCalendar) e.getDateRetourPrevue().clone();
                dateRetourPrevueProvisoire.add(GregorianCalendar.DAY_OF_MONTH, 15);
                if (e.getDateRetour() == null && (dateRetourPrevueProvisoire.before(dateActuelle))) {
                    e.afficheInfos();
                    EntreesSorties.afficherMessage("**************************");
                    compteur++;
                }
            }
            return compteur;
        }
        
        // Ajoute l'emprunt au dictionnaire d'emprunts.
        public void ajouterEmpruntDico(Emprunt emp){
            _historiqueEmprunts.add(emp);
        }
        
        // Retourne un emprunt présent dans le dictionnaire d'emprunts, à partir d'un numéro ISBN et d'un numéro d'exemplaire
        private Emprunt getEmprunt(String numISBN, int numExemplaire){
            Emprunt emp = null;
            for (Emprunt e : _historiqueEmprunts){
                if (e.getOuvrage().getNumISBN().equalsIgnoreCase(numISBN) && e.getExemplaire().getNumExemplaire() == numExemplaire && e.getDateRetour() == null){
                    emp = e;
                }
            }
            return emp;
        }
        
        
}
