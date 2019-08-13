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

public class Cell{
    //Declare datafields
    private int xCoord, yCoord; //Coordinates of cell
    private boolean active; //Status of life of cell
    private int size; //The length and width of a cell
    /**
     * Create a cell object and set the x and y coordinates of it
     * @param xCoord the integer x coordinate of the cell, measured to top left corner
     * @param yCoord the integer y coordinate of the cell, measured to top left corner
     */
    public Cell(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        active = false;
        size = Constant.CELL_SIZE;
    }
    
    /**
     * Changes the activation flag of cell
     */
    public void changeActivation(){
        this.active = !this.active;
    }
    
    /**
     * Displays the cell at the given coordinates
     * @param g the graphics object used to draw the cell
     */
    public void display(Graphics g){
        g.setColor(new Color(128,128,128));
        g.drawRect(xCoord, yCoord, size, size); //Draw the square representing the cell
        if(active){ //Draw a counter in the cell if that cell is filled
            g.setColor(Color.black);
            g.fillOval((int)(xCoord+(size*0.15)), (int)(yCoord+(size*0.15)), (int)(size*0.7), (int)(size*0.7));
        }
    }
}