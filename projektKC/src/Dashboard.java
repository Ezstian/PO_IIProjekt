import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Dashboard extends JFrame {
    private JPanel mainPanel;
    private JPanel recipeContainer; // To jest grid wewnątrz ScrollPane
    private JButton wszystkieButton;
    private JButton sniadaniaButton;
    private JButton obiadyButton;
    private JButton deseryButton;
    private JButton button1;

    private final String URL = "jdbc:mysql://localhost:3306/ksiazkakucharska";
    private final String USER = "root";
    private final String PASS = "";

    public Dashboard() {
        super("Katalog Przepisów");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        recipeContainer.setLayout(new GridLayout(0, 3, 15, 15));

        wszystkieButton.addActionListener(e -> ladujPrzepisy(""));
        sniadaniaButton.addActionListener(e -> ladujPrzepisy("Śniadanie"));
        obiadyButton.addActionListener(e -> ladujPrzepisy("Obiad"));
        deseryButton.addActionListener(e -> ladujPrzepisy("Deser"));

        ladujPrzepisy("");
        setVisible(true);
    }

    private void ladujPrzepisy(String kategoria) {
        recipeContainer.removeAll();
        String sql = kategoria.isEmpty() ? "SELECT * FROM recipes" : "SELECT * FROM recipes WHERE category = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (!kategoria.isEmpty()) ps.setString(1, kategoria);
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
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);

        JLabel imgLabel = new JLabel(new ImageIcon("src/cookingBook.png")); // Tymczasowa ikona
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(tytul);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel(kat + " • " + czas + " min");
        infoLabel.setForeground(Color.GRAY);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewBtn = new JButton("Zobacz przepis");
        viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        card.add(infoLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(viewBtn);

        return card;
    }
}