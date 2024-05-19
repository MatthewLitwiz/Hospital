import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class RegisterTwo extends JFrame implements ActionListener{


    private JTextField allergiesField, medicationField;
    private JButton finish;
    private JComboBox<String> languageField, relationshipField, chronicConditions, familyHistory;
    private JDateChooser dateChooser;

    public RegisterTwo() {

        setTitle("Page 2/2");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addGuiComponents();

        setVisible(false);
    }

    private void addGuiComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel id = new JLabel("ID: " + idNum); // id number
        id.setBounds(350, 10, 150, 30);
        id.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel title = new JLabel("Medical History:");
        title.setBounds(80, 40, 400, 50);
        title.setFont(new Font("Monospaced", Font.BOLD, 30));

        JLabel Hospitalization = new JLabel("Last Hospitalization:");
        Hospitalization.setBounds(15, 150, 200, 30);
        Hospitalization.setFont(new Font("Arial", Font.BOLD, 20));

        // date
        dateChooser = new JDateChooser();
        dateChooser.setBounds(210, 150, 200, 30);
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel allergies = new JLabel("Known allergies:");
        allergies.setBounds(15, 200, 200, 30);
        allergies.setFont(new Font("Arial", Font.BOLD, 20));

        allergiesField = new JTextField();
        allergiesField.setBounds(200, 200, 200, 30);
        allergiesField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel conditions = new JLabel("Chronic conditions:");
        conditions.setBounds(15, 250, 200, 30);
        conditions.setFont(new Font("Arial", Font.BOLD, 20));

        chronicConditions = new JComboBox<String>();
        chronicConditions.setBounds(210, 250, 200, 30);
        chronicConditions.setFont(new Font("Arial", Font.PLAIN, 20));
        chronicConditions.addItem("None");
        chronicConditions.addItem("Diabetes");
        chronicConditions.addItem("Hypertension");
        chronicConditions.addItem("Asthma");
        chronicConditions.addItem("Cancer");
        chronicConditions.addItem("Heart Disease");
        chronicConditions.addItem("Stroke");
        chronicConditions.addItem("Kidney Disease");
        chronicConditions.addItem("Liver Disease");
        chronicConditions.addItem("HIV/AIDS");
        chronicConditions.addItem("Other");
        chronicConditions.setSelectedIndex(0); // default

        JLabel medication = new JLabel("Current Medication:");
        medication.setBounds(15, 300, 200, 30);
        medication.setFont(new Font("Arial", Font.BOLD, 20));

        medicationField = new JTextField();
        medicationField.setBounds(210, 300, 200, 30);
        medicationField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel History = new JLabel("Family History:");
        History.setBounds(15, 350, 200, 30);
        History.setFont(new Font("Arial", Font.BOLD, 20));

        familyHistory = new JComboBox<String>();
        familyHistory.setBounds(210, 350, 200, 30);
        familyHistory.setFont(new Font("Arial", Font.PLAIN, 20));
        familyHistory.addItem("None");
        familyHistory.addItem("Diabetes");
        familyHistory.addItem("Hypertension");
        familyHistory.addItem("Asthma");
        familyHistory.addItem("Cancer");
        familyHistory.addItem("Heart Disease");
        familyHistory.addItem("Stroke");
        familyHistory.addItem("Kidney Disease");
        familyHistory.addItem("Liver Disease");
        familyHistory.addItem("HIV/AIDS");
        familyHistory.addItem("Other");
        familyHistory.setSelectedIndex(0); // default

        JLabel language = new JLabel("Preferred Language:");
        language.setBounds(15, 400, 200, 30);
        language.setFont(new Font("Arial", Font.BOLD, 20));

        languageField = new JComboBox<String>();
        languageField.setBounds(215, 400, 200, 30);
        languageField.setFont(new Font("Arial", Font.PLAIN, 20));
        languageField.addItem("English");
        languageField.addItem("Spanish");
        languageField.addItem("French");
        languageField.addItem("German");
        languageField.addItem("Chinese");
        languageField.addItem("Japanese");
        languageField.addItem("Korean");
        languageField.addItem("Other");
        languageField.setSelectedIndex(0); // default

        JLabel Relationship = new JLabel("Relationship:");
        Relationship.setBounds(15, 450, 200, 30);
        Relationship.setFont(new Font("Arial", Font.BOLD, 20));

        relationshipField = new JComboBox<String>();
        relationshipField.setBounds(215, 450, 200, 30);
        relationshipField.setFont(new Font("Arial", Font.PLAIN, 20));
        relationshipField.addItem("Self");
        relationshipField.addItem("Spouse");
        relationshipField.addItem("Parent");
        relationshipField.addItem("Child");
        relationshipField.addItem("Sibling");
        relationshipField.addItem("Other");
        relationshipField.setSelectedIndex(0); // default

        finish = new JButton("Finish");
        finish.setBounds(350, 500, 100, 30);
        finish.setFont(new Font("Arial", Font.BOLD, 20));
        finish.setFocusable(false);
        finish.addActionListener(this);

        panel.add(title);
        panel.add(Hospitalization);
        panel.add(allergies);
        panel.add(conditions);
        panel.add(chronicConditions);
        panel.add(medication);
        panel.add(History);
        panel.add(id);
        panel.add(language);
        panel.add(allergiesField);
        panel.add(medicationField);
        panel.add(familyHistory);
        panel.add(languageField);
        panel.add(dateChooser);
        panel.add(Relationship);
        panel.add(relationshipField);
        panel.add(finish);

        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    
        if (command.equals("Finish")) {
            // Get the data from the fields
            String allergies = allergiesField.getText();
            String chronicCondition = (String) chronicConditions.getSelectedItem();
            String medication = medicationField.getText();
            String familyHist = (String) familyHistory.getSelectedItem();
            String language = (String) languageField.getSelectedItem();
            String relationship = (String) relationshipField.getSelectedItem();
            String date = dateChooser.toString();

            if (allergies.equals("") || medication.equals("") || date == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {

                // Update the user's information in the database

                updateUserData(idNum, allergies, chronicCondition, medication, familyHist, language, relationship, date);

            // Move to the next registration page
            this.setVisible(false);
            new LoginGui(idNum);
            }
    }
  
}

    private void updateUserData(int idNum, String allergies, String chronicCondition, String medication, String familyHist, String language, String relationship, String date) {
        try {
            Connection conn = new Conn().c;
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE users SET allergies = ?, chronic_condition = ?, medication = ?, family_history = ?, language = ?, relationship = ?, date = ? WHERE id = ?");
            preparedStatement.setString(1, allergies);
            preparedStatement.setString(2, chronicCondition);
            preparedStatement.setString(3, medication);
            preparedStatement.setString(4, familyHist);
            preparedStatement.setString(5, language);
            preparedStatement.setString(6, relationship);
            preparedStatement.setString(7, date);
            preparedStatement.setInt(8, idNum);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "You've SuccessFully Registered","Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user information", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}