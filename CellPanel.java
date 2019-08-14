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

public class CellPanel extends JPanel{
    //Add UID
    private static final long serialVersionUID = 2L;
    //Create a new 2 dimensional array for all the cells to be held in
    Cell[][] cellArray = new Cell[Constant.NUM_CELLS_X][Constant.NUM_CELLS_Y];
    //hold the next state of each cell
    Boolean[][] nextStateArray = new Boolean[Constant.NUM_CELLS_X][Constant.NUM_CELLS_Y];
    
    public javax.swing.Timer timer;
    
    /**
     * Add the cells to the panel
     */
    public CellPanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Constant.CELL_PANEL_WIDTH,Constant.CELL_PANEL_HEIGHT));
        setBackground(new Color(240,240,255));
        
        //Create the timer
        timer = new javax.swing.Timer(Constant.TICK_SPEED, new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //iterate through each cell, check neighbours and tick cell
                for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
                    for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                        tickCell(cellArray[x][y]);
                    }
                }
                
                //After ticking all cells, update each state according to the nextStateArray
                for(int x=0; x<Constant.NUM_CELLS_X; x++){ //iterate through each row
                    for(int y=0; y<Constant.NUM_CELLS_Y; y++){ //iterate through each column
//                        System.out.println(x+" "+y+" "+nextStateArray[x][y]);
                        activateCell(cellArray[x][y], nextStateArray[x][y]);
                    }
                }
            }
        });
        
        //Add the mouse listeners to check for mouse events
        addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent event) {
                try{
                    activateCell(cellArray[event.getX()/Constant.CELL_SIZE][event.getY()/Constant.CELL_SIZE]);
                } catch (ArrayIndexOutOfBoundsException e){
                    //Do nothing, event is simply outside array of cells
                }
            }                
            //Override remaining methods
            public void mouseExited(MouseEvent event) {}                
            public void mouseEntered(MouseEvent event) {}
            public void mousePressed(MouseEvent event) {}
            public void mouseReleased(MouseEvent event) {}
        });
        addMouseMotionListener(new MouseMotionListener(){
            //Update a cell's filled flag when clicked
            Cell currentCell = null; //keep track of what cell has last been dragged on, to avoid many activations of same cell
            public void mouseDragged(MouseEvent event){
                try{//Ensure event occurs in cell array
                    if(currentCell!=cellArray[event.getX()/Constant.CELL_SIZE][event.getY()/Constant.CELL_SIZE]){ //Check to see if cell was just activated
                        currentCell=cellArray[event.getX()/Constant.CELL_SIZE][event.getY()/Constant.CELL_SIZE]; //Update the most recent cell
                        activateCell(currentCell); //Activate the cell
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    //Do nothing, event is simply outside array of cells
                }
            }
            public void mouseMoved(MouseEvent event){} //Override
        });
        
        //Add the cells
        for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
            for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                cellArray[x][y] = new Cell(x*Constant.CELL_SIZE, y*Constant.CELL_SIZE);
            }
        }
    }
    
    //Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Cell[] rowArray:cellArray){//iterate through all the row cell arrays
            for(Cell cell:rowArray){//iterate through all the cells in the row cell arrays
                cell.display(g); //Draw each cell
            }
        }
    }
    
    /**
     * Activate the cell by changing activation and repaint the panel
     * @param event the mouse event that triggered on a cell
     */
    private void activateCell(Cell cell){
        cell.changeActivation();
        //repaint the panel
        repaint();
    }
    
    /**
     * Activate the cell and set the exact state
     * @param event the mouse event that triggered on a cell
     * @param state the new activation states
     */
    private void activateCell(Cell cell, boolean state){
        cell.setActive(state);
        //repaint the panel
        repaint();
    }
    
    /**
     * Count how many active neighbours a cell has
     * @param cell the cell to check
     * @return the number of neighbours the cell has
     */
    public int neighbourCount(Cell cell){
        int xIndex = cell.getX()/Constant.CELL_SIZE;
        int yIndex = cell.getY()/Constant.CELL_SIZE;
        int neighbourCount = 0;
        //Cycle through every neighbour
        for(int x=xIndex-1; x<=xIndex+1; x++){
            for(int y=yIndex-1; y<=yIndex+1; y++){
                if(x==xIndex && y==yIndex){//Do not search current cell
                    continue;
                }
                try{
                    if(cellArray[x][y].getActive()){ //Check if the cell is active
                        neighbourCount++;
                    }
                } catch(ArrayIndexOutOfBoundsException e){ //We are checking a cell on the edge, let all edges be inactive
                    //Continue on
                }
            }
        }
        return neighbourCount;
    }
    
    /**
     * Changes cell over time in accourdance to its neighbour count
     * @param neighbourCount the number of neighbours this cell has
     */
    public void tickCell(Cell cell){
        int neighbourCount = neighbourCount(cell);
        if(!cell.getActive() && neighbourCount == 3){
            //Cell can become active if exactly three neighbours surround it
            nextStateArray[cell.getX()/Constant.CELL_SIZE][cell.getY()/Constant.CELL_SIZE] = true;
        } else if(cell.getActive() && neighbourCount<2){
            //An active cell with less than 2 neighbours dies
            nextStateArray[cell.getX()/Constant.CELL_SIZE][cell.getY()/Constant.CELL_SIZE] = false;
        } else if (cell.getActive() && neighbourCount>3){
            //An active cell with more than 3 neighbours dies
            nextStateArray[cell.getX()/Constant.CELL_SIZE][cell.getY()/Constant.CELL_SIZE] = false;
        } else if (cell.getActive()){
            //An active cell with 2 or 3 neighbours lives
            nextStateArray[cell.getX()/Constant.CELL_SIZE][cell.getY()/Constant.CELL_SIZE] = true;
        } else {
            //Let all other cells die
            nextStateArray[cell.getX()/Constant.CELL_SIZE][cell.getY()/Constant.CELL_SIZE] = false;
        }
//        System.out.println(cell.getX()/Constant.CELL_SIZE+ " "+ nextStateArray[cell.getX()/Constant.CELL_SIZE][cell.getY()/Constant.CELL_SIZE]);
    }
    
    public void clearArray(){
        for(Cell[] row:cellArray){ //iterate through each row
            for(Cell cell:row){ //iterate through each cell in a row
                activateCell(cell, false);
            }
        }
    }
}
