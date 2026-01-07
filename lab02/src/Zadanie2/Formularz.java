package Zadanie2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formularz extends JFrame {


    private JPanel JPanel;
    private JRadioButton mniejNiz18RadioButton;
    private JRadioButton a1830RadioButton;
    private JRadioButton a30RadioButton;
    private JTextField secondName;
    private JTextField firstName;
    private JTextArea komentarz;
    private JButton wyślijButton;

    public Formularz() {
        super("Formularz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setVisible(true);
        setContentPane(this.JPanel);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mniejNiz18RadioButton.isSelected()){
                    JOptionPane.showMessageDialog(null,"Aby wysłać komentarz trzeba mieć ponad 18 lat!");
                    dispose();
                }
            }
        };
        mniejNiz18RadioButton.addActionListener(listener);
        a1830RadioButton.addActionListener(listener);
        a30RadioButton.addActionListener(listener);
        wyślijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = firstName.getText();
                String surname = secondName.getText();
                String koment = komentarz.getText();
                JOptionPane.showMessageDialog(null,"Hej "+name+" "+surname+"!\n" +
                        "Twoj komentarz to: \n"+koment+"\n\n Dziękujemy za komentarz!"
                );
            }
        });
    }
}
