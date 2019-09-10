package Conway;
/**
 * FileControlTab
 * Hayden McAlister
 * September 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import Conway.Constant;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

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
        GamePanel.repaintCells();
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JButton buttonPressed = (JButton)event.getSource(); //All events must be buttons
            if(buttonPressed.getText() == "Save"){
                saveState();
            } else if(buttonPressed.getText() == "Load"){
                loadState();  
            }
            repaint();
        }
    }
    
    /**
     * Saves the current state of the board to a file in the save directory
     * @return the boolean of success, true only if the file is saved correctly
     */
    private void saveState(){
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
        }
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
    
    /**
     * Loads the state of the board from a file
     * @return the boolean of success, true if state is loaded
     */
    private void loadState(){
        File saveDirectory = new File(Constant.SAVE_DIRECTORY); //define where the saves are located
        saveDirectory.mkdirs(); //Create the save folder, incase it hasn't been made
        JFileChooser fileChooser = new JFileChooser(saveDirectory); //Set the file chooser to open at the saves directory
        fileChooser.setMultiSelectionEnabled(false); //enable only one file to be selected
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Conway Files", "conway"); //ensure user only selected conway files
        fileChooser.addChoosableFileFilter(fileFilter); //add the filter
        fileChooser.setFileFilter(fileFilter); //set conway to default filter
        fileChooser.setAcceptAllFileFilterUsed(false); //stop user using "all files" filter
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //only allow the user to select files
        
        int returnValue = fileChooser.showOpenDialog(null); //open the file choosers
        if (returnValue == JFileChooser.APPROVE_OPTION) { //user has selected a file
            File selectedFile = fileChooser.getSelectedFile();
            Boolean loadSuccess = scanFile(selectedFile); //Loads data from selectedFile and update cell array
        } else if(returnValue == JFileChooser.CANCEL_OPTION){ //user canceled selection
            fileLabel.setText("File selection canceled");
        } else if(returnValue == JFileChooser.ERROR_OPTION){ //An error occured
            fileLabel.setText("An error occured: I'M as confused as you are");
        }
    }
    
    /**
     * Scans data from conway file and sets cell array state to the data if valid, updates if invalid
     * @param loadFile the conway file to get data from
     * @return boolean of success for loading the data to game state
     */
    private boolean scanFile(File loadFile){
        try {
            int lineNum=0; //keep track of how many lines have been taken from the file, should equal lines of cells!
            int colNum=0; //keep track of what column we are on
            Scanner fileScanner = new Scanner(loadFile); //create a scanner to get data from file
            Scanner lineScanner; //A scanner to scan along each line
            
            while(fileScanner.hasNext()){ //Continue
                if(lineNum > Constant.NUM_CELLS_Y){//Too many lines, this format is incorrect, do no useless checks
                    fileLabel.setText("ERROR: File is of incorrect dimensions");
                    resetAllOnError();
                    return false; //exit the method
                }
                //Get the next token and ensure it is of correct size
                String currentLine = fileScanner.next();
                if (currentLine.length() != Constant.NUM_CELLS_X){ //Incorrect file format
                    fileLabel.setText("ERROR: File is of incorrect dimensions");
                    resetAllOnError();
                    return false; //exit the method
                }
                lineScanner = new Scanner(currentLine);
                lineScanner.useDelimiter(""); //get only a single character at a time
                while(lineScanner.hasNext()){
                    String nextData = lineScanner.next();
                    if(nextData.equals("1")){ //sets state to true
                        Cell.cellArray[lineNum][colNum].setState(true); 
                    } else if(nextData.equals("0")){ //sets state to false
                        Cell.cellArray[lineNum][colNum].setState(false);
                    } else { //nextData is not of expected format
                        fileLabel.setText("ERROR: File is corrupt");
                        System.out.println(lineNum+", "+colNum+": "+nextData);
                        resetAllOnError();
                        return false; //exit the method
                    }
                    colNum+=1; //increment the column number to set next cell
                }
                lineScanner.close(); //close scanner gracefully
                //Set entire line successfully, move onto next line
                lineNum += 1; //increment line number to set next line
                colNum = 0; //reset column number to start at start
            }
            if(lineNum!=Constant.NUM_CELLS_Y){ //format is not correct, not enough lines given
                fileLabel.setText("ERROR: File is of incorrect dimensions");
                resetAllOnError();
                return false; //exit the method
            }
            fileScanner.close(); //gracefully close the scanner
            //All has gone well
            fileLabel.setText("Loaded File: " + loadFile.getName());
            return true;
        } catch(java.io.FileNotFoundException e){
            //This should never be reached, but if it does...
            fileLabel.setText("ERROR: File does not exist!");
            System.err.println(e);
            return false;
        }
        
    }
    
    /**
     * Reset all cells on an error with loading files
     */
    private void resetAllOnError(){
        //Clear all cells and stop timer
        Cell.clearCellArray();
        Cell.clearStateArray();
        GamePanel.tick();
    }
    
}