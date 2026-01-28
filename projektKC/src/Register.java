import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame {
    private JPanel registerPanel;
    private JTextField nameInput; // nazwa
    private JTextField emailInput; // email
    private JPasswordField passwordInput1;
    private JPasswordField passwordInput2;
    private JButton zarejestrujSieButton;
    private JButton logInButton;

    public Register() {
        super("Panel rejestracji");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);
        this.setContentPane(this.registerPanel);
        setVisible(true);
        zarejestrujSieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText().trim();
                String email = emailInput.getText().trim();
                String pass1 = new String(passwordInput1.getPassword());
                String pass2 = new String(passwordInput2.getPassword());


                if (name.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione!", "Błąd walidacji", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. Jeśli dane są poprawne, przechodzimy do bazy
                String url = "jdbc:mysql://localhost:3306/ksiazkakucharska";
                String user = "root";
                String password = "";
                String dodanie = "INSERT INTO users (name, email, password) VALUES ( ?, ?, ?)";

                if (!pass1.equals(pass2)) {
                    JOptionPane.showMessageDialog(null, "Hasła nie są identyczne!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection polaczenie = DriverManager.getConnection(url, user, password);
                     PreparedStatement statement = polaczenie.prepareStatement(dodanie)) {

                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, pass1);

                    int wiersze = statement.executeUpdate();

                    if (wiersze > 0) {
                        JOptionPane.showMessageDialog(null, "Rejestracja pomyślna! Możesz się zalogować.");
                        new logIn();
                        dispose();
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd bazy: " + ex.getMessage());
                }
            }

        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new logIn();
            }
        });
    }
}