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
    JTextField redComponent, greenComponent, blueComponent;
    JLabel redLabel, greenLabel, blueLabel;
    JPanel labelPanel, textFieldPanel;
    
    public ColorControlTab(){
        //Set the labels and text fields
        redLabel = new JLabel("Red: ");
        greenLabel = new JLabel("Green: ");
        blueLabel = new JLabel("Blue: ");
        
        redComponent = new JTextField(3);
        greenComponent = new JTextField(3);
        blueComponent = new JTextField(3);
        
        //Define the panels to be box panels
        labelPanel = new BoxPanel();
        textFieldPanel = new BoxPanel();
        //Add components
        labelPanel.add(redLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0,10)));
        labelPanel.add(greenLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0,10)));
        labelPanel.add(blueLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0,10)));
        textFieldPanel.add(redComponent);
        textFieldPanel.add(greenComponent);
        textFieldPanel.add(blueComponent);
        
        //Add the panels to this parent panel
        this.add(labelPanel);
        this.add(textFieldPanel);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    /**
     * Defines a new jpanel to hold other components within the parent panel, specifically designed for a box layout
     */
    private class BoxPanel extends JPanel{
        public BoxPanel(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
        }
    }
}