import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientInfo extends JFrame {

    int idNum;

    JTextField nameField, languageField, genderField, idField, phoneField, emailField;

    public PatientInfo(int idNum) {

        this.idNum = idNum;

        setTitle("Patient Information");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addGuiComponents();

        fetchPatientData(idNum);

        setVisible(true);  // Set visibility to true here
    }

    private void addGuiComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        ImageIcon profile = Tools.loadAndResizeImage("src/icons/profile.jpg", 200, 200);
        JLabel profileLabel = new JLabel(profile);
        profileLabel.setBounds(200, 50, 200, 200);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(50, 275, 100, 30);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        nameField = new JTextField();
        nameField.setBounds(200, 275, 300, 30);
        nameField.setFont(new Font("Dialog", Font.PLAIN, 18));
        nameField.setEditable(false);

        JLabel ageLabel = new JLabel("Language: ");
        ageLabel.setBounds(50, 325, 100, 30);
        ageLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        languageField = new JTextField();
        languageField.setBounds(200, 325, 300, 30);
        languageField.setFont(new Font("Dialog", Font.PLAIN, 18));
        languageField.setEditable(false);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setBounds(50, 375, 100, 30);
        genderLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        genderField = new JTextField();
        genderField.setBounds(200, 375, 300, 30);
        genderField.setFont(new Font("Dialog", Font.PLAIN, 18));
        genderField.setEditable(false);

        JLabel addressLabel = new JLabel("ID: ");
        addressLabel.setBounds(50, 425, 100, 30);
        addressLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        idField = new JTextField();
        idField.setBounds(200, 425, 300, 30);
        idField.setFont(new Font("Dialog", Font.PLAIN, 18));
        idField.setEditable(false);

        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(50, 475, 100, 30);
        phoneLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        phoneField = new JTextField();
        phoneField.setBounds(200, 475, 300, 30);
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 18));
        phoneField.setEditable(false);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(50, 520, 100, 30);
        emailLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        
        emailField = new JTextField();
        emailField.setBounds(200, 520, 300, 30);
        emailField.setFont(new Font("Dialog", Font.PLAIN, 18));
        emailField.setEditable(false);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(languageField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(addressLabel);
        panel.add(idField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(profileLabel);

        getContentPane().add(panel);
    }

    private void fetchPatientData(int idNum) {
        Conn conn = new Conn();
        String query = "SELECT first_name, last_name, email, phone_number, gender, id, language FROM users WHERE id = ?";

        try (Connection connection = conn.c;
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, idNum);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    nameField.setText(rs.getString("first_name") + " " + rs.getString("last_name"));
                    emailField.setText(rs.getString("email"));
                    phoneField.setText(rs.getString("phone_number"));
                    genderField.setText(rs.getString("gender"));
                    idField.setText(rs.getString("id"));
                    languageField.setText(rs.getString("language"));
                } else {
                    JOptionPane.showMessageDialog(this, "Patient not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching patient data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}