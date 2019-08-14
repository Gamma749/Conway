package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import java.awt.*;
import javax.swing.*;
import Conway.Constant;

public class GamePanel extends JPanel{
    private static final long serialVersionUID = 1L;
    JPanel cellPanel;
    JPanel controlPanel;
    
    /**
     * Define a constructor to create the game panel and manage the main parts of the application
     */
    public GamePanel(){
        //Set the size and background of the game panel
        setPreferredSize(new Dimension(Constant.CELL_PANEL_WIDTH, Constant.CELL_PANEL_HEIGHT+Constant.CONTROL_PANEL_HEIGHT));
        setBackground(new Color(140,140,155));
        
        //Create and add the panels
        cellPanel = new CellPanel();
        controlPanel = new ControlPanel(cellPanel);
        //Add panels
        add(cellPanel);
        add(controlPanel);
    }
    
    //Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    //Create a new class that will house the controls for the game
    
}