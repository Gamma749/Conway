package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import Conway.Constant;

public class ControlPanel extends JPanel{
    JTabbedPane tabPane = new JTabbedPane();
    private javax.swing.Timer timer;
    private JLabel saveLabel;
    private JTextField saveNameTextEntry;
    
    public ControlPanel(javax.swing.Timer timer){
        //Set the dimensions of the panel
        setPreferredSize(new Dimension(Constant.CONTROL_PANEL_WIDTH, Constant.CONTROL_PANEL_HEIGHT));
        //Set the background color
        setBackground(new Color(200,200,235));
        
        //Create the panes from objects
        JPanel mainControlTab = new MainControlTab(timer);
        JPanel fileControlTab = new FileControlTab();
        
        tabPane.addTab("Main",mainControlTab); //The main panel, for timer and clear buttons
        tabPane.addTab("File", fileControlTab); //The file panel, for save/load functionality
        tabPane.setBackground(new Color(200,200,235));
        this.add(tabPane); //add the tabPane to the controlPanel so it may be used
    }
    
    
}