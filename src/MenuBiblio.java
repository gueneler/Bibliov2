
public class MenuBiblio {
	private Bibliotheque _bibliotheque;
	
	public MenuBiblio (Bibliotheque bibliotheque) {
	_bibliotheque = bibliotheque;
	}
	
	/*
	 * menuPrincipal permet � l'utilisateur de selectionner un type de sous menu (Lecteur, Ouvrage ou Exemplaire) 
	 * o� il effectuera par la suite l'action d�sir�e. Si l'utilisateur a fini d'utiliuser le programme, il choisit l'option Quitter.
	*/
public void menuPrincipal() {
	Integer menu;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("|                   Menu Principal                       |");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Menu Lecteur : 1                                       |");
                EntreesSorties.afficherMessage("| Menu Ouvrage : 2                                       |");
                EntreesSorties.afficherMessage("| Menu Exemplaire : 3                                    |");
                EntreesSorties.afficherMessage("| Afficher retardataires : 4                             |");
                EntreesSorties.afficherMessage("| Stats : 5                                              |");
		EntreesSorties.afficherMessage("| Quitter : 0                                            |");
		EntreesSorties.afficherMessage(" ========================================================");
		menu = EntreesSorties.lireEntier();
			
			switch (menu){
				case 1 : {
					this.menuLecteur();
					break;
				}
                                
                                case 2 : {
                                        this.menuOuvrage();
                                        break;
                                }
                                
                                case 3 : {
                                        this.menuExemplaire();
                                        break;
                                }

                                case 4 : {
                                        _bibliotheque.relancerLecteur();
                                        break;
                                }
                                
                                case 5 : {
                                        this.menuStats();
                                        break;
                                }
				
				default : {
					break;
				}
			}
	} while (menu != 0);	
}

	/* menuLect permet d'effectuer une s�rie d'action concernant les utilisateur (lecteurs) de la biblioth�que.
	 * Une fois une action effectu�e, l'utilisateur sera rediriger vers ce m�me menu afin de pouvoir selectionner
	 * une nouvelle fois une action concernant les lecteurs.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
	*/
public void menuLecteur() {
	Integer menuLect;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouveau Lecteur : 1                                    |");
		EntreesSorties.afficherMessage("| Consulter Lecteur : 2                                  |");
                EntreesSorties.afficherMessage("| Consulter emprunts en cours d'un lecteur : 3           |");
		EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuLect = EntreesSorties.lireEntier();
			
			switch (menuLect){
				case 1 : {
					_bibliotheque.nouveauLecteur();
					break;
				}
				case 2 : {
					_bibliotheque.consulterLecteur();
					break;
				}
                                
                                case 3 : {
					_bibliotheque.consulterEmpruntsLecteur();
					break;
				}
				default : {
					break;
				}
			}
	} while (menuLect != 0);	
}

public void menuOuvrage() {
	Integer menuOuv;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvel Ouvrage : 1                                     |");
		EntreesSorties.afficherMessage("| Consulter Ouvrage : 2                                  |");
		EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuOuv = EntreesSorties.lireEntier();
			
			switch (menuOuv){
				case 1 : {
					_bibliotheque.nouvelOuvrage();
					break;
				}
				case 2 : {
					_bibliotheque.consulterOuvrage();
					break;
				}
				default : {
					break;
				}
			}
	} while (menuOuv != 0);	
}

public void menuExemplaire() {
	Integer menuEx;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvel Exemplaire : 1                                  |");
		EntreesSorties.afficherMessage("| Consulter Exemplaire : 2                               |");
                EntreesSorties.afficherMessage("| Emprunter Exemplaire : 3                               |");
                EntreesSorties.afficherMessage("| Rendre Exemplaire : 4                                  |");
		EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuEx = EntreesSorties.lireEntier();
			
			switch (menuEx){
				case 1 : {
					_bibliotheque.nouvelExemplaire();
					break;
				}
				case 2 : {
					_bibliotheque.consulterExemplaire();
					break;
				}
                                
                                case 3 : {
					_bibliotheque.emprunterExemplaire();
					break;
				}
                                
                                case 4 : {
					_bibliotheque.rendreExemplaire();
					break;
				}
				default : {
					break;
				}
			}
	} while (menuEx != 0);	
}

    public void menuStats() {
            Integer menuStat;
            do {
                    EntreesSorties.afficherMessage(" ========================================================");
                    EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
                    EntreesSorties.afficherMessage("| Historique d'emprunts d'un lecteur : 1                 |");
                    EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
                    EntreesSorties.afficherMessage(" ========================================================");
                    menuStat = EntreesSorties.lireEntier();
                    
                    switch (menuStat){
				case 1 : {
					_bibliotheque.histoLecteurs();
					break;
				}
                                
                                case 1 : {
					_bibliotheque.histoOuvrage();
					break;
				}
                                
				default : {
					break;
				}
			}
	} while (menuStat != 0);
         
    }
}

