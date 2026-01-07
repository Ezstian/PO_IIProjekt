import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JPanel Panel1;
    private JLabel lab2;
    private JButton WyjscieButton;
    private JButton ZalogujButton;
    private JTextField txtLogin;
    private JTextField passwordField1;
    private JLabel lblOutput;
    String user= "admin", password="admin";

    public LoginForm(){
        super("LoginForm");
        this.setContentPane(this.Panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 400, height = 300;
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        WyjscieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ZalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = txtLogin.getText();
                String passwordInput = passwordField1.getText();
                if(userInput.equals(user) && passwordInput.equals(password)){
                    lblOutput.setText("Zalogowano do systemu...");
                    //otwarcie okna menu
                }
                else{
                    lblOutput.setText("Podano błędne dane.. spróbuj ponownie");
                    txtLogin.setText("");
                    passwordField1.setText("");
                }
            }
        });
    }
}
