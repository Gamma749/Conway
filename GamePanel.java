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

public class GamePanel extends JPanel{
    //Create a new 2 dimensional array for all the cells to be held in
    Cell[][] cellArray = new Cell[Constant.NUM_CELLS_X][Constant.NUM_CELLS_Y];
    
    /**
     * Define a constructor to create the game panel and manage the main parts of the application
     */
    public GamePanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Constant.GAME_PANEL_HEIGHT, Constant.GAME_PANEL_WIDTH));
        setBackground(new Color(240,240,255));
        //Add the mouse listeners to check for mouse events
        MyMouseMotionListener mouseMotionListener = new MyMouseMotionListener();
        MyMouseListener mouseListener = new MyMouseListener();
        addMouseMotionListener(mouseMotionListener);
        addMouseListener(mouseListener);
        
        for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
            for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                cellArray[x][y] = new Cell(x*Constant.CELL_SIZE, y*Constant.CELL_SIZE);
            }
        }
    }
    
    /**
     * Override
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Cell[] rowArray:cellArray){//iterate through all the row cell arrays
            for(Cell cell:rowArray){//iterate through all the cells in the row cell arrays
                cell.display(g); //Draw each cell
            }
        }
    }
    
    /**
     * Activate the cell that has been activated
     * @param event the mouse event that triggered on a cell
     */
    private void activateCell(Cell cell){
        cell.changeActivation();
        //repaint the panel
        repaint();
        
    }
    
    private class MyMouseMotionListener implements MouseMotionListener{
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
    }
    
    private class MyMouseListener implements MouseListener{
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
    }

}