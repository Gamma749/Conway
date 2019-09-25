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
    private static JTabbedPane controlTabbedPanel;
    private static javax.swing.Timer timer; //A timer to tick the game and cells
    private static boolean tickFlag=false;
    
    /**
     * Define a constructor to create the game panel and manage the main parts of the application
     */
    public GamePanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Math.max(Constant.CELL_PANEL_WIDTH, Constant.CONTROL_PANEL_WIDTH), 
                                       Constant.CELL_PANEL_HEIGHT+Constant.CONTROL_PANEL_HEIGHT+Constant.CONTROL_PANEL_TAB_CORRECTION));
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
        
        //Create and add the cell panel
        cellPanel = new CellPanel();
        add(cellPanel);
        
        //Create the Control Panel
        controlTabbedPanel = new JTabbedPane();
        //Set dimensions using Constant
        controlTabbedPanel.setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        controlTabbedPanel.setBackground(new Color(200,200,235));
        
        //Create the panes from objects to add to control panel
        JPanel mainControlTab = new MainControlTab(timer);
        JPanel fileControlTab = new FileControlTab();
        JPanel colorControlTab = new ColorControlTab();
        
        //Add panes to control panel
        controlTabbedPanel.addTab("Main",mainControlTab); //The main panel, for timer and clear buttons
        controlTabbedPanel.addTab("File", fileControlTab); //The file panel, for save/load functionality
        controlTabbedPanel.addTab("Color", colorControlTab); //The color panel, for customising the color of the cells
        
        this.add(controlTabbedPanel); //add the tabPane to the GamePanel so it may be used
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