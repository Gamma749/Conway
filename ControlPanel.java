package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conway.Constant;

public class ControlPanel extends JPanel{
    private static final long serialVersionUID = 3L;
    private JButton[] buttonArray = {new JButton("Start"), new JButton("Stop"), new JButton("Tick"), new JButton("Clear")};
    private javax.swing.Timer timer;
    private JLabel timerStateLabel;
    
    public ControlPanel(javax.swing.Timer timer){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        //Create a button listener to listen on buttons
        ButtonListener buttonListener = new ButtonListener();
        
        //Create a reference to the timer
        this.timer = timer;
        
        //Create and add the timer state
        timerStateLabel=new JLabel("OFF"); //off to start
        add(timerStateLabel);
        
        for(JButton button:buttonArray){
            add(button);
            button.addActionListener(buttonListener);
        }
    }
    
    public void paintComponent(Graphics g){
        timerStateLabel.setText(timer.isRunning()?"ON":"OFF"); //Determine the state of the timer at repaint time
        super.paintComponent(g);
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JButton buttonPressed = (JButton)event.getSource(); //All events must be buttons
            if(buttonPressed.getText() == "Start"){ //Start the timer
                timer.start();
            } else if(buttonPressed.getText() == "Stop"){ //Stop the timer
                timer.stop();
            } else if(buttonPressed.getText() == "Tick"){ //Pulse the timer once
                GamePanel.tick();
            } else if(buttonPressed.getText() == "Clear"){ //Clear the cell array and state array, pulse the timer to clear
                Cell.clearCellArray();
                Cell.clearStateArray();
                GamePanel.tick();
            }
            repaint();
        }
    }
}