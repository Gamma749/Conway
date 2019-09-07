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
    /**
     * Add the cells to the panel
     * 
     */
    public CellPanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Constant.CELL_PANEL_WIDTH,Constant.CELL_PANEL_HEIGHT));
        setBackground(new Color(240,240,255));
        
        //Add the mouse listeners to check for mouse events
        addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent event) {
                try{
                    activateCell(Cell.cellArray[event.getX()/Constant.CELL_SIZE][event.getY()/Constant.CELL_SIZE]);
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
                    if(currentCell!=Cell.cellArray[event.getX()/Constant.CELL_SIZE][event.getY()/Constant.CELL_SIZE]){ //Check to see if cell was just activated
                        currentCell=Cell.cellArray[event.getX()/Constant.CELL_SIZE][event.getY()/Constant.CELL_SIZE]; //Update the most recent cell
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
                Cell.cellArray[x][y] = new Cell(x*Constant.CELL_SIZE, y*Constant.CELL_SIZE);
            }
        }
    }
    
    //Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Cell.displayAll(g);
    }
    
    /**
     * Activate the cell by changing activation and repaint the panel
     * @param event the mouse event that triggered on a cell
     */
    private void activateCell(Cell cell){
        cell.toggleState();
        //repaint the panel
        repaint();
    }
    
}
