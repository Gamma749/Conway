package Conway;
/**
 * MainControlTab
 * Hayden McAlister
 * September 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conway.Constant;

public class MainControlTab extends JPanel{
    private JButton[] buttonArray = {new JButton("Start"), new JButton("Tick"), new JButton("Clear"), new JButton("Randomise")};
    private javax.swing.Timer timer;
    private JLabel timerLabel;
    
    public MainControlTab(javax.swing.Timer timer){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        //Create a button listener to listen on buttons
        ButtonListener buttonListener = new ButtonListener();
        
        //Get a reference to the timer
        this.timer = timer;
        
        //__________________________________________________________
        //Set up the panel
        //Create and add the timer label
        timerLabel=new JLabel("TIMER");
        this.add(timerLabel);
        
        for(JButton button:buttonArray){//Iterate through all buttons
            this.add(button);
            button.addActionListener(buttonListener);
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        GamePanel.repaintCells();
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
                for(JButton button:buttonArray){ //Find the start/stop button and set it to start, as timer has stopped
                    if(button.getText() == "Stop"){
                        button.setForeground(new Color(0,200,0));
                        button.setText("Start");
                    }
                }
                GamePanel.tick();
            } else if(buttonPressed.getText() == "Randomise"){ //Randomise the cell array, stop the timer
                Cell.randomiseAll();
                timer.stop();
                for(JButton button:buttonArray){ //Find the start/stop button and set it to start, as timer has stopped
                    if(button.getText() == "Stop"){
                        button.setForeground(new Color(0,200,0));
                        button.setText("Start");
                    }
                }
            } else if(buttonPressed.getText() == "Clear"){ //Clear the cell array and state array, pulse the timer to clear
                Cell.clearCellArray();
                Cell.clearStateArray();
                for(JButton button:buttonArray){ //Find the start/stop button and set it to start, as timer has stopped
                    if(button.getText() == "Stop"){
                        button.setForeground(new Color(0,200,0));
                        button.setText("Start");
                    }
                }
                GamePanel.tick();
            }
            repaint();
        }
    }
}

