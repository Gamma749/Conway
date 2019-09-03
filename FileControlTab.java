package Conway;
/**
 * FileControlTab
 * Hayden McAlister
 * September 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import Conway.Constant;
import java.util.Date;

public class FileControlTab extends JPanel{
    private JButton[] buttonArray = {new JButton("Save"), new JButton("Load")};
    private JTextArea fileLabel;
    
    public FileControlTab(){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        //Create a button listener to listen on buttons
        ButtonListener buttonListener = new ButtonListener();
        
        //_____________________________________________________________
        //Set up the panel
        for(JButton button:buttonArray){//Iterate through all buttons
            this.add(button);
            button.addActionListener(buttonListener);
        }
        //Initalise the fileLabel, which will update the user about save/load success
        fileLabel = new JTextArea(2,40);
        fileLabel.setEditable(false); //stop the user editing the field
        fileLabel.setBackground(new Color(220,220,255)); //set background
        fileLabel.setMargin(new Insets(10,10,10,10)); //Set the margins
        this.add(fileLabel);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JButton buttonPressed = (JButton)event.getSource(); //All events must be buttons
            if(buttonPressed.getText() == "Save"){
                saveState();
            }
            repaint();
        }
    }
    
    /**
     * Saves the current state of the board to a file in the save directory
     * @return the boolean of success, true only if the file is saved correctly
     */
    private boolean saveState(){
        //Ensure the selected name is valid
        try{ //Catch any IOExceptions
            File saveDirectory = new File(Constant.SAVE_DIRECTORY);
            File file = new File(saveDirectory, generateFileName()+".conway"); //Create the file object
            saveDirectory.mkdirs(); //Create the save folder, incase it hasn't been made
            file.createNewFile(); //Create the file to save to
            FileWriter writer = new FileWriter(file); //Create a new file writer that wil write the state of the board
            
            for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
                for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                    writer.write((Cell.cellArray[x][y].getState()?'1':'0')); //write a 1 if the cell is filled, 0 otherwise
                }
                writer.write('\n'); //write a \n between lines
            }
            writer.close(); //close the scanner
            fileLabel.setText("File Saved Successfully:\n" + file); //Tell the use the file has been saved
        } catch (java.io.IOException e){
            //In an error, update the GUI and print error to stderr
            System.err.println(e);
            fileLabel.setText("ERROR: File not saved successfully");
            return false; //If an error is encountered, exit the method gracefully
        }
        
        return true; //Method has sucesfully terminated
    }
    
    /**
     * Generates the file name that the current board state will be saved under
     * @return the string representing the name of the file
     */
    private String generateFileName(){
        String fileName = new Date().toString(); //use the time stamp as the basis of the file name
        //replace colons with hyphens
        fileName = fileName.replace(':', '-');
        return fileName;
    }
    
}