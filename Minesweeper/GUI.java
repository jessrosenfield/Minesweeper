import java.awt.*;
import javax.swing.*; 
public class GUI
{
    private static JFrame createWindow()
    {
        JFrame frame = new JFrame("Minesweeper");
        //noframegrid bpanel = new noframegrid(9, 9);
        noframegrid bpanel = new noframegrid(9, 9);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        
        
        frame.setPreferredSize(new Dimension(500, 500));
       
        frame.setLocationRelativeTo(null); 
       frame.add(bpanel);
       frame.setResizable(false);
         frame.pack();
        frame.setVisible(true);
        
       
        return frame;
    }
    public static void main(String[] args)
    {
       //new noframegrid(9,9);
       createWindow();
    }
} 





