import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    ArrayList<Utilisateur> listeUtilisateurs;
    ArrayList<Livre> listeLivres;
    HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;


// Constructeur pour initialiser les attributs
Bibliotheque() {
    this.listeUtilisateurs = new ArrayList<>();
    this.listeLivres = new ArrayList<>();
    this.empruntsUtilisateurs = new HashMap<>();
}
public ArrayList<Livre> getListeLivres() {
    return listeLivres;
}

    // Méthode pour ajouter un utilisateur avec la méthode add de "ArrayList" 
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        listeUtilisateurs.add(utilisateur);
    }

    // Méthode pour ajouter un livre à la bibliothèque avec la méthode add de "ArrayList"
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    // Méthode pour supprimer un livre de la bibliothèque
    // On déclare d'abord une variable de type livre qu'on va initialiser à null puis on parcoure la liste des livres en comparant chaque titre de livre avec le titre donné en paramètre. Si ils sont égaux alors on affecte ce livre a la variable livre à supprimer
    public void supprimerLivre(String titre) {
        Livre livreASupprimer = null;
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equals(titre)) {
                livreASupprimer = livre;
                break;
            }
        // Ensuite on vérifie si le livre existe ou pas dans la bibliothèque avec un if - else
        }
        if (livreASupprimer != null) {
            listeLivres.remove(livreASupprimer);
            System.out.println("Le livre " + titre + " a été supprimé de la bibliothèque.");
        } else {
            System.out.println("Le livre " + titre + " n'a pas été trouvé dans la bibliothèque.");
        }
    }

    // Méthode pour rechercher par Titre
    // On déclare une variable resultats qui est une liste de livres qui sert à stocker les livres trouvés
    // Pour trouver les livres recherchés, on utilise la boucle for pour parcourir la liste des livres et on compare chaque titre de livre au titre donné en paramètre et si c'est égal on ajoute le livre a la liste de livre "resultats".
    public ArrayList<Livre> rechercherParTitre(String recherche) {
        ArrayList<Livre> resultats = new ArrayList<>();
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equals(recherche)) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    // Méthode pour rechercher un livre par Auteur
    // On fait la même chose pour rechercher auteur sauf que là on compare les auteurs et on donne le nom d'un auteur en parametre
    public ArrayList<Livre> rechercherParAuteur(String recherche) {
        ArrayList<Livre> resultats = new ArrayList<>();
        for (Livre livre : listeLivres) {
            if (livre.getAuteur().equals(recherche)) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    // Méthode pour rechercher un livre par ISBN
    // On fait aussi la même chose 
    public ArrayList<Livre> rechercherParISBN(String recherche) {
        ArrayList<Livre> resultats = new ArrayList<>();
        for (Livre livre : listeLivres) {
            if (livre.getIsbn().equals(recherche)) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    // Méthode pour enregistrer les emprunts des utilisateurs
    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        // Vérifier si l'utilisateur existe dans la bibliothèque
        // On déclare d'abord un boolean pour savoir si un utilisateur existe qu'on initialise a false puis on parcoure la liste des utilisateurs avec la boucle for en comparant le numéro d'identification des utilisateurs avec celui donné en paramètre. Si oui on change la variable déclaré en "true"
        boolean utilisateurExiste = false;
        for (Utilisateur u : listeUtilisateurs) {
            if (u.getNumeroIdentification() == utilisateur.getNumeroIdentification()) {
                utilisateurExiste = true;
                break;
            }
        }
        if (!utilisateurExiste) {
            System.out.println("L'utilisateur \"" + utilisateur.getNom() + "\" n'existe pas dans la bibliothèque.");
            return;
        }
        
        // On vérifie si le livre existe dans la bibliothèque
        if (listeLivres.stream().anyMatch(l -> l.getTitre().equalsIgnoreCase(livre.getTitre()))) {
            if (estEligibleEmprunt(utilisateur)) {
                // Vérifier si le livre est déjà emprunté par l'utilisateur
                if (utilisateur.livresEmpruntes.stream().noneMatch(l -> l.getTitre().equalsIgnoreCase(livre.getTitre()))) {
                    if (!empruntsUtilisateurs.containsKey(utilisateur)) {
                        empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
                    }
                    if (utilisateur.getLivresEmpruntes().size() < Utilisateur.getMaxLivresEmpruntes()) {
                        utilisateur.emprunterLivre(livre, this);
                        empruntsUtilisateurs.get(utilisateur).add(livre);
                        System.out.println("L'emprunt du livre \"" + livre.getTitre() + "\" a été enregistré pour " + utilisateur.getNom() + ".");
                    } else {
                        System.out.println("L'utilisateur a déjà atteint le maximum de livres empruntés simultanément.");
                    }
                } else {
                    System.out.println("Vous avez déjà emprunté le livre \"" + livre.getTitre() + "\".");
                }
            } else {
                System.out.println("L'utilisateur n'est pas éligible à emprunter des livres.");
            }
        } else {
            System.out.println("Le livre \"" + livre.getTitre() + "\" n'existe pas dans la bibliothèque.");
        }
    }
    
    
    
    
    
    


    // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
    public boolean estEligibleEmprunt(Utilisateur utilisateur) {
        return utilisateur.cotisationAJour;
    }

    // Méthode pour afficher les statistiques de la bibliotheque
    public void afficherStatistiques() {
        int nbreUtilisateurs = listeUtilisateurs.size(); // nombre total d'utilisateurs
        int nbreLivres = listeLivres.size(); // Nombre total de livres
        int nbreEmprunts = 0; // On initialise les emprunts à zéro
        // On calcule le nombre d'emprunts
        for (ArrayList<Livre> livresEmpruntes : empruntsUtilisateurs.values()) {
            nbreEmprunts += livresEmpruntes.size();
        }

        // On affiche les statistiques
        System.out.println("---------------------------------------------");
        System.out.println("|     Statistiques de la bibliothèque :     |");
        System.out.println("---------------------------------------------");
        System.out.println("|- Nombre total d'utilisateurs : "+ nbreUtilisateurs+ "          |");
        System.out.println("|- Nombre total de livres : " + nbreLivres+"               |");
        System.out.println("|- Nombre total d'exemplaires empruntés : " + nbreEmprunts+" |");
        System.out.println("---------------------------------------------");

    }
    public Utilisateur rechercherUtilisateur(String nom, int numeroIdentification) {
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getNom().equalsIgnoreCase(nom) && utilisateur.getNumeroIdentification() == numeroIdentification) {
                return utilisateur;
            }
        }
        return null; // Retourner null si l'utilisateur n'est pas trouvé
    }


    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        // Vérifier si le livre existe dans la bibliothèque
        boolean livreExiste = listeLivres.stream().anyMatch(l -> l.getTitre().equalsIgnoreCase(livre.getTitre()));
        
        if (livreExiste) {
            // Vérifier si l'utilisateur est éligible à emprunter des livres
            if (estEligibleEmprunt(utilisateur)) {
                // Vérifier si l'utilisateur a emprunté le livre
                boolean livreEmprunte = utilisateur.getLivresEmpruntes().stream()
                        .anyMatch(l -> l.getTitre().equalsIgnoreCase(livre.getTitre()));
    
                if (livreEmprunte) {
                    // Retirer le livre de la liste des emprunts de l'utilisateur
                    utilisateur.retournerLivre(livre);
                    // Retirer le livre de la liste des emprunts de la bibliothèque
                    empruntsUtilisateurs.get(utilisateur).remove(livre);
                    // Afficher un message de confirmation
                    System.out.println("Le livre \"" + livre.getTitre() + "\" a été retourné par " + utilisateur.getNom() + ".");
                } else {
                    System.out.println("L'utilisateur n'a pas emprunté le livre \"" + livre.getTitre() + "\".");
                }
            } else {
                System.out.println("L'utilisateur n'est pas éligible à emprunter des livres.");
            }
        } else {
            System.out.println("Le livre \"" + livre.getTitre() + "\" n'existe pas dans la bibliothèque.");
        }
    }
    
    
    
    

}
