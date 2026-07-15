package gestionemployes.dao;

import gestionemployes.modele.Employe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO {

    // Ajouter un employe
    public void ajouterEmploye(Employe e) {
        String sql = "INSERT INTO employes (nom, prenom, date_embauche, role, departement_id, email, telephone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getDateEmbauche());
            ps.setString(4, e.getRole());
            ps.setInt(5, e.getDepartementId());
            ps.setString(6, e.getEmail());
            ps.setString(7, e.getTelephone());
            ps.executeUpdate();
            System.out.println("Employé ajouté !");
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }

    // Modifier un employe
    public void modifierEmploye(Employe e) {
        String sql = "UPDATE employes SET nom=?, prenom=?, date_embauche=?, role=?, departement_id=?, email=?, telephone=? WHERE id=?";
        try (Connection conn = Connexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getDateEmbauche());
            ps.setString(4, e.getRole());
            ps.setInt(5, e.getDepartementId());
            ps.setString(6, e.getEmail());
            ps.setString(7, e.getTelephone());
            ps.setInt(8, e.getId());
            ps.executeUpdate();
            System.out.println("Employé modifié !");
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }

    // Supprimer un employe
    public void supprimerEmploye(int id) {
        String sql = "DELETE FROM employes WHERE id=?";
        try (Connection conn = Connexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Employé supprimé !");
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }

    // Afficher tous les employes
    public List<Employe> getTousLesEmployes() {
        List<Employe> liste = new ArrayList<>();
        String sql = "SELECT * FROM employes";
        try (Connection conn = Connexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employe e = new Employe(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("date_embauche"),
                    rs.getString("role"),
                    rs.getInt("departement_id"),
                    rs.getString("email"),
                    rs.getString("telephone")
                );
                liste.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
        return liste;
    }

    // Rechercher un employe par son nom
    public List<Employe> rechercherParNom(String nom) {
        List<Employe> liste = new ArrayList<>();
        String sql = "SELECT * FROM employes WHERE nom LIKE ?";
        try (Connection conn = Connexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employe e = new Employe(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("date_embauche"),
                    rs.getString("role"),
                    rs.getInt("departement_id"),
                    rs.getString("email"),
                    rs.getString("telephone")
                );
                liste.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
        return liste;
    }
}