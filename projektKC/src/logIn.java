import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class logIn extends JFrame {
    private JPanel loginPanel;
    private JPasswordField passwordinput;
    private JTextField loginInput;
    private JButton zarejestrujSieButton;
    private JButton zalogujSięButton;

    public logIn() {
        super("Panel logowania");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setContentPane(this.loginPanel);
        this.setLocationRelativeTo(null);

        zalogujSięButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/ksiazkakucharska";
                String user = "root";
                String password = "";

                String passInput = new String(passwordinput.getPassword());
                String logInput = loginInput.getText();

                if (logInput.isEmpty() || passInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wprowadź dane!");
                    return;
                }

                String zapytanie = "SELECT * FROM users WHERE name = ? AND password = ?";

                try (Connection polaczenie = DriverManager.getConnection(url, user, password);
                     PreparedStatement statement = polaczenie.prepareStatement(zapytanie)) {

                    statement.setString(1, logInput);
                    statement.setString(2, passInput);
                    ResultSet wynik = statement.executeQuery();

                    if (wynik.next()) {
                        int idUzytkownika = wynik.getInt("user_id");
                        String nazwaUzytkownika = wynik.getString("name");

                        JOptionPane.showMessageDialog(null, "Witaj, " + nazwaUzytkownika + "!");
                        if (nazwaUzytkownika.equalsIgnoreCase("admin")) {
                            new MainPageAdmin(idUzytkownika,nazwaUzytkownika);
                        } else {
                            new MainPage(idUzytkownika, nazwaUzytkownika);
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Błędny login lub hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd bazy: " + ex.getMessage());
                }
            }
        });

        zarejestrujSieButton.addActionListener(e -> {
            new Register();
            dispose();
        });

        setVisible(true);
    }
}