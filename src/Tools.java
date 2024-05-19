import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tools {

    String path;
    int width;
    int height;

    Tools(String path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;
    }

        public static ImageIcon loadAndResizeImage(String path, int width, int height) {
        try {
            // Load the image from the file
            BufferedImage originalImage = ImageIO.read(new File(path));
            
            // Create a new buffered image with the specified dimensions
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            
            // Draw the original image, scaled to the new dimensions, onto the resized image
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();
            
            // Return the resized image as an ImageIcon
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            System.err.println("Error loading or resizing image: " + e.getMessage());
            return null;
        }
    }

        public static JButton createButtonWithImageIcon(String imagePath, int width, int height) {
        try {
            // Load the image from the file
            BufferedImage image = ImageIO.read(Tools.class.getResourceAsStream(imagePath));

            // Resize the image to the specified dimensions
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Create an ImageIcon with the scaled image
            ImageIcon icon = new ImageIcon(scaledImage);

            // Create a JButton with the ImageIcon
            JButton button = new JButton(icon);

            // Make the button transparent
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
