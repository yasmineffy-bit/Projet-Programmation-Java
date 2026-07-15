package gestionemployes.vue;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame {

    // Couleurs
    private static final Color VIOLET_FONCE = new Color(106, 13, 173);
    private static final Color VIOLET_MOYEN = new Color(155, 89, 182);
    private static final Color BLANC = Color.WHITE;

    public FenetrePrincipale() {
        setTitle("Système de Gestion des Employés");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // titre barre
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(VIOLET_FONCE);
        panelTitre.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel lblTitre = new JLabel("Système de Gestion des Employés");
        lblTitre.setForeground(BLANC);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        panelTitre.add(lblTitre);
        add(panelTitre, BorderLayout.NORTH);

        // centre
        JPanel panelCentre = new JPanel(new GridLayout(3, 1, 20, 20));
        panelCentre.setBackground(BLANC);
        panelCentre.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        JButton btnEmployes = creerBouton("👤  Gérer les Employés");
        JButton btnDepartements = creerBouton("🏢  Gérer les Départements");
        JButton btnQuitter = creerBouton("🚪  Quitter");
        btnQuitter.setBackground(new Color(180, 50, 50));

        panelCentre.add(btnEmployes);
        panelCentre.add(btnDepartements);
        panelCentre.add(btnQuitter);

        add(panelCentre, BorderLayout.CENTER);

        // en bas
        JPanel panelBas = new JPanel();
        panelBas.setBackground(VIOLET_FONCE);
        JLabel lblBas = new JLabel("ENSA de Fès - 2026");
        lblBas.setForeground(BLANC);
        lblBas.setFont(new Font("Arial", Font.ITALIC, 12));
        panelBas.add(lblBas);
        add(panelBas, BorderLayout.SOUTH);

        // Actions boutons
        btnEmployes.addActionListener(e -> new FenetreEmploye());
        btnDepartements.addActionListener(e -> new FenetreDepartement());
        btnQuitter.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton creerBouton(String texte) {
        JButton btn = new JButton(texte);
        btn.setBackground(VIOLET_MOYEN);
        btn.setForeground(BLANC);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        return btn;
    }
}
