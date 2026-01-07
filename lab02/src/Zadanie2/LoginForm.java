package Zadanie2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {


    private JPanel JPanel;
    private JPasswordField passwordField;
    private JTextField loginField;
    private JButton logIn;

    public LoginForm() {
        super("Zadanie 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        setContentPane(this.JPanel);
        String password1="admin", login="admin";


        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginField.getText();
                String password = new String(passwordField.getPassword());

                if(username.equals(login) && password.equals(password1)){
                    new Formularz();
                }else{
                    JOptionPane.showMessageDialog(null,"Podano błędne dane");
                }

            }
        });
    }
}
