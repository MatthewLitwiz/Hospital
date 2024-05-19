import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGui extends JFrame implements ActionListener {
    private JButton loginButton, registerButton;
    JTextField emailField;
    JPasswordField passwordField;;
    PatientInfo patientInfo;
    int idNum;

    LoginGui(int idNum) {

        this.idNum = idNum;


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login to Hospital");
        addGuiComponents();

        setVisible(true);

    }

    public void addGuiComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Hospital Login");
        title.setBounds(100, 20, 300, 50);
        title.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel username = new JLabel("Email: ");
        username.setBounds(50, 150, 100, 30);
        username.setFont(new Font("Dialog", Font.BOLD, 16));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setBounds(50, 150, 75, 30);

        emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel password = new JLabel("Password: ");
        password.setBounds(50, 200, 100, 30);
        password.setFont(new Font("Dialog", Font.BOLD, 16));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setBounds(50, 200, 75, 30);
        passwordPanel.setBackground(Color.LIGHT_GRAY);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 200, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 350, 100, 50);
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setBounds(200, 350, 100, 50);
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 400));

        JPanel color1 = new JPanel();
        color1.setBounds(200, 100, 400, 200);
        color1.setBackground(Color.RED);
        color1.setOpaque(true); // Set opaque to true to ensure visibility
        layeredPane.add(color1, JLayeredPane.DEFAULT_LAYER); // Add color1 to the default layer

        JPanel color2 = new JPanel();
        color2.setBounds(0, 100, 200, 200);
        color2.setBackground(Color.BLUE);
        color2.setOpaque(true);
        layeredPane.add(color2, JLayeredPane.DRAG_LAYER);

        JPanel color3 = new JPanel();
        color3.setBounds(0, 0, 400, 100);
        color3.setBackground(Color.GREEN);
        color3.setOpaque(true);
        layeredPane.add(color3, JLayeredPane.PALETTE_LAYER);

        JPanel color4 = new JPanel();
        color4.setBounds(0, 300, 400, 200);
        color4.setBackground(Color.YELLOW);
        color4.setOpaque(true);
        layeredPane.add(color4, JLayeredPane.MODAL_LAYER);

        panel.add(password);
        panel.add(passwordField);
        panel.add(username);
        panel.add(emailField);
        panel.add(title);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(color1);
        panel.add(color2);
        panel.add(color3);
        panel.add(color4);
        panel.add(usernamePanel);
        panel.add(passwordPanel);

        getContentPane().add(layeredPane);
        getContentPane().add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Login")) {
            String enteredEmail = emailField.getText();
            String enteredPassword = new String(passwordField.getPassword());
    
            try {
                // Connect to the database
                Connection conn = new Conn().c;
    
                // Prepare the SQL statement to retrieve password for the entered email
                String query = "SELECT email, password FROM users WHERE email=?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, enteredEmail);
    
                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();
    
                // Check if the email exists in the database
                if (resultSet.next()) {
                    // Email found, retrieve the corresponding password
                    String storedEmail = resultSet.getString("email");
                    String storedPassword = resultSet.getString("password");
    
                    // Compare the passwords
                    if (enteredPassword.equals(storedPassword)) {
                        // Passwords match, allow login
                        setVisible(false);
                        new MainScreen(idNum);
                    } else {
                        // Passwords don't match, show error message
                        JOptionPane.showMessageDialog(this, "Incorrect password", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
    } catch (Exception ex) {
        ex.printStackTrace();
    }


    }else if (command.equalsIgnoreCase("Register")) {
        setVisible(false);
        new RegisterOne(patientInfo).setVisible(true);
    }

}


}