package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import Conway.Constant;

public class ControlPanel extends JPanel{
<<<<<<< HEAD
    JTabbedPane tabPane = new JTabbedPane();
    private javax.swing.Timer timer;
    private JLabel saveLabel;
=======
    private static final long serialVersionUID = 3L;
    JTabbedPane tabPane = new JTabbedPane();
    private JPanel[] panelArray = {new JPanel(), new JPanel()};
    private JButton[][] buttonArray = {
        {new JButton("Start"), new JButton("Tick"), new JButton("Clear")},
        {new JButton("Save"), new JButton("Load")}
    };
    private javax.swing.Timer timer;
    private JLabel timerStateLabel, saveLabel;
>>>>>>> 39159ca772ad08e844816e55b1e742e0967e6167
    private JTextField saveNameTextEntry;
    
    public ControlPanel(javax.swing.Timer timer){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        
<<<<<<< HEAD
        //Create the panes from objects
        JPanel mainControlTab = new MainControlTab(timer);
        JPanel fileControlTab = new FileControlTab();
        
        tabPane.addTab("Main",mainControlTab); //The main panel, for timer and clear buttons
        tabPane.addTab("File", fileControlTab); //The file panel, for save/load functionality
        tabPane.setBackground(new Color(200,200,235));
        this.add(tabPane); //add the tabPane to the controlPanel so it may be used
    }
    
    
=======
        //Set up the panels and add them to the tabbed pane
        for(JPanel panel:panelArray){
            panel.setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        }
        tabPane.addTab("Main",panelArray[0]); //The main panel, for timer and clear buttons
        tabPane.addTab("Save/Load", panelArray[1]);
        tabPane.setBackground(new Color(200,200,235));
        this.add(tabPane); //add the tabPane to the controlPanel so it may be used
        
        //Create a reference to the timer
        this.timer = timer;
        
        //Set up the first pane----------------------------------------------------------------
        
        //Create and add the timer state
        timerStateLabel=new JLabel("TIMER"); //off to start
        panelArray[0].add(timerStateLabel);
        
        for(JButton button:buttonArray[0]){//Iterate through all buttons in the first button array
            panelArray[0].add(button);
            button.addActionListener(buttonListener);
        }
        
        //Set up the second pane----------------------------------------------------------------
        saveLabel = new JLabel("Save Name:");
        panelArray[1].add(saveLabel);
        saveNameTextEntry = new JTextField(10);
        panelArray[1].add(saveNameTextEntry);
        for(JButton button:buttonArray[1]){//Iterate through all buttons in the Second button array
            panelArray[1].add(button);
            button.addActionListener(buttonListener);
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JButton buttonPressed = (JButton)event.getSource(); //All events must be buttons
            if(buttonPressed.getText() == "Start"){ //Start the timer
                buttonPressed.setForeground(new Color(240, 50, 50));
                buttonPressed.setText("Stop");
                timer.start();
            } else if(buttonPressed.getText() == "Stop"){ //Stop the timer
                buttonPressed.setForeground(new Color(0,200,0));
                buttonPressed.setText("Start");
                timer.stop();
            } else if(buttonPressed.getText() == "Tick"){ //Pulse the timer once
                for(JButton button:buttonArray[0]){
                    if(button.getText() == "Stop"){
                        button.setForeground(new Color(0,200,0));
                        button.setText("Start");
                    }
                }
                GamePanel.tick();
            } else if(buttonPressed.getText() == "Clear"){ //Clear the cell array and state array, pulse the timer to clear
                Cell.clearCellArray();
                Cell.clearStateArray();
                GamePanel.tick();
            } else if(buttonPressed.getText() == "Save"){
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
>>>>>>> 39159ca772ad08e844816e55b1e742e0967e6167
}