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

public class ConwayApp{
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Conway");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        GamePanel panel = new GamePanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
}