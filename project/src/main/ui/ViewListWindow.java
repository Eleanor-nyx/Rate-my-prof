package ui;

import model.ListOfRating;
import model.Rating;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


// This class reference code from
// "https://www.tabnine.com/code/java/classes/javax.swing.JFrame"
// "https://docs.oracle.com/javase/tutorial/uiswing/components/table.html"
// View the rating list window
public class ViewListWindow extends JFrame implements ActionListener {
    private ListOfRating ratingList;
    private static final String SAVE_APP_ACTION = "SAVE_APP_ACTION";
    private static final String ADD_RATING = "ADD_RATING";
    private static final String DELETE_RATING = "DELETE_RATING";
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/list.json";
    private DefaultTableModel tableModel;
    private JTable table;



    // MODIFIES: this
    // EFFECTS: constructs viewList
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public ViewListWindow(ListOfRating ratingList) {
        super("View the Rating List");
        this.ratingList = ratingList;
        jsonWriter = new JsonWriter(JSON_STORE);
        final String[] columnNames = new String[] {
                "Index",
                "Name",
                "Course Code",
                "Quality",
                "Comment"
        };
        tableModel = new DefaultTableModel(null,columnNames) {
        };
        table = new JTable(tableModel);
        this.getTableRows();
        this.setWindow();
        this.setBackground();
        this.setButtons();
        add(new JScrollPane(table));
        pack();
        setLayout(new FlowLayout());
        setTitle("The List Of Ratings");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }


    private void setWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024,860));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }


    // EFFECTS: get the rows in the rating list table
    public void getTableRows() {
        for (int i = 0; i < ratingList.numRatings(); i++) {
            Rating rating = ratingList.getRating(i);
            Object[]  tableRow = new Object[] {
                    i,
                    rating.getName(),
                    rating.getCourseCode(),
                    rating.getQuality(),
                    rating.getComment()
            };
            tableModel.addRow(tableRow);
        }
    }


    // MODIFIES: this
    // EFFECTS: set buttons for add, delete and save the rating list
    private void setButtons() {
        JButton addButton = new JButton("add");
        addButton.setBounds(70,50,60,30);
        add(addButton);
        addButton.setForeground(Color.darkGray);
        addButton.setActionCommand(ADD_RATING);
        addButton.addActionListener(this);

        JButton deleteButton = new JButton("delete");
        deleteButton.setBounds(70,50,60,30);
        add(deleteButton);
        deleteButton.setForeground(Color.darkGray);
        deleteButton.setActionCommand(DELETE_RATING);
        deleteButton.addActionListener(this);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(70,50,60,30);
        add(saveButton);
        saveButton.setForeground(Color.darkGray);
        saveButton.setActionCommand(SAVE_APP_ACTION);
        saveButton.addActionListener(this);


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
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (action.equals(ADD_RATING)) {
            new AddRatingWindow(this,ratingList);
        } else if (action.equals(DELETE_RATING)) {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "Please select the rating you want to delete.");
                return;
            }
            ratingList.deleteRating(table.getSelectedRow());
            ((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
            JOptionPane.showMessageDialog(null,"Your rating has been deleted.");
        } else if (action.equals(SAVE_APP_ACTION)) {
            // new SaveReminderWindow(ratingList);
            try {
                jsonWriter.open();
                jsonWriter.write(ratingList);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Rating List saved.");

            } catch (IOException e) {
                System.out.println("Unable to write to file" + JSON_STORE);
            }
            dispose();
        }
    }


}
