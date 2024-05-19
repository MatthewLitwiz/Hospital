import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainScreen extends JFrame implements ActionListener{

    int idNum;

	public MainScreen(int idNum) {

        this.idNum = idNum;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Hospital");
        setResizable(false);

        addGuiComponents();

        setVisible(true);
    }

	private void addGuiComponents() {
        JPanel panel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        // doctor stuff
        JMenu Doctor = new JMenu("Doctor Portal");
        Doctor.setFont(new Font("AERIAL", Font.PLAIN, 14));

        // Patient stuff

        JMenu patient = new JMenu("Patient");
        patient.setFont(new Font("AERIAL", Font.PLAIN, 14));
        menuBar.add(patient);


        JMenuItem info = new JMenuItem("My info");
        info.setFont(new Font("AERIAL", Font.PLAIN, 14));
        patient.add(info);
        info.addActionListener(this);

        JMenuItem medicine = new JMenuItem("Medication");
        medicine.setFont(new Font("AERIAL", Font.PLAIN, 14));
        patient.add(medicine);
        patient.addActionListener(this);
        
        JMenuItem appointment = new JMenuItem("Appointment");
        appointment.setFont(new Font("AERIAL", Font.PLAIN, 14));
        patient.add(appointment);
        patient.addActionListener(this);

        JMenuItem billing = new JMenuItem("Billing info");
        billing.setFont(new Font("AERIAL", Font.PLAIN, 14));
        patient.add(billing);
        patient.addActionListener(this);


        setJMenuBar(menuBar);
        getContentPane().add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equalsIgnoreCase("My info")) {
            setVisible(false);
            new PatientInfo(idNum).setVisible(true);

        }


    }



}
