import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class logIn extends JFrame{
    private JPanel loginPanel;
    private JPasswordField passwordinput;
    private JTextField loginInput;
    private JButton zarejestrujSieButton;
    private JButton zalogujSięButton;

    public logIn(){
        super("Panel logowania");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);
        this.setContentPane(this.loginPanel);
        setVisible(true);
        zarejestrujSieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register rejestracja = new Register();
                dispose();
            }
        });
        zalogujSięButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/ksiazkakucharska";
                String user = "root";
                String password = "";
                String passwordInput = passwordinput.getText();
                String login = "";
                String logininput = loginInput.getText();
                String zapytanie = "select * from users where name = ? AND password = ?";
                try(Connection polaczenie = DriverManager.getConnection(url,user,password);
                PreparedStatement statement = polaczenie.prepareStatement(zapytanie)){
                    statement.setString(1,logininput);
                    statement.setString(2,passwordInput);
                    ResultSet wynik = statement.executeQuery();
                    if (wynik.next()) {

                        String nazwaUzytkownika = wynik.getString("name");

                        JOptionPane.showMessageDialog(null, "Witaj, " + nazwaUzytkownika + "!", "Zalogowano", JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(null, "Błędny login lub hasło!", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                    }


                }catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd połączenia z bazą: " + ex.getMessage());
                }
            }
        });
        zarejestrujSieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register rejestracja = new Register();
            }
        });
    }

}
