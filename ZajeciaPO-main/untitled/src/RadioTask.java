import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioTask extends JFrame{
    private JLabel lab2;
    private JLabel txtOption;
    private JRadioButton windowsRadioButton;
    private JRadioButton linuxRadioButton;
    private JRadioButton macRadioButton;
    private JLabel iconOS;
    private JButton wsteczButton;
    private JButton okButton;
    private JPanel JPanel1;
    private JLabel lblSystemCheck;

    private ImageIcon iconLinux = new ImageIcon(getClass().getResource("Linux.png"));

    public RadioTask(){
        super("LoginForm");
        this.setContentPane(this.JPanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 400, height = 300;
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        linuxRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(linuxRadioButton.isSelected()){
                    iconOS.setIcon(iconLinux);
                    lblSystemCheck.setText("Wybrano system: Linux");
                }
            }
        });
    }
}
