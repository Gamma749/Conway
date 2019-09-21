package Conway;

/**
 * Hayden McAlister
 * September 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conway.Constant;

public class ColorControlTab extends JPanel{
    JTextArea redComponent, greenComponent, blueComponent;
    JLabel redLabel, greenLabel, blueLabel;
    JPanel redPanel, greenPanel, bluePanel;
    
    public ColorControlTab(){
        this.setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, 10));
        this.setMaximumSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, 10));
        this.setLayout(new GridLayout(3,1,0,10));
        //Set the labels and text fields
        redLabel = new JLabel("Red: ");
        greenLabel = new JLabel("Green: ");
        blueLabel = new JLabel("Blue: ");
 
        redComponent = new JTextArea(1,3);
        greenComponent = new JTextArea(1,3);
        blueComponent = new JTextArea(1,3);
        
        //Define the panels to be box panels
        redPanel=new ColorBoxPanel();
        redPanel.setBackground(Color.RED);
        greenPanel=new ColorBoxPanel();
        greenPanel.setBackground(Color.GREEN);
        bluePanel=new ColorBoxPanel();
        bluePanel.setBackground(Color.BLUE);
        
        //Set Sizes
        redLabel.setSize(50,50);
        
        //Add components
        redPanel.add(redLabel);
//        redPanel.add(redComponent);
        greenPanel.add(greenLabel);
//        greenPanel.add(greenComponent);
        bluePanel.add(blueLabel);
//        bluePanel.add(blueComponent);
        
        //Add the panels to this parent panel
        this.add(redPanel);
        this.add(greenPanel);
        this.add(bluePanel);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    /**
     * Defines a new jpanel to hold other components within the parent panel, specifically designed for a box layout
     */
    private class ColorBoxPanel extends JPanel{
        public ColorBoxPanel(){
            this.setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, 100));
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
        }
    }
}