package ui;


import javax.swing.*;
import java.awt.*;

// Background for windows
// Reference code from "https://stackoverflow.com/questions/523767/how-to-set-background-image-in-java"
public class Background extends JComponent {
    private Image image;

    // EFFECTS: constructs the background image
    public Background(Image image) {
        this.image = image;
    }


    @Override
    // MODIFIES: this
    // EFFECTS: paint the image to component
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }

}
