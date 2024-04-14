public class Livre {
    String titre;
    String auteur;
    int anneePublication;
    String isbn;

    // Constructeur pour initialiser les attributs
    Livre(String titre, String auteur, int anneePublication, String isbn) {
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    Livre(String titre) {
        this.titre = titre;
    }

    // Getters
    public int getAnneePublication() {
        return anneePublication;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    // Setters 
    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Méthode toString pour les afficher les détails du livre
    @Override
    public String toString() {
        return "Livre [Titre : "+titre+", auteur : "+auteur+", année de publication : "+anneePublication+", ISBN : "+isbn+"]";
    }
}