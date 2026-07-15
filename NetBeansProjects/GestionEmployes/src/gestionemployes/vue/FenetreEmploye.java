package gestionemployes.vue;

import gestionemployes.dao.DepartementDAO;
import gestionemployes.dao.EmployeDAO;
import gestionemployes.modele.Departement;
import gestionemployes.modele.Employe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FenetreEmploye extends JFrame {

    //couleurs
    
    private static final Color VIOLET_FONCE = new Color(106, 13, 173);
    private static final Color VIOLET_MOYEN = new Color(155, 89, 182);
    private static final Color VIOLET_CLAIR = new Color(243, 229, 245);
    private static final Color BLANC = Color.WHITE;

    // elements du formulaire
    private JTextField tfNom, tfPrenom, tfDate, tfRole, tfEmail, tfTel, tfRecherche;
    private JComboBox<Departement> cbDepartement;
    private JTable table;
    private DefaultTableModel tableModel;

    // DAO
    private EmployeDAO employeDAO = new EmployeDAO();
    private DepartementDAO departementDAO = new DepartementDAO();

    public FenetreEmploye() {
        setTitle("Gestion des Employés");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //  titre barre
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(VIOLET_FONCE);
        JLabel lblTitre = new JLabel("Gestion des Employés");
        lblTitre.setForeground(BLANC);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 22));
        panelTitre.add(lblTitre);
        add(panelTitre, BorderLayout.NORTH);

        // formulaire
        JPanel panelFormulaire = new JPanel(new GridLayout(9, 2, 10, 10));
        panelFormulaire.setBackground(BLANC);
        panelFormulaire.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelFormulaire.add(creerLabel("Nom :"));
        tfNom = creerTextField();
        panelFormulaire.add(tfNom);

        panelFormulaire.add(creerLabel("Prénom :"));
        tfPrenom = creerTextField();
        panelFormulaire.add(tfPrenom);

        panelFormulaire.add(creerLabel("Date d'embauche :"));
        tfDate = creerTextField();
        panelFormulaire.add(tfDate);

        panelFormulaire.add(creerLabel("Rôle :"));
        tfRole = creerTextField();
        panelFormulaire.add(tfRole);

        panelFormulaire.add(creerLabel("Email :"));
        tfEmail = creerTextField();
        panelFormulaire.add(tfEmail);

        panelFormulaire.add(creerLabel("Téléphone :"));
        tfTel = creerTextField();
        panelFormulaire.add(tfTel);

        panelFormulaire.add(creerLabel("Département :"));
        cbDepartement = new JComboBox<>();
        chargerDepartements();
        panelFormulaire.add(cbDepartement);

        panelFormulaire.add(creerLabel("Rechercher :"));
        tfRecherche = creerTextField();
        panelFormulaire.add(tfRecherche);

        // bouton
        JPanel panelBoutons = new JPanel(new FlowLayout());
        panelBoutons.setBackground(BLANC);

        JButton btnAjouter = creerBouton("Ajouter");
        JButton btnModifier = creerBouton("Modifier");
        JButton btnSupprimer = creerBouton("Supprimer");
        JButton btnRechercher = creerBouton("Rechercher");
        JButton btnAfficher = creerBouton("Afficher tout");

        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnModifier);
        panelBoutons.add(btnSupprimer);
        panelBoutons.add(btnRechercher);
        panelBoutons.add(btnAfficher);

        JPanel panelGauche = new JPanel(new BorderLayout());
        panelGauche.add(panelFormulaire, BorderLayout.CENTER);
        panelGauche.add(panelBoutons, BorderLayout.SOUTH);
        panelGauche.setBackground(BLANC);

        add(panelGauche, BorderLayout.WEST);

        // tableau
        String[] colonnes = {"ID", "Nom", "Prénom", "Date", "Rôle", "Département", "Email", "Téléphone"};
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

        // actions pour chaque bouton
        btnAjouter.addActionListener(e -> ajouterEmploye());
        btnModifier.addActionListener(e -> modifierEmploye());
        btnSupprimer.addActionListener(e -> supprimerEmploye());
        btnRechercher.addActionListener(e -> rechercherEmploye());
        btnAfficher.addActionListener(e -> afficherEmployes());

        // clic tableau
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                remplirFormulaire();
            }
        });

        afficherEmployes();
        setVisible(true);
    }

 
    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(VIOLET_FONCE);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }

    // chanps de texte
    private JTextField creerTextField() {
        JTextField tf = new JTextField();
        tf.setBorder(BorderFactory.createLineBorder(VIOLET_MOYEN));
        return tf;
    }

    //bouton 
    private JButton creerBouton(String texte) {
        JButton btn = new JButton(texte);
        btn.setBackground(VIOLET_MOYEN);
        btn.setForeground(BLANC);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return btn;
    }

    // departements
    private void chargerDepartements() {
        cbDepartement.removeAllItems();
        List<Departement> deps = departementDAO.getTousLesDepartements();
        for (Departement d : deps) {
            cbDepartement.addItem(d);
        }
    }

    // aouter un employe
    private void ajouterEmploye() {
        Departement dep = (Departement) cbDepartement.getSelectedItem();
        Employe e = new Employe(0, tfNom.getText(), tfPrenom.getText(),
                tfDate.getText(), tfRole.getText(),
                dep != null ? dep.getId() : 0,
                tfEmail.getText(), tfTel.getText());
        employeDAO.ajouterEmploye(e);
        afficherEmployes();
        viderFormulaire();
        JOptionPane.showMessageDialog(this, "Employé ajouté avec succès !");
    }

    //modifier un employe
    private void modifierEmploye() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un employé !");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        Departement dep = (Departement) cbDepartement.getSelectedItem();
        Employe e = new Employe(id, tfNom.getText(), tfPrenom.getText(),
                tfDate.getText(), tfRole.getText(),
                dep != null ? dep.getId() : 0,
                tfEmail.getText(), tfTel.getText());
        employeDAO.modifierEmploye(e);
        afficherEmployes();
        viderFormulaire();
        JOptionPane.showMessageDialog(this, "Employé modifié avec succès !");
    }

    // supprimer un employe
    private void supprimerEmploye() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un employé !");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer cet employé ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            employeDAO.supprimerEmploye(id);
            afficherEmployes();
            JOptionPane.showMessageDialog(this, "Employé supprimé !");
        }
    }

    // chercher un employe
    private void rechercherEmploye() {
        List<Employe> liste = employeDAO.rechercherParNom(tfRecherche.getText());
        tableModel.setRowCount(0);
        for (Employe e : liste) {
            tableModel.addRow(new Object[]{
                e.getId(), e.getNom(), e.getPrenom(),
                e.getDateEmbauche(), e.getRole(),
                e.getDepartementId(), e.getEmail(), e.getTelephone()
            });
        }
    }

    // afficher tous les employes
    private void afficherEmployes() {
        List<Employe> liste = employeDAO.getTousLesEmployes();
        tableModel.setRowCount(0);
        for (Employe e : liste) {
            tableModel.addRow(new Object[]{
                e.getId(), e.getNom(), e.getPrenom(),
                e.getDateEmbauche(), e.getRole(),
                e.getDepartementId(), e.getEmail(), e.getTelephone()
            });
        }
    }

    // Remplir formulaire 
    private void remplirFormulaire() {
        int row = table.getSelectedRow();
        if (row != -1) {
            tfNom.setText(tableModel.getValueAt(row, 1).toString());
            tfPrenom.setText(tableModel.getValueAt(row, 2).toString());
            tfDate.setText(tableModel.getValueAt(row, 3).toString());
            tfRole.setText(tableModel.getValueAt(row, 4).toString());
            tfEmail.setText(tableModel.getValueAt(row, 6).toString());
            tfTel.setText(tableModel.getValueAt(row, 7).toString());
        }
    }

    // Vider le formulaire
    private void viderFormulaire() {
        tfNom.setText("");
        tfPrenom.setText("");
        tfDate.setText("");
        tfRole.setText("");
        tfEmail.setText("");
        tfTel.setText("");
        tfRecherche.setText("");
    }
}