import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddRecipe extends JFrame {
    private JPanel addPanel;
    private JTextField titleFIeld;
    private JComboBox<String> timeField;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextArea instrukcjaTextArea;
    private JTextArea ingrediensArea;
    private JComboBox<String> categoryArea;
    private int currentUserId;

    public AddRecipe(int userId) {
        super("Dodaj nowy przepis");
        this.currentUserId = userId;

        setContentPane(addPanel);
        setSize(450,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        styleComponents();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleFIeld.getText();
                String ingredients = ingrediensArea.getText();
                String instructions = instrukcjaTextArea.getText();
                String category = (String) categoryArea.getSelectedItem();
                String selectedTime = (String) timeField.getSelectedItem();

                if (title.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione!");
                    return;
                }

                String numericTime = selectedTime.replace("min", "").trim();
                saveToDatabase(title, category, ingredients, instructions, numericTime);
            }
        });

        saveButton.addActionListener(e -> dispose());

        setVisible(true); //
    }

    private void saveToDatabase(String title, String category, String ingredients, String instructions, String time) {
        String url = "jdbc:mysql://localhost:3306/ksiazkakucharska";
        String user = "root";
        String pass = "";

        String sql = "INSERT INTO recipes (title, category, ingredients, instructions, prep_time, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, category);
            ps.setString(3, ingredients);
            ps.setString(4, instructions);
            ps.setInt(5, Integer.parseInt(time));
            ps.setInt(6, currentUserId); //

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Przepis został pomyślnie dodany!");
                dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Błąd zapisu do bazy: " + ex.getMessage());
        }
    }

    private void styleComponents() {
        if (cancelButton == null || saveButton == null) return;

        cancelButton.setBackground(new Color(46, 204, 113));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        saveButton.setBackground(new Color(231, 76, 60));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        if (ingrediensArea != null) ingrediensArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
        if (instrukcjaTextArea != null) instrukcjaTextArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
    }
}