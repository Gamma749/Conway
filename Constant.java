package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

public final class Constant{
    /**
     * Restrict instantiation
     */
    private Constant(){}
    
    //Set the constants to be used throughout the project
    public static final int GAME_PANEL_HEIGHT = 400;
    public static final int GAME_PANEL_WIDTH = 400;
    
    //The size of the cells, length and width of the squares
    public static final int CELL_SIZE = 20;
   
    //Calculate the number of cells that can be drawn vertically and horizontally
    public static final int NUM_CELLS_X = GAME_PANEL_WIDTH/CELL_SIZE;
    public static final int NUM_CELLS_Y = GAME_PANEL_HEIGHT/CELL_SIZE;
}
