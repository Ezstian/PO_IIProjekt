import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RecipeDetails extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel infoLabel;
    private JTextArea ingredientsArea;
    private JTextArea instructionsArea;
    private JButton closeButton;

    private final String URL = "jdbc:mysql://localhost:3306/ksiazkakucharska";
    private final String USER = "root";
    private final String PASS = "";

    public RecipeDetails(String recipeTitle) {
        super("Szczegóły przepisu: " + recipeTitle);

        if (mainPanel == null) return;

        setContentPane(mainPanel);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadRecipeDetails(recipeTitle);

        closeButton.addActionListener(e -> dispose());
        ingredientsArea.setEditable(false);
        instructionsArea.setEditable(false);
        ingredientsArea.setLineWrap(true);
        instructionsArea.setLineWrap(true);

        setVisible(true);
    }

    private void loadRecipeDetails(String title) {
        String sql = "SELECT title, category, prep_time, ingredients, instructions FROM recipes WHERE title = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                titleLabel.setText(rs.getString("title"));
                infoLabel.setText(rs.getString("category") + " | Czas: " + rs.getInt("prep_time") + " min");
                ingredientsArea.setText(rs.getString("ingredients"));
                instructionsArea.setText(rs.getString("instructions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd ładowania szczegółów: " + e.getMessage());
        }
    }
}