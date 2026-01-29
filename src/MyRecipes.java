import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MyRecipes extends JFrame {
    private JPanel mainPanel;
    private JPanel recipeContainer;
    private JButton closeButton;
    private JCheckBox onlyMineCheckBox;
    private JButton deseryButton;
    private JButton obiadyButton;
    private JButton sniadaniaButton;
    private JButton wszystkieButton;

    private int loggedUserId;
    private String aktualnaKategoria = "";

    private final String URL = "jdbc:mysql://localhost:3306/ksiazkakucharska";
    private final String USER = "root";
    private final String PASS = "";

    public MyRecipes(int userId) {
        super("Katalog Przepisów");
        this.loggedUserId = userId;
        if (mainPanel == null) {
            System.err.println("Błąd: mainPanel nie został zainicjalizowany!");
            return;
        }

        setContentPane(mainPanel);
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        styleButton(wszystkieButton, new Color(52, 152, 219));
        styleButton(sniadaniaButton, new Color(52, 152, 219));
        styleButton(obiadyButton, new Color(52, 152, 219));
        styleButton(deseryButton, new Color(52, 152, 219));
        styleButton(closeButton, new Color(231, 76, 60));

        recipeContainer.setLayout(new GridLayout(0, 3, 20, 20));

        wszystkieButton.addActionListener(e -> setKategoria(""));
        sniadaniaButton.addActionListener(e -> setKategoria("Śniadanie"));
        obiadyButton.addActionListener(e -> setKategoria("Obiad"));
        deseryButton.addActionListener(e -> setKategoria("Deser"));
        onlyMineCheckBox.addActionListener(e -> refreshData());
        closeButton.addActionListener(e -> dispose());

        refreshData();
        setVisible(true);
    }

    private void setKategoria(String kategoria) {
        this.aktualnaKategoria = kategoria;
        refreshData();
    }

    private void styleButton(JButton btn, Color bgColor) {
        if (btn == null) return;
        btn.setFocusPainted(false);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void refreshData() {
        if (recipeContainer == null) return;
        recipeContainer.removeAll();

        boolean showOnlyMine = onlyMineCheckBox.isSelected();

        StringBuilder sql = new StringBuilder(
                "SELECT r.title, r.category, r.prep_time, u.name FROM recipes r " +
                        "JOIN users u ON r.user_id = u.user_id WHERE 1=1 "
        );

        if (showOnlyMine) sql.append(" AND r.user_id = ?");
        if (!aktualnaKategoria.isEmpty()) sql.append(" AND r.category = ?");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (showOnlyMine) {
                ps.setInt(paramIndex++, loggedUserId);
            }
            if (!aktualnaKategoria.isEmpty()) {
                ps.setString(paramIndex, aktualnaKategoria);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recipeContainer.add(createRecipeCard(
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getInt("prep_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recipeContainer.revalidate();
        recipeContainer.repaint();
    }

    private JPanel createRecipeCard(String tytul, String kat, int czas) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setBackground(Color.WHITE);
        JLabel imgLabel = new JLabel(new ImageIcon("src/cookingBook.png"));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(tytul);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel(kat + " • " + czas + " min");
        infoLabel.setForeground(Color.GRAY);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewBtn = new JButton("Zobacz przepis");
        viewBtn.setBackground(new Color(0, 102, 204));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewBtn.addActionListener(e -> new RecipeDetails(tytul));

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        card.add(infoLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(viewBtn);

        return card;
    }
}