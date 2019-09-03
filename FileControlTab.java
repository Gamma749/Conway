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

public class FileControlTab extends JPanel{
    private JButton[] buttonArray = {new JButton("Save"), new JButton("Load")};
    private JLabel saveLabel;
    private JTextField saveNameTextEntry;
    
    public FileControlTab(){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        //Create a button listener to listen on buttons
        ButtonListener buttonListener = new ButtonListener();
        
        //_____________________________________________________________
        //Set up the panel
        saveLabel = new JLabel("Save Name:");
        this.add(saveLabel);
        saveNameTextEntry = new JTextField(10);
        this.add(saveNameTextEntry);
        for(JButton button:buttonArray){//Iterate through all buttons
            this.add(button);
            button.addActionListener(buttonListener);
        }
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
     * Saves the current state of the board to a file in the save directory, with a name determined by the textfield
     * @return the boolean of success, true only if the file is saved correctly
     */
    private boolean saveState(){
        //Ensure the selected name is valid
        if(saveNameTextEntry.getText()=="" || saveNameTextEntry.getText().indexOf('.')!=-1){
            return false; //if the text field is empty, or contains a . , don't save anything
        }
        try{ //Catch any IOExceptions
            File file = new File(Constant.SAVE_FOLDER, saveNameTextEntry.getText()+".txt"); //Create the file object
            file.mkdir(); //Create the folver, incase it hasn't been made
            file.createNewFile(); //Create the file to save to
            FileWriter writer = new FileWriter(file); //Create a new file writer that wil write the state of the board
            
            for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
                for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                    writer.write((Cell.cellArray[x][y].getState()?'1':'0')); //write a 1 if the cell is filled, 0 otherwise
                }
                writer.write('\n'); //write a \n between lines
            }
        } catch (java.io.IOException e){
            System.err.println(e);
            return false; //If an error is encountered, exit the method gracefully
        }
        return true; //Method has sucesfully terminated
    }
    
}