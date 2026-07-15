package gestionemployes.modele;

//les attributs
public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String dateEmbauche;
    private String role;
    private int departementId;
    private String email;
    private String telephone;

    //les constructeurs
    public Employe() {}

    public Employe(int id, String nom, String prenom, String dateEmbauche, String role, int departementId, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEmbauche = dateEmbauche;
        this.role = role;
        this.departementId = departementId;
        this.email = email;
        this.telephone = telephone;
    }

    //les gets et sets
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(String dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getDepartementId() { return departementId; }
    public void setDepartementId(int departementId) { this.departementId = departementId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}