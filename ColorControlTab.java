package Conway;

/**
 * Hayden McAlister
 * September 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import Conway.Constant;

public class ColorControlTab extends JPanel{
    int[] currentColor = {Constant.CELL_COLOR.getRed(), Constant.CELL_COLOR.getGreen(), Constant.CELL_COLOR.getBlue()};
    JTextField redText, greenText, blueText;
    JLabel redLabel, greenLabel, blueLabel;
    JSlider redSlider, greenSlider, blueSlider;
    boolean updatingValues = false;
    
    public ColorControlTab(){
        //Set layout and insets to be consistant for each color
        this.setLayout(new GridLayout(3,3,0,10));
        //Set up listeners for components
        ColorTextListener colorTextListener = new ColorTextListener();
        ColorSliderListener colorSliderListener = new ColorSliderListener();
        
        //Set the labels and text fields
        redLabel = new JLabel("Red: ");
        redLabel.setOpaque(true);
        redLabel.setBackground(new Color(255,128,128));
        greenLabel = new JLabel("Green: ");
        greenLabel.setOpaque(true);
        greenLabel.setBackground(new Color(128,255,128));
        blueLabel = new JLabel("Blue: ");
        blueLabel.setOpaque(true);
        blueLabel.setBackground(new Color(128,128,255));
        
        //Set the textfields and add action listener
        redText = new JTextField(Integer.toString(currentColor[0]),3);
        redText.getDocument().addDocumentListener(colorTextListener);
        greenText = new JTextField(Integer.toString(currentColor[1]),3);
        greenText.getDocument().addDocumentListener(colorTextListener);
        blueText = new JTextField(Integer.toString(currentColor[2]),3);
        blueText.getDocument().addDocumentListener(colorTextListener);
        
        //set the sliders
        redSlider = new JSlider(0, 255, currentColor[0]);
        redSlider.setBackground(new Color(255,128,128));
        redSlider.addChangeListener(colorSliderListener);
        greenSlider = new JSlider(0, 255, currentColor[1]);
        greenSlider.setBackground(new Color(128,255,128));
        greenSlider.addChangeListener(colorSliderListener);
        blueSlider = new JSlider(0, 255, currentColor[2]);
        blueSlider.setBackground(new Color(128,128,255));
        blueSlider.addChangeListener(colorSliderListener);
        
        //Add components
        this.add(redLabel);
        this.add(redText);
        this.add(redSlider);
        this.add(greenLabel);
        this.add(greenText);
        this.add(greenSlider);
        this.add(blueLabel);
        this.add(blueText);
        this.add(blueSlider);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    private class ColorSliderListener implements ChangeListener{
        public void stateChanged(ChangeEvent event){
            if(updatingValues){ //change event is simply due to change in text fields
                updatingValues = false; //resest boolean
            } else {
                updatingValues = true; //to stop text fields updating and looping
                
                //We know that change event must be a slider, cast to such
                JSlider sliderChanged = (JSlider) event.getSource();
                int newColorValue = sliderChanged.getValue();
                //Determine what slider has changed and update text fields and cell color
                if(sliderChanged.equals(redSlider)){ //red slider changed
                   redText.setText(Integer.toString(newColorValue)); //update text field
                   Constant.CELL_COLOR = new Color(newColorValue, Constant.CELL_COLOR.getGreen(), Constant.CELL_COLOR.getBlue()); //update cell color
                } else if(sliderChanged.equals(greenSlider)){ //green slider changed
                    greenText.setText(Integer.toString(newColorValue));  //update text field
                    Constant.CELL_COLOR = new Color(Constant.CELL_COLOR.getRed(), newColorValue, Constant.CELL_COLOR.getBlue()); //update cell color
                } else if(sliderChanged.equals(blueSlider)){ //blue slider changed
                    blueText.setText(Integer.toString(newColorValue));  //update text field
                    Constant.CELL_COLOR = new Color(Constant.CELL_COLOR.getRed(), Constant.CELL_COLOR.getGreen(), newColorValue); //update cell color
                } else{
                    //Should not be reachable, a slider other than color sliders changed??
                }
                
                repaint(); //update GUI so text fields get new values
                GamePanel.repaintCells(); //repaint cells so new color is in effect
            }
        }
    }
    
    private class ColorTextListener implements DocumentListener{
        public void insertUpdate(DocumentEvent event){updateFields(event);}
        public void removeUpdate(DocumentEvent event){updateFields(event);}
        public void changedUpdate(DocumentEvent event){updateFields(event);}
        private void updateFields(DocumentEvent event){
            if(updatingValues){ //change event is simply due to change in sliders
                updatingValues = false; //resest boolean
            } else {
                updatingValues = true; //to stop sliders updating and looping
                
                int newColorValue = 0;
                //Get value from textChanged, handle errors
                try{
                    String textChanged =  event.getDocument().getText(0, event.getDocument().getLength());
                    newColorValue=Integer.parseInt(textChanged);
                    if(newColorValue<0 || newColorValue>255){
                        throw new NumberFormatException();
                    }
                } catch(NumberFormatException | javax.swing.text.BadLocationException e){
                    //Set everything to default values
                    newColorValue=0;
                }            
                //Determine what was updated, and perform neccessary updates (sliders and cells)
                if(event.getDocument().equals(redText.getDocument())){ //red text changed
                    redSlider.setValue(newColorValue);  //update slider 
                    Constant.CELL_COLOR = new Color(newColorValue, Constant.CELL_COLOR.getGreen(), Constant.CELL_COLOR.getBlue()); //Update cell color
                } else if(event.getDocument().equals(greenText.getDocument())){ //green text changed
                    greenSlider.setValue(newColorValue);  //update slider
                    Constant.CELL_COLOR = new Color(Constant.CELL_COLOR.getRed(), newColorValue, Constant.CELL_COLOR.getBlue()); //Seriously is this the only way to update colors
                } else if(event.getDocument().equals(blueText.getDocument())){ //blue text changed
                    blueSlider.setValue(newColorValue);  //update slider
                    Constant.CELL_COLOR = new Color(Constant.CELL_COLOR.getRed(), Constant.CELL_COLOR.getGreen(), newColorValue); //There must be a better way to do this...
                } else {
                    //Some other field has been activated, ignore, should never be reached
                }
                repaint(); //update GUI so sliders change
                GamePanel.repaintCells(); //update cell colors
            }
        }
    }
}