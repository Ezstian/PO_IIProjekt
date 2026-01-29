import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel menuPanel;
    private JButton zaloguj;

    public Menu() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 500);
        this.setContentPane(this.menuPanel);
        setVisible(true);
        zaloguj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn logowanie = new logIn();
                dispose();
            }
        });

    }
}