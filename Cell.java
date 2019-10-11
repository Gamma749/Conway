package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import java.awt.*;
import Conway.Constant;
import java.util.Random;

public class Cell{
    //Declare the public static arrays to hold cells and states
    //Create a new 2 dimensional array for all the cells to be held in
    private static Cell[][] cellArray = new Cell[Constant.NUM_CELLS_X][Constant.NUM_CELLS_Y];
    //hold the next state of each cell
    private static Boolean[][] nextStateArray = new Boolean[Constant.NUM_CELLS_X][Constant.NUM_CELLS_Y];
    
    //Declare datafields
    private int xCoord, yCoord, xIndex, yIndex; //Coordinates of cell
    private boolean state; //Status of life of cell
    private int size; //The length and width of a cell
    /**
     * Create a cell object and set the x and y coordinates of it
     * @param xCoord the integer x coordinate of the cell, measured to top left corner
     * @param yCoord the integer y coordinate of the cell, measured to top left corner
     */
    public Cell(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        state = false;
        size = Constant.CELL_SIZE;
        xIndex = xCoord/size;
        yIndex = yCoord/size;
    }
    
    /**
     * Get the cell from a specified x and y index
     * @param xIndex the x index of the cell in the cellArray
     * @param yIndex the y index of the cell in the cellArray
     * @return the cell at these indices
     */
    public static Cell getCell(int xIndex, int yIndex){
        try{
            return cellArray[xIndex][yIndex]; //Call the nonstatic toggle state on this cell
        } catch(ArrayIndexOutOfBoundsException e){
            //Cell requested is out of bounds, cannot continue without returning cell
            //Return cell (0,0) and print error
            System.err.println(e);
            return cellArray[0][0];
        }
    }
    
    /**
     * populate the cell array with cells
     */
    public static void initalise(){
        for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
            for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                Cell.cellArray[x][y] = new Cell(x*Constant.CELL_SIZE, y*Constant.CELL_SIZE);
            }
        }   
    }
    
    /**
     * Gets the x position of this cell in array
     * @return the x coordinate of the cell
     */
    public int getXCoord(){
        return this.xCoord;
    }
    
    /**
     * Gets the y position of this cell in array
     * @return the y coordinate of the cell
     */
    public int getYCoord(){
        return this.yCoord;
    }
    
    /**
     * Get the xIndex of the cell in CellArray
     * @return the x index of the cell
     */
    public int getXIndex(){
        return this.xIndex;
    }
    
    /**
     * Get the yIndex of the cell in CellArray
     * @return the y index of the cell
     */
    public int getYIndex(){
        return this.yIndex;
    }
    
    
    /**
     * Get the activity state of the cell
     */
    public boolean getState(){
        return this.state;
    }
    
    /**
     * Set the active state of this cell
     * @param state the state to set this cell to
     */
    public void setState(boolean state){
        this.state=state;
    }
    
    /**
     * Changes the activation flag of cell
     */
    public void toggleState(){
        this.state = !state;
    }
    
    /**
     * Override the togglestate method to toggle a particular cell in the cellArray, continue if our of range
     * @param xIndex the x index of the cell in the cell array
     * @param yIndex the y index of the cell in the cell array
     */
    public static void toggleState(int xIndex, int yIndex){
        try{
            cellArray[xIndex][yIndex].toggleState(); //Call the nonstatic toggle state on this cell
        } catch(ArrayIndexOutOfBoundsException e){
            //Continue, do nothing if out of range somehow
        }
    }
    
    /**
     * Randomise the cell array
     */
    public static void randomiseAll(){
        Random random = new Random();
        for (int x=0; x<Constant.NUM_CELLS_X; x++){//iterate through all the row cell arrays
            for(int y=0; y<Constant.NUM_CELLS_Y; y++){//iterate through all the cells in the row cell arrays
                cellArray[x][y].setState(random.nextBoolean());
            }
        }
    }
    
    /**
     * Clear the cell array by setting all cell states to false
     */
    public static void clearCellArray(){
        for (int x=0; x<Constant.NUM_CELLS_X; x++){//iterate through all the row cell arrays
            for(int y=0; y<Constant.NUM_CELLS_Y; y++){//iterate through all the cells in the row cell arrays
                cellArray[x][y].setState(false);
            }
        }
    }
    
    /**
     * Clear state array by setting all enteries to false
     */
    public static void clearStateArray(){
        for (int x=0; x<Constant.NUM_CELLS_X; x++){//iterate through all the row cell arrays
            for(int y=0; y<Constant.NUM_CELLS_Y; y++){//iterate through all the cells in the row cell arrays
                nextStateArray[x][y]=false;
            }
        }
    }
    
    /**
     * Displays the cell at the given coordinates
     * @param g the graphics object used to draw the cell
     */
    public void display(Graphics g){
        if(state){ //Draw a counter in the cell if that cell is filled
            g.setColor(Constant.CELL_COLOR);
            //Counter
//            g.fillOval((int)(xCoord+(size*0.15)), (int)(yCoord+(size*0.15)), (int)(size*0.7), (int)(size*0.7));
            //Fill square
            g.fillRect(xCoord, yCoord, size, size);
        }
        //Draw the box around the cell
        g.setColor(new Color(128,128,128));
        g.drawRect(xCoord, yCoord, size, size); //Draw the square representing the cell
    }
    
    /**
     * redraw ALL cells
     * @param g the graphics object to draw the cells
     */
    public static void displayAll(Graphics g){
        for(int x=0; x<Constant.NUM_CELLS_X; x++){ //iterate through each row
            for(int y=0; y<Constant.NUM_CELLS_Y; y++){ //iterate through each column
                cellArray[x][y].display(g);
            }
        }
    }
    
    /**
     * Count how many active neighbours a cell has
     * @param cell the cell to check
     * @return the number of neighbours the cell has
     */
    private int neighbourCount(){
        int neighbourCount = 0;
        //Cycle through every neighbour
        for(int x=xIndex-1; x<=xIndex+1; x++){ //Check each neighbour in x direciton
            for(int y=yIndex-1; y<=yIndex+1; y++){ //Check each neighbour in y direction
//                System.out.println(x+", "+y);
                if(x==xIndex && y==yIndex)continue;//Do not search current cell
                else if(Cell.cellArray[mod(x, Constant.NUM_CELLS_X)][mod(y, Constant.NUM_CELLS_Y)].getState()){
                    try{
                        neighbourCount++;
                    } catch(ArrayIndexOutOfBoundsException e){
                        System.err.println(e);
                        System.err.println(x+", "+y);
                    }
                }
            }
        }
        return neighbourCount;
    }
    
    
    /**
     * Take a raw integer and return the integer shifted to be in the range given
     * eg modFunc(-1,100) will return 99
     * @param rawInt the unshifted integer
     * @param modulus the value to shift to (between 0 (inclusive) and modulus (exclusive))
     * @return the shifted integer
     */
    private int mod(int rawInt, int modulus){
        int currentInt = rawInt%modulus; //shift the input int to be in the right range
        if (currentInt<0){ //deal with negatives
            currentInt += modulus; //shift to postive domain
        }
        return currentInt;
    }
    
    /**
     * Changes cell over time in accourdance to its neighbour count
     */
    private void tickCell(){
        int neighbourCount = neighbourCount();
        if(!getState() && neighbourCount == 3){
            //Cell can become active if exactly three neighbours surround it
            nextStateArray[getXIndex()][getYIndex()] = true;
        } else if(getState() && neighbourCount<2){
            //An active cell with less than 2 neighbours dies
            nextStateArray[getXIndex()][getYIndex()] = false;
        } else if (getState() && neighbourCount>3){
            //An active cell with more than 3 neighbours dies
            nextStateArray[getXIndex()][getYIndex()] = false;
        } else if (getState()){
            //An active cell with 2 or 3 neighbours lives
            nextStateArray[getXIndex()][getYIndex()] = true;
        } else {
            //Let all other cells die
            nextStateArray[getXIndex()][getYIndex()] = false;
        }
//        System.out.println(getXIndex()+ " "+ nextStateArray[getXIndex()][getYIndex()]);
    }
    
    /**
     * Iterate through all cells and update nextState and then cell array
     */
    public static void nextGeneration(){
        //iterate through each cell, check neighbours and tick cell
        for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
            for(int y=0; y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                cellArray[x][y].tickCell();
            }
        }
        
        //After ticking all cells, update each state according to the nextStateArray
        for(int x=0; x<Constant.NUM_CELLS_X; x++){ //iterate through each row
            for(int y=0; y<Constant.NUM_CELLS_Y; y++){ //iterate through each column
                cellArray[x][y].setState(nextStateArray[x][y]);
            }
        }   
    }
    
}