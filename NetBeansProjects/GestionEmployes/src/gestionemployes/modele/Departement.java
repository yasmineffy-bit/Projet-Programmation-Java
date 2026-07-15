package gestionemployes.modele;

//les attributs- un departement a un id et un nom
public class Departement {
    private int id;
    private String nom;

    //constructeur
    public Departement() {}

    public Departement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    @Override
    public String toString() {
        return nom;
    }
}