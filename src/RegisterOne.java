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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterOne extends JFrame implements ActionListener{

    JTextField firstNameField, lastNameField, emailField, phoneNumberField, SSNField;
    JPasswordField passwordField, confirmPasswordField;
    private JButton next, quit;
    int idNum;
    JComboBox<String> genderBox;

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
        firstNameField.setBounds(200, 100, 250, 30);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lastName = new JLabel("Last Name:");
        lastName.setBounds(15, 150, 200, 30);
        lastName.setFont(new Font("Arial", Font.BOLD, 20));

        lastNameField = new JTextField();
        lastNameField.setBounds(200, 150, 250, 30);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel email = new JLabel("Email:");
        email.setBounds(15, 200, 200, 30);
        email.setFont(new Font("Arial", Font.BOLD, 20));

        emailField = new JTextField();
        emailField.setBounds(200, 200, 250, 30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel password = new JLabel("Password:");
        password.setBounds(15, 250, 200, 30);
        password.setFont(new Font("Arial", Font.BOLD, 20));

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 250, 250, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel confirmPassword = new JLabel("Confirm Password:");
        confirmPassword.setBounds(15, 300, 200, 30);
        confirmPassword.setFont(new Font("Arial", Font.BOLD, 20));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 300, 250, 30);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setBounds(15, 350, 200, 30);
        phoneNumber.setFont(new Font("Arial", Font.BOLD, 20));

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(200, 350, 250, 30);
        phoneNumberField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel gender = new JLabel("Gender");
        gender.setBounds(15, 400, 200, 30);
        gender.setFont(new Font("Arial", Font.BOLD, 20));

        genderBox = new JComboBox<String>();
        genderBox.setBounds(200, 400, 250, 30);
        genderBox.setFont(new Font("Arial", Font.PLAIN, 15));
        genderBox.addItem("Male");
        genderBox.addItem("Female");
        genderBox.addItem("Other");
        genderBox.setSelectedItem(null);
        genderBox.addActionListener(this);

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
        panel.add(gender);
        panel.add(genderBox);
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
            String gender = (String)genderBox.getSelectedItem();

            if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("") || phoneNumber.equals("") || gender.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (password.equals(confirmPassword) == false) {
                JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.WARNING_MESSAGE);

            }  else if (command.equals("Quit")) {
                System.exit(0);
            }else if(Conn.register(email, confirmPassword, firstName, lastName, phoneNumber, gender, idNum, firstName, lastName, email, password, confirmPassword, phoneNumber, gender)){
                setVisible(false);
                new RegisterTwo();

            }
            }
    
        } 
    }
