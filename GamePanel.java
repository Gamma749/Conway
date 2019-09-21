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
    //Create data fields for the class, so these objects are available in all methods
    private static JPanel cellPanel;
    private JPanel controlPanel;
    private static javax.swing.Timer timer; //A timer to tick the game and cells
    private static boolean tickFlag=false;
    
    /**
     * Define a constructor to create the game panel and manage the main parts of the application
     */
    public GamePanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Math.max(Constant.CELL_PANEL_WIDTH, Constant.CONTROL_PANEL_WIDTH), Constant.CELL_PANEL_HEIGHT+Constant.CONTROL_PANEL_HEIGHT+Constant.CONTROL_PANEL_TAB_CORRECTION));
        setBackground(new Color(180,180,200));
        
        //Define the timer
         timer = new javax.swing.Timer(Constant.TICK_SPEED, new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Cell.nextGeneration();
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
    
    /**
     * Rerender the cell panel so cells are updated
     */
    public static void repaintCells(){
        cellPanel.repaint();
    }
}