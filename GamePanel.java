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
    private static final long serialVersionUID = 1L;
    //Create data fields for the class, so these objects are available in all methods
    private JPanel cellPanel;
    private JPanel controlPanel;
    private static javax.swing.Timer timer; //A timer to tick the game and cells
    private static boolean tickFlag=false;
    
    /**
     * Define a constructor to create the game panel and manage the main parts of the application
     */
    public GamePanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Constant.CELL_PANEL_WIDTH, Constant.CELL_PANEL_HEIGHT+Constant.CONTROL_PANEL_HEIGHT));
        setBackground(new Color(180,180,200));
        
        //Define the timer
         timer = new javax.swing.Timer(Constant.TICK_SPEED, new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //iterate through each cell, check neighbours and tick cell
                for(int x=0; x<Constant.NUM_CELLS_X; x++){ //Iterate through all the possible cells in the x direction
                    for(int y=0;y<Constant.NUM_CELLS_Y; y++){ //Iterate through all possible cells in the y direction
                        Cell.cellArray[x][y].tickCell();
                    }
                }
                
                //After ticking all cells, update each state according to the nextStateArray
                for(int x=0; x<Constant.NUM_CELLS_X; x++){ //iterate through each row
                    for(int y=0; y<Constant.NUM_CELLS_Y; y++){ //iterate through each column
                        Cell.cellArray[x][y].setState(Cell.nextStateArray[x][y]);
                    }
                }
                if(tickFlag){//indicates timer was only supposed to pulse
                    timer.stop();
                    tickFlag=false;
                }
                repaint();
            }
        });
        
        //Create and add the panels
        cellPanel = new CellPanel();
        controlPanel = new ControlPanel(timer);
        //Add panels
        add(cellPanel);
        add(controlPanel);
    }
    
    //Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    /**
     * Tick the timer by one pulse 
     */
    public static void tick(){
        tickFlag = true;
        timer.restart();
    }
}