import javax.swing.*;
import java.awt.*;

public class MainPageAdmin extends JFrame {
    private JPanel mainPanel;
    private JButton showRecipesBtn;
    private JButton addRecipeBtn;
    private JButton logoutBtn;
    private JLabel welcomeLabel;
    private JButton deleteBtn;

    private int userId;
    private String userName;

    public MainPageAdmin(int userId, String userName) {
        super("Książka Kucharska - Panel Administratora");
        this.userId = userId;
        this.userName = userName;


        setContentPane(mainPanel);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeLabel.setText("Panel Admina ");

        styleButton(showRecipesBtn, new Color(44, 62, 80));
        styleButton(addRecipeBtn, new Color(52, 152, 219));
        styleButton(deleteBtn, new Color(46, 204, 113));
        styleButton(logoutBtn, new Color(231, 76, 60));

        showRecipesBtn.addActionListener(e -> new MyRecipes(userId));
        addRecipeBtn.addActionListener(e -> new AddRecipe(userId));

        deleteBtn.addActionListener(e -> {
            try {
                new DeleteUser();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Błąd: Klasa Stats nie istnieje!");
            }
        });

        logoutBtn.addActionListener(e -> {
            new logIn();
            dispose();
        });

        setVisible(true);
    }

    private void styleButton(JButton btn, Color bgColor) {
        if (btn == null) return;
        btn.setFocusPainted(false);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));


    }
}