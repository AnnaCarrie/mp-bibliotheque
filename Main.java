import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        Scanner scanner = new Scanner(System.in);
        String choix;

        // On utilise la boucle Do While pour pouvoir répéter les instructions suivantes
        do {
            afficherMenuPrincipal();
            choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    // Ajouter un livre
                    ajouterLivre(scanner, bibliotheque);
                    break;
                case "2":
                    // Ajouter un utilisateur
                    ajouterUtilisateur(scanner, bibliotheque);
                    break;
                case "3":
                    // Supprimer un livre
                    supprimerLivre(scanner, bibliotheque);
                    break;
                case "4":
                    // Rechercher un livre
                    rechercherLivre(scanner, bibliotheque);
                    break;
                case "5":
                    // Emprunter un livre
                    emprunterLivre(scanner, bibliotheque);
                    break;
                case "6":
                    // Retourner un livre
                    retournerLivre(scanner, bibliotheque);
                    break;
                case "7":
                    // Afficher les statistiques
                    bibliotheque.afficherStatistiques();
                    break;
                    case "8":
    // Afficher les livres empruntés par un utilisateur
    afficherLivresEmpruntesUtilisateur(scanner, bibliotheque);
    break;

                case "9":
                    System.out.println("A la prochaine fois !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (!choix.equals("9"));
    }

    // Méthode pour afficher le menu principal 
    private static void afficherMenuPrincipal() {
        System.out.println("===========Bienvenue dans notre bibliothèque !========");
        System.out.println("Sélectionnez une option dans le menu ci dessous : ");
        System.out.println("-------------------------------------------");
        System.out.println("|           MENU PRINCIPAL                |");
        System.out.println("-------------------------------------------");
        System.out.println("|      1. Ajouter un livre                |");
        System.out.println("|      2. Ajouter un utilisateur          |");
        System.out.println("|      3. Supprimer un livre              |");
        System.out.println("|      4. Rechercher un livre             |");
        System.out.println("|      5. Emprunter un livre              |");
        System.out.println("|      6. Retourner un livre              |");
        System.out.println("|      7. Afficher les statistiques       |");
        System.out.println("|      8. Afficher les livres empruntés   |");
        System.out.println("|      9. Quitter l'application           |");
        System.out.println("-------------------------------------------");
        System.out.println("Votre choix : ");
    }

    // Méthode pour ajouter un livre
    private static void ajouterLivre(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + AJOUT D'UN LIVRE + ====");
        System.out.println("Entrez les informations du livre à ajouter : ");
        System.out.print("Titre du livre: ");
        String titreAjout = scanner.nextLine();
        System.out.print("Auteur du livre: ");
        String auteurAjout = scanner.nextLine();
        System.out.print("Année de publication: ");
        int anneePublieeAjout = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
        System.out.print("ISBN du livre: ");
        String ISBNAjout = scanner.nextLine();
        Livre nouveauLivre = new Livre(titreAjout, auteurAjout, anneePublieeAjout, ISBNAjout);
        bibliotheque.ajouterLivre(nouveauLivre);
        System.out.println("Livre ajouté avec succès !");
        System.out.println();
    }

    // Méthode pour ajouter un utilisateur
    private static void ajouterUtilisateur(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + AJOUT D'UN UTILISATEUR + ====");
        System.out.println("Entrez les informations de l'utilisateur à ajouter : ");
        System.out.print("Nom : ");
        String nomAjout = scanner.nextLine();
        System.out.print("Numero d'identification : ");
        int numeroAjout = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
        Utilisateur nouvelUtilisateur = new Utilisateur(nomAjout, numeroAjout);
        bibliotheque.ajouterUtilisateur(nouvelUtilisateur);
        System.out.println("Utilisateur ajouté avec succès !");
        System.out.println();
    }

    // Méthode pour supprimer un livre
    private static void supprimerLivre(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + SUPPRESSION D'UN LIVRE + ====");
        System.out.println("Entrez le titre du livre à supprimer : ");
        System.out.print("Titre du livre: ");
        String titreSup = scanner.nextLine();
        bibliotheque.supprimerLivre(titreSup);
        System.out.println();
    }

    // Methode pour emprunter un livre
    private static void emprunterLivre(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + EMPRUNT DE LIVRE + ====");
        System.out.println("Veuillez, d'abord entrer vos informations : ");
        System.out.println("Nom : ");
        String nomEmprunteur = scanner.nextLine();
        System.out.println("Numéro d'identification : ");
        int numeroEmprunteur = scanner.nextInt();
        scanner.nextLine();
    
        // Recherche de l'utilisateur dans la bibliothèque
        Utilisateur emprunteur = bibliotheque.rechercherUtilisateur(nomEmprunteur, numeroEmprunteur);
        
        if (emprunteur != null) {
            // L'utilisateur existe dans la bibliothèque
            System.out.println("Êtes-vous à jour dans vos cotisations (O pour OUI et N pour NON)");
            String etreAJour = scanner.nextLine();
            boolean cotisation = etreAJour.equalsIgnoreCase("O");
            
            // Mettre à jour la situation de cotisation de l'utilisateur
            emprunteur.setCotisationAJour(cotisation);
    
            // Emprunter le livre
            System.out.println(" Entrez le titre du livre à emprunter: ");
            String titreEmprunt = scanner.nextLine().trim();
            Livre livreEmprunte = new Livre(titreEmprunt);
            bibliotheque.enregistrerEmprunt(emprunteur, livreEmprunte);
        } else {
            // Si l'utilisateur n'existe pas dans la bibliothèque, le système ajoute directement un nouvel utilisateur
            System.out.println("L'utilisateur n'existe pas dans la bibliothèque.");
            Utilisateur nouvelUtilisateur = new Utilisateur(nomEmprunteur, numeroEmprunteur, new ArrayList<>(), false);
            bibliotheque.ajouterUtilisateur(nouvelUtilisateur);
            System.out.println("L'utilisateur a été ajouté à la bibliothèque avec la cotisation non à jour.");
        }
        System.out.println();
    }
    
    
    
    // Méthode pour retourner un livre
    private static void retournerLivre(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + RETOUR D'UN LIVRE + ====");
        System.out.println("Veuillez, d'abord entrer vos informations : ");
        System.out.println("Nom : ");
        String nomRetourneur = scanner.nextLine();
        System.out.println("Numéro d'identification : ");
        int numeroRetourneur = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le retour à la ligne restant
        
        // Rechercher l'utilisateur dans la bibliothèque
        Utilisateur retourneur = bibliotheque.rechercherUtilisateur(nomRetourneur, numeroRetourneur);
        
        if (retourneur != null) {
            // L'utilisateur existe dans la bibliothèque, procéder au retour du livre
            System.out.println("Entrez le titre du livre à retourner : ");
            String titreRetour = scanner.nextLine().trim();
            
            // Rechercher le livre dans la liste des livres empruntés par l'utilisateur
            Livre livreRetour = null;
            for (Livre livre : retourneur.getLivresEmpruntes()) {
                if (livre.getTitre().equalsIgnoreCase(titreRetour)) {
                    livreRetour = livre;
                    break;
                }
            }
            
            if (livreRetour != null) {
                // Le livre à retourner a été trouvé dans la liste des livres empruntés par l'utilisateur
                bibliotheque.enregistrerRetour(retourneur, livreRetour);
            } else {
                System.out.println("Le livre \"" + titreRetour + "\" n'a pas été trouvé parmi les livres empruntés par l'utilisateur.");
            }
        } else {
            System.out.println("Utilisateur non trouvé dans la bibliothèque.");
        }
        System.out.println();
    }
    
    // Methode pour rechercher un livre
    private static void rechercherLivre(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + RECHERCHE D'UN LIVRE + ====");
        System.out.println("Entrez l'option qui vous intéresse : ");
        System.out.println("----------------------------------");
        System.out.println("| 1. Rechercher par Titre         |");
        System.out.println("| 2. Rechercher par Auteur        |");
        System.out.println("| 3. Rechercher par ISBN          |");
        System.out.println("-----------------------------------");
        System.out.println("Option choisie : ");
        int recherche = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
        switch (recherche) {
            case 1:
                System.out.println("Entrer le titre du livre : ");
                String titreRecherche = scanner.nextLine();
                ArrayList<Livre> resultatsTitre = bibliotheque.rechercherParTitre(titreRecherche);
                // Afficher les résultats de la recherche
                System.out.println("Résultats de la recherche par titre : ");
                for (Livre livre : resultatsTitre) {
                    System.out.println(livre);
                }
                break;
            case 2:
                System.out.println("Entrer l'auteur du livre : ");
                String auteurRecherche = scanner.nextLine();
                ArrayList<Livre> resultatsAuteur = bibliotheque.rechercherParAuteur(auteurRecherche);
                // Afficher les résultats de la recherche
                System.out.println("Résultats de la recherche par auteur : ");
                for (Livre livre : resultatsAuteur) {
                    System.out.println(livre);
                }
                break;
            case 3:
                System.out.println("Entrer l'ISBN du livre : ");
                String isbnRecherche = scanner.nextLine();
                ArrayList<Livre> resultatsISBN = bibliotheque.rechercherParISBN(isbnRecherche);
                // Afficher les résultats de la recherche
                System.out.println("Résultats de la recherche par ISBN : ");
                for (Livre livre : resultatsISBN) {
                    System.out.println(livre);
                }
                break;
            default:
                System.out.println("Option invalide ! ");
                break;
        }
        System.out.println();
    }

    // Methode pour afficher les livres empruntes par un utilisateur spécifique
    private static void afficherLivresEmpruntesUtilisateur(Scanner scanner, Bibliotheque bibliotheque) {
        System.out.println("==== + AFFICHAGE DES LIVRES EMPRUNTÉS PAR UN UTILISATEUR + ====");
        System.out.println("Entrez votre nom : ");
        String nomUtilisateur = scanner.nextLine();
        System.out.println("Entrez votre numéro d'identification : ");
        int numeroUtilisateur = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
        
        // Recherche de l'utilisateur dans la bibliothèque
        Utilisateur utilisateur = bibliotheque.rechercherUtilisateur(nomUtilisateur, numeroUtilisateur);
        
        if (utilisateur != null) {
            // L'utilisateur existe dans la bibliothèque, afficher ses livres empruntés
            utilisateur.afficherLivresEmpruntes();
        } else {
            System.out.println("L'utilisateur n'existe pas dans la bibliothèque.");
        }
    }
    
    
    

   
}

