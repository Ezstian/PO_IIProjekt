import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Stats extends JFrame {
    private JPanel mainPanel;
    private JLabel totalRecipesLabel;
    private JLabel userRecipesLabel;
    private JButton closeButton;

    private final String URL = "jdbc:mysql://localhost:3306/ksiazkakucharska";
    private final String USER = "root";
    private final String PASS = "";

    public Stats(int userId, String userName) {
        super("Statystyki - " + userName);

        setContentPane(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (totalRecipesLabel != null) {
            totalRecipesLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        }
        if (userRecipesLabel != null) {
            userRecipesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        loadStats(userId);

        if (closeButton != null) {
            styleButton(closeButton, new Color(52, 152, 219));
            closeButton.addActionListener(e -> dispose());
        }

        setVisible(true);
    }

    private void loadStats(int userId) {
        int totalRecipes = 0;
        int userSpecificRecipes = 0;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            String sqlTotal = "SELECT COUNT(*) FROM recipes";
            try (PreparedStatement psTotal = conn.prepareStatement(sqlTotal);
                 ResultSet rsTotal = psTotal.executeQuery()) {
                if (rsTotal.next()) {
                    totalRecipes = rsTotal.getInt(1);
                }
            }

            String sqlUser = "SELECT COUNT(*) FROM recipes WHERE user_id = ?";
            try (PreparedStatement psUser = conn.prepareStatement(sqlUser)) {
                psUser.setInt(1, userId);
                ResultSet rsUser = psUser.executeQuery();
                if (rsUser.next()) {
                    userSpecificRecipes = rsUser.getInt(1);
                }
            }

            if (totalRecipesLabel != null) {
                totalRecipesLabel.setText("Wszystkich przepisów w bazie: " + totalRecipes);
            }
            if (userRecipesLabel != null) {
                userRecipesLabel.setText("Twoich przepisów: " + userSpecificRecipes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (totalRecipesLabel != null) {
                totalRecipesLabel.setText("Błąd ładowania statystyk: " + e.getMessage());
            }
            if (userRecipesLabel != null) {
                userRecipesLabel.setText("");
            }
            JOptionPane.showMessageDialog(this, "Błąd bazy danych podczas ładowania statystyk: " + e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
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