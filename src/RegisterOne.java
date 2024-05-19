import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterOne extends JFrame implements ActionListener{

    JTextField firstNameField, lastNameField, emailField, phoneNumberField, SSNField;
    JPasswordField passwordField, confirmPasswordField;
    private JButton next, quit;
    int idNum;

    public RegisterOne() {
        setTitle("Page 1/2");
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

        JLabel title = new JLabel("Personal Information:");
        title.setBounds(35, 30, 400, 50);
        title.setFont(new Font("Monospaced", Font.BOLD, 30));

        JLabel firstName = new JLabel("First Name:");
        firstName.setBounds(15, 100, 200, 30);
        firstName.setFont(new Font("Arial", Font.BOLD, 20));

        firstNameField = new JTextField();
        firstNameField.setBounds(200, 100, 200, 15);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lastName = new JLabel("Last Name:");
        lastName.setBounds(15, 150, 200, 30);
        lastName.setFont(new Font("Arial", Font.BOLD, 20));

        lastNameField = new JTextField();
        lastNameField.setBounds(200, 150, 200, 15);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel email = new JLabel("Email:");
        email.setBounds(15, 200, 200, 30);
        email.setFont(new Font("Arial", Font.BOLD, 20));

        emailField = new JTextField();
        emailField.setBounds(200, 200, 200, 15);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel password = new JLabel("Password:");
        password.setBounds(15, 250, 200, 30);
        password.setFont(new Font("Arial", Font.BOLD, 20));

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 250, 200, 15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel confirmPassword = new JLabel("Confirm Password:");
        confirmPassword.setBounds(15, 300, 200, 30);
        confirmPassword.setFont(new Font("Arial", Font.BOLD, 20));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 300, 200, 15);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setBounds(15, 350, 200, 30);
        phoneNumber.setFont(new Font("Arial", Font.BOLD, 20));

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(200, 350, 200, 30);
        phoneNumberField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel SSN = new JLabel("Last 4 Digit - SSN:");
        SSN.setBounds(15, 400, 200, 30);
        SSN.setFont(new Font("Arial", Font.BOLD, 20));

        SSNField = new JTextField();
        SSNField.setBounds(200, 400, 200, 30);
        SSNField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel id = new JLabel("Auto Generated ID:");
        id.setBounds(15, 450, 200, 30);
        id.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel idNumber = new JLabel();
        idNumber.setBounds(200, 450, 200, 30);
        idNumber.setFont(new Font("Arial", Font.PLAIN, 20));
        idNum = (int) (Math.random() * 10000);
        idNumber.setText(Integer.toString(idNum));

        next = new JButton("Next");
        next.setBounds(350, 500, 100, 30);
        next.setFont(new Font("Arial", Font.BOLD, 20));
        next.setFocusable(false);
        next.addActionListener(this);

        quit = new JButton("Quit");
        quit.setBounds(15, 500, 100, 30);
        quit.setFont(new Font("Arial", Font.BOLD, 20));
        quit.setFocusable(false);
        quit.addActionListener(this);

        panel.add(title);
        panel.add(idNumber);
        panel.add(id);
        panel.add(firstName);
        panel.add(lastName);
        panel.add(email);
        panel.add(password);
        panel.add(confirmPassword);
        panel.add(phoneNumber);
        panel.add(firstNameField);
        panel.add(lastNameField);
        panel.add(emailField);
        panel.add(passwordField);
        panel.add(confirmPasswordField);
        panel.add(phoneNumberField);
        panel.add(SSN);
        panel.add(SSNField);
        panel.add(next);
        panel.add(quit);


        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    
        if (command.equals("Next")) {
    
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String phoneNumber = phoneNumberField.getText();
            String SSN = SSNField.getText();

            if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("") || phoneNumber.equals("") || SSN.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (password.equals(confirmPassword) == false) {
                JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.WARNING_MESSAGE);

            } else if (SSN.length() != 4) {
                JOptionPane.showMessageDialog(null, "Please enter the last 4 digits of your SSN", "Error", JOptionPane.WARNING_MESSAGE);

            } else if (command.equals("Quit")) {
                System.exit(0);
            }else {

                try {
                    // Insert user information into the database
                    String query = "INSERT INTO users (first_name, last_name, email, password, phone_number, SSN, id, allergies, chronic_condition, medication, family_history, language, relationship, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Connection conn = new Conn().c;
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, password);
                    preparedStatement.setString(5, phoneNumber);
                    preparedStatement.setString(6, SSN);
                    preparedStatement.setInt(7, idNum);
                    preparedStatement.setString(8, "");
                    preparedStatement.setString(9, "");
                    preparedStatement.setString(10, "");
                    preparedStatement.setString(11, "");
                    preparedStatement.setString(12, "");
                    preparedStatement.setString(13, "");
                    preparedStatement.setString(14, "");

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "1/2 Page Complete");
                        setVisible(false);
                        new RegisterTwo(idNum).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to register user", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
    
        } 
    }
}