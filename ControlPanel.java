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
    private JButton[] buttonArray = {new JButton("Start"), new JButton("Stop"), new JButton("Clear")};
    public CellPanel cellPanel;
    private JLabel timerState;
    
    public ControlPanel(JPanel cellPanel){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        //Create a button listener to listen on buttons
        ButtonListener buttonListener = new ButtonListener();
        
        //Create a reference to the cell panel
        this.cellPanel = (CellPanel) cellPanel;
        
        //Create and add the timer state
        timerState=new JLabel("OFF"); //off to start
        add(timerState);
        
        for(JButton button:buttonArray){
            add(button);
            button.addActionListener(buttonListener);
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JButton buttonPressed = (JButton)event.getSource(); //All events must be buttons
            if(buttonPressed.getText() == "Start"){
                cellPanel.timer.start();
                timerState.setText("ON");
            } else if(buttonPressed.getText() == "Stop"){
                cellPanel.timer.stop();
                timerState.setText("OFF");
            } else if(buttonPressed.getText() == "Clear"){
                cellPanel.clearArray();
            }
        }
    }
}