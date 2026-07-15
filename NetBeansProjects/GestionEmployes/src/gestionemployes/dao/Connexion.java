package gestionemployes.dao;

// les imports qui permettent de parler avec MySQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_employes";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnexion() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
            return conn;
            
            // pour la securite j ai ajoute catch
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            return null;
        }
    }
}