package Zadanie1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class passwordGen extends JFrame {


    private JPanel JPanel;
    private JButton okButton;
    private JPasswordField enterPassword;
    private JPasswordField confirmPassword;

    public passwordGen() {
        super("Zadanie1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setContentPane(this.JPanel);
        String password="admin";
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(enterPassword.getPassword());
                String confirmedPassword = new String(confirmPassword.getPassword());

                if (!enteredPassword.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Entered password is incorrect!");
                } else if (!confirmedPassword.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Wrong password!");
                } else {
                    JOptionPane.showMessageDialog(null, "Congratulations! You entered the correct password.");
                }
            }
        });
    }
}
