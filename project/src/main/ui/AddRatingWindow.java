package ui;

import model.ListOfRating;
import model.Rating;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Add ratings window
// This class reference code from "https://www.tabnine.com/code/java/classes/javax.swing.JFrame"
public class AddRatingWindow extends JFrame implements ActionListener {
    private ListOfRating ratingList;
    private ViewListWindow viewList;
    private JTextField nameField;
    private JTextField courseCodeField;
    private JTextField qualityField;
    private JTextField commentField;
    private static final String DONE_ACTION = "DONE_ACTION";



    // EFFECTS: constructs Add Rating Window
    public AddRatingWindow(ViewListWindow viewList, ListOfRating ratingList) {
        super("Add a rating");
        this.ratingList = ratingList;
        this.viewList = viewList;
        this.setWindow();
        this.setBackground();
        this.setButton();
        this.setLabels();
        pack();
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }


    // MODIFIES: this
    // EFFECTS: set a addRating window
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(1000,800));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    
    // MODIFIES: this
    // EFFECTS: set button for finish adding a rating
    private void setButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setBounds(400,620,100,40);
        add(doneButton);
        doneButton.setForeground(Color.darkGray);
        doneButton.setActionCommand(DONE_ACTION);
        doneButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: set label to give instructions about how to add rating
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void setLabels() {
        JLabel nameLabel = new JLabel("Enter the full name of the professor, eg. 'Meghan Allen'");
        nameLabel.setBounds(80,30,400,20);
        add(nameLabel);
        nameLabel.setForeground(Color.darkGray);
        nameField = new JTextField(40);
        nameField.setBounds(80,70,400,20);
        add(nameField);

        JLabel courseCodeLabel = new JLabel("Enter the course code, eg. 'CPSC210'");
        courseCodeLabel.setBounds(80,190,400,20);
        add(courseCodeLabel);
        courseCodeLabel.setForeground(Color.darkGray);
        courseCodeField = new JTextField(40);
        courseCodeField.setBounds(80,230,400,20);
        add(courseCodeField);

        JLabel qualityLabel = new JLabel(
                "Enter the quality rating you want to give the professor (out of 5), eg. '4' ");
        qualityLabel.setBounds(80,350,400,20);
        add(qualityLabel);
        qualityLabel.setForeground(Color.darkGray);
        qualityField = new JTextField(40);
        qualityField.setBounds(80,390,400,20);
        add(qualityField);

        JLabel commentLabel = new JLabel(
                "Enter some comment you want to give this professor, eg 'He is very patient.' ");
        commentLabel.setBounds(80,510,600,20);
        add(commentLabel);
        commentLabel.setForeground(Color.darkGray);
        commentField = new JTextField(40);
        commentField.setBounds(80,540,400,20);
        add(commentField);

    }

    // MODIFIES: this
    // EFFECTS: set the background
    private void setBackground() {
        try {
            BufferedImage background = ImageIO.read(new File("src/main/ui/background.jpg"));
            setContentPane(new Background(background));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // EFFECTS: prompts user to select an option and performs the selected action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DONE_ACTION)) {
            String name = nameField.getText();
            String courseCode = courseCodeField.getText();
            String quality = qualityField.getText();
            String comment = commentField.getText();
            ratingList.addRating(new Rating(name,courseCode,quality,comment));
            dispose();
            viewList.dispose();
            new ViewListWindow(ratingList);
        }
    }
}
