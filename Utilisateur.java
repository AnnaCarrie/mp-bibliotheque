import java.util.ArrayList;

public class Utilisateur {
    String nom; 
    int numeroIdentification;
    ArrayList<Livre> livresEmpruntes;
    boolean cotisationAJour;
    private static final int MAX_LIVRES_EMPRUNTES = 2;

    // Constructeur pour initialiser les attributs
    Utilisateur(String nom, int numeroIdentification, ArrayList<Livre> livresEmpruntes, boolean cotisationAJour) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
        this.cotisationAJour = cotisationAJour;
    }

    Utilisateur(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
    }


    // Getters 
    public ArrayList<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public String getNom() {
        return nom;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }
    
    public static int getMaxLivresEmpruntes() {
        return MAX_LIVRES_EMPRUNTES;
    }

    // Setters
    public void setLivresEmpruntes(ArrayList<Livre> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumeroIdentification(int numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }
    public void setCotisationAJour(boolean cotisationAJour) {
        this.cotisationAJour = cotisationAJour;
    }
    
    // Méthode pour voir si les cotisations de l'utilisateur sont à jour
    public boolean estAJourCotisation() {
        return cotisationAJour;
    }

    // Méthode pour emprunter un livre
    public void emprunterLivre(Livre livre, Bibliotheque bibliotheque) {
        if (estAJourCotisation()) {
            if (livresEmpruntes.size() < MAX_LIVRES_EMPRUNTES) {
                if (bibliotheque.getListeLivres().stream().anyMatch(l -> l.getTitre().equalsIgnoreCase(livre.getTitre()))) {
                    if (livresEmpruntes.stream().noneMatch(l -> l.getTitre().equalsIgnoreCase(livre.getTitre()))) {
                        livresEmpruntes.add(livre);
                        System.out.println("Le livre \"" + livre.getTitre() + "\" a été emprunté par " + nom + ".");
                    } else {
                        System.out.println("Vous avez déjà emprunté le livre \"" + livre.getTitre() + "\".");
                    }
                } else {
                    System.out.println("Le livre \"" + livre.getTitre() + "\" n'existe pas dans la bibliothèque.");
                }
            } else {
                System.out.println("Vous avez déjà atteint le maximum de livres empruntés simultanément.");
            }
        } else {
            System.out.println("Vous ne pouvez pas emprunter un livre car vous n'êtes pas à jour dans vos cotisations.");
        }
    }
    
    
    

    // Méthode pour retourner un livre
    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
        System.out.println("Le livre "+ livre.getTitre() +" a été rendu.");
    }

    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        System.out.println("Liste des livres empruntés par "+nom+" :");
        if (livresEmpruntes.isEmpty()) {
            System.out.println("Aucun livre emprunté !");
        } else {
            for (Livre livre : livresEmpruntes) {
                System.out.println("- " + livre.getTitre());
            }
        }
    }
}

