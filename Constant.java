package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import java.awt.*;

public final class Constant{
    /**
     * Restrict instantiation
     */
    private Constant(){}
    
    //Set the dimensions of the cell panel
    public static final int CELL_PANEL_HEIGHT = 400;
    public static final int CELL_PANEL_WIDTH = 400;
    
    //Set the dimensions of the control panel
    public static final int CONTROL_PANEL_TAB_CORRECTION = 15;
    public static final int CONTROL_PANEL_HEIGHT = 150;
    public static final int CONTROL_PANEL_WIDTH = CELL_PANEL_WIDTH;
    
    //The size of the cells, length and width of the squares
    public static final int CELL_SIZE = 5;
   
    //Calculate the number of cells that can be drawn vertically and horizontally
    public static final int NUM_CELLS_X = CELL_PANEL_WIDTH/CELL_SIZE;
    public static final int NUM_CELLS_Y = CELL_PANEL_HEIGHT/CELL_SIZE;
    
    //Define the tick time of the game timer
    public static final int TICK_SPEED = 100; //milliseconds
    
    //Define the folder that all saves and loads will be stored/loaded
    public static final String SAVE_DIRECTORY = "Conway/Saves";
    
    //Hold the color of Cells
    public static Color CELL_COLOR = new Color(0,0,0);
}
