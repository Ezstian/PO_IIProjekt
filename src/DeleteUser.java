import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeleteUser extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> userComboBox;
    private JButton deleteBtn;
    private JButton backBtn;

    public DeleteUser() {
        super("Usuwanie Użytkowników");
        setContentPane(mainPanel);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadUsersToComboBox();

        deleteBtn.addActionListener(e -> {
            String selectedName = (String) userComboBox.getSelectedItem();
            if (selectedName == null || selectedName.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Nie możesz usunąć tego konta!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz usunąć użytkownika " + selectedName + "?");
            if (confirm == JOptionPane.YES_OPTION) {
                deleteUserFromDb(selectedName);
            }
        });

        backBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void loadUsersToComboBox() {
        userComboBox.removeAllItems();
        String url = "jdbc:mysql://localhost:3306/ksiazkakucharska";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM users WHERE name != 'admin'")) {
            while (rs.next()) {
                userComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteUserFromDb(String name) {
        String url = "jdbc:mysql://localhost:3306/ksiazkakucharska";
        try (Connection conn = DriverManager.getConnection(url, "root", "")) {
            conn.setAutoCommit(false);
            try {
                String sqlRecipes = "DELETE FROM recipes WHERE user_id = (SELECT user_id FROM users WHERE name = ?)";
                PreparedStatement ps1 = conn.prepareStatement(sqlRecipes);
                ps1.setString(1, name);
                ps1.executeUpdate();
                String sqlUser = "DELETE FROM users WHERE name = ?";
                PreparedStatement ps2 = conn.prepareStatement(sqlUser);
                ps2.setString(1, name);
                ps2.executeUpdate();

                conn.commit();
                JOptionPane.showMessageDialog(this, "Użytkownik i jego przepisy zostały usunięte.");
                loadUsersToComboBox();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd bazy: " + e.getMessage());
        }
    }
}