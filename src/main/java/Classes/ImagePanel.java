/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author cagla
 */
public class ImagePanel extends JPanel {

    Image img;

// Constructor method that takes the path of the image
    public ImagePanel(String imagePath) {

        // Creating the icon
        ImageIcon icon = new ImageIcon(imagePath);
        // Filling the img with the image
        img = icon.getImage();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // Drawing the image, this is a flexible structure
        graphics.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

}
