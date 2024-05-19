import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainScreen extends JFrame{

	public MainScreen() {
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



        getContentPane().add(panel);

    }



}
