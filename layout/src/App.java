import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class App extends JFrame implements ActionListener {

    JLabel lBienvenu, lUsername, lPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnValider, btnVider;
    JPanel pnlHaut, pnlCentre, pnlBas;

    public App(){
        lBienvenu = new JLabel("<html><h2><i>Bienvenu");
        lUsername = new JLabel("Nom d'utilisateur");
        lPassword = new JLabel("Mot de passe");
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnValider = new JButton("VALIDER");
        btnVider = new JButton("VIDER");

        pnlHaut = new JPanel();
        pnlHaut.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlHaut.add(lBienvenu);

        pnlBas = new JPanel();
        pnlBas.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlBas.add(btnVider);
        pnlBas.add(btnValider);

        pnlCentre = new JPanel();
        pnlCentre.setLayout(new GridLayout(2, 2, 0, 5));
        pnlCentre.add(lUsername);
        pnlCentre.add(txtUsername);
        pnlCentre.add(lPassword);
        pnlCentre.add(txtPassword);
        Border border = BorderFactory.createEmptyBorder(5, 20, 5, 20);
        pnlCentre.setBorder(border);

        this.setLayout(new BorderLayout());
        add(pnlHaut, BorderLayout.NORTH);
        add(pnlCentre, BorderLayout.CENTER);
        add(pnlBas, BorderLayout.SOUTH);

        setVisible(true);
        setTitle("Authentification");
        setSize(600, 220);
        setLocation(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        btnVider.addActionListener(this);
        btnValider.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnVider){
            txtUsername.setText("");
            txtPassword.setText("");
        }

        if(e.getSource() == btnValider){
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            if(username.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(this, "Veuillez remplir les deux champs",
                "Erreur d'authentification", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                    boolean exist = false;
                    database db = new database();
                    Vector<User> users = db.getUsers();
                    for(User u : users){
                        if(username.equals(u.getUsername()) && password.equals(u.getPassword())){
                            JOptionPane.showMessageDialog(this, "Bienvenu "+username,
                            "Authetification réussite", JOptionPane.INFORMATION_MESSAGE);
                            exist = true;
                            break;
                        }
                    }
                    if(!exist){
                            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrecte!",
                        "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        
    }

    public static void main(String[] args) throws Exception {
        new App();
    }



    
}