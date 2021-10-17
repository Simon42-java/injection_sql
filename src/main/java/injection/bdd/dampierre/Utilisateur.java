package injection.bdd.dampierre;

public class Utilisateur {

    private long id;
    private String prenom;
    private String nom;
    private String email;

    // methode utiliser par l'appel de UtilisateurDAO
    public Utilisateur(long id, String prenom, String nom, String email) {

        this.setId(id);
        this.setPrenom(prenom);
        this.setNom(nom);
        this.setEmail(email);
    }

    //getter 
    public long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
