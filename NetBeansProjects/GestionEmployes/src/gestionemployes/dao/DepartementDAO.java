package gestionemployes.dao;

import gestionemployes.modele.Departement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDAO {

    // ajouter departement
    public void ajouterDepartement(Departement d) {
        String sql = "INSERT INTO departements (nom) VALUES (?)";
        try (Connection conn = Connexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNom());
            ps.executeUpdate();
            System.out.println("Département ajouté !");
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }

    // afficher tous les departements
    public List<Departement> getTousLesDepartements() {
        List<Departement> liste = new ArrayList<>();
        String sql = "SELECT * FROM departements";
        try (Connection conn = Connexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Departement d = new Departement(
                    rs.getInt("id"),
                    rs.getString("nom")
                );
                liste.add(d);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
        return liste;
    }

    // Supprimer un departement
    public void supprimerDepartement(int id) {
        String sql = "DELETE FROM departements WHERE id=?";
        try (Connection conn = Connexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Département supprimé !");
        } catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }
}