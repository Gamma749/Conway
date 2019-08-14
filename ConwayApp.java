package Conway;
/**
 * Hayden McAlister
 * CONWAY GAME OF LIFE
 * Aug 2019
 */

import javax.swing.*;

public class ConwayApp{
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Conway");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(new GamePanel());
        frame.pack();
        frame.setVisible(true);
    }
    
}