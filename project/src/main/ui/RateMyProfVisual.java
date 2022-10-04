package ui;

import model.Event;
import model.EventLog;
import model.ListOfRating;
import persistence.JsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// The main view of the Rate My Professor Application
// This class reference code from "https://www.tabnine.com/code/java/classes/javax.swing.JFrame"
public class RateMyProfVisual extends JFrame implements ActionListener {

    private static final String VIEW_APP_ACTION = "VIEW_APP_ACTION";
    private static final String LOAD_APP_ACTION = "LOAD_APP_ACTION";
    private static final String QUIT_APP_ACTION = "QUIT_APP_ACTION";
    private ListOfRating ratingList;
    private ViewListWindow viewList;
    private ListOfRating listOfRating = new ListOfRating();
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/list.json";

    public RateMyProfVisual() throws IOException {
        super("Rate My Professor Application");
        this.setWindow();
        this.setBackground();
        this.setButtons();
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // EFFECTS: set the main window
    private void setWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024,860));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    private void setButtons() {
        JButton viewButton = new JButton("View");
        viewButton.setFont(new Font("Courier",Font.BOLD,50));
        viewButton.setBounds(400,215,300,80);
        add(viewButton);
        viewButton.setForeground(Color.darkGray);
        viewButton.setActionCommand(VIEW_APP_ACTION);
        viewButton.addActionListener(this);

        JButton loadButton = new JButton("Load");
        loadButton.setFont(new Font("Courier",Font.BOLD,50));
        loadButton.setBounds(400,430,300,80);
        add(loadButton);
        loadButton.setForeground(Color.darkGray);
        loadButton.setActionCommand(LOAD_APP_ACTION);
        loadButton.addActionListener(this);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Courier",Font.BOLD,50));
        quitButton.setBounds(400,645,300,80);
        add(quitButton);
        quitButton.setForeground(Color.darkGray);
        quitButton.setActionCommand(QUIT_APP_ACTION);
        quitButton.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (action.equals(VIEW_APP_ACTION)) {
            viewList = new ViewListWindow(ratingList);
        } else if (action.equals(LOAD_APP_ACTION)) {
            jsonReader = new JsonReader(JSON_STORE);
            try {
                ratingList = jsonReader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals(QUIT_APP_ACTION)) {
            EventLog log = EventLog.getInstance();
            for (Event currentEvent : log) {
                System.out.println(currentEvent);
            }
            dispose();
            System.exit(0);
        }
    }
}
