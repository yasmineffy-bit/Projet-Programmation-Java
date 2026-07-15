package gestionemployes.vue;

import gestionemployes.dao.DepartementDAO;
import gestionemployes.modele.Departement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FenetreDepartement extends JFrame {

    // couleurs
    private static final Color VIOLET_FONCE = new Color(106, 13, 173);
    private static final Color VIOLET_MOYEN = new Color(155, 89, 182);
    private static final Color VIOLET_CLAIR = new Color(243, 229, 245);
    private static final Color BLANC = Color.WHITE;

    // composants
    private JTextField tfNom;
    private JTable table;
    private DefaultTableModel tableModel;

    // DAO
    private DepartementDAO departementDAO = new DepartementDAO();

    public FenetreDepartement() {
        setTitle("Gestion des Départements");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // titre barre
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(VIOLET_FONCE);
        JLabel lblTitre = new JLabel("Gestion des Départements");
        lblTitre.setForeground(BLANC);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        panelTitre.add(lblTitre);
        add(panelTitre, BorderLayout.NORTH);

        // formulaire
        JPanel panelFormulaire = new JPanel(new GridLayout(2, 2, 10, 10));
        panelFormulaire.setBackground(BLANC);
        panelFormulaire.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNom = new JLabel("Nom du département :");
        lblNom.setForeground(VIOLET_FONCE);
        lblNom.setFont(new Font("Arial", Font.BOLD, 13));
        panelFormulaire.add(lblNom);

        tfNom = new JTextField();
        tfNom.setBorder(BorderFactory.createLineBorder(VIOLET_MOYEN));
        panelFormulaire.add(tfNom);

        // boutons
        JPanel panelBoutons = new JPanel(new FlowLayout());
        panelBoutons.setBackground(BLANC);

        JButton btnAjouter = creerBouton("Ajouter");
        JButton btnSupprimer = creerBouton("Supprimer");

        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnSupprimer);

        JPanel panelGauche = new JPanel(new BorderLayout());
        panelGauche.setBackground(BLANC);
        panelGauche.add(panelFormulaire, BorderLayout.NORTH);
        panelGauche.add(panelBoutons, BorderLayout.CENTER);
        add(panelGauche, BorderLayout.NORTH);

        // Tableau
        String[] colonnes = {"ID", "Nom du département"};
        tableModel = new DefaultTableModel(colonnes, 0);
        table = new JTable(tableModel);
        table.setBackground(VIOLET_CLAIR);
        table.setSelectionBackground(VIOLET_MOYEN);
        table.setSelectionForeground(BLANC);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(VIOLET_FONCE);
        table.getTableHeader().setForeground(BLANC);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Actions 
        btnAjouter.addActionListener(e -> ajouterDepartement());
        btnSupprimer.addActionListener(e -> supprimerDepartement());

        // Clic tb
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    tfNom.setText(tableModel.getValueAt(row, 1).toString());
                }
            }
        });

        afficherDepartements();
        setVisible(true);
    }

    // 
    private JButton creerBouton(String texte) {
        JButton btn = new JButton(texte);
        btn.setBackground(VIOLET_MOYEN);
        btn.setForeground(BLANC);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return btn;
    }

    // Ajouter un departement
    private void ajouterDepartement() {
        if (tfNom.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Entrez un nom de département !");
            return;
        }
        Departement d = new Departement(0, tfNom.getText());
        departementDAO.ajouterDepartement(d);
        afficherDepartements();
        tfNom.setText("");
        JOptionPane.showMessageDialog(this, "Département ajouté avec succès !");
    }

    // Supprimer un departement
    private void supprimerDepartement() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un département !");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer ce département ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            departementDAO.supprimerDepartement(id);
            afficherDepartements();
            JOptionPane.showMessageDialog(this, "Département supprimé !");
        }
    }

    // aficher tous depart
    private void afficherDepartements() {
        List<Departement> liste = departementDAO.getTousLesDepartements();
        tableModel.setRowCount(0);
        for (Departement d : liste) {
            tableModel.addRow(new Object[]{d.getId(), d.getNom()});
        }
    }
}
