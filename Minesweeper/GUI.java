import java.awt.*;
import javax.swing.*; 
public class GUI

{
    private static JFrame createWindow()
    {
        JFrame frame = new JFrame("Minesweeper");
        noframegrid bpanel = new noframegrid(9, 9);
        JButton topbtn = new JButton();
        topbtn.setPreferredSize(new Dimension(500, 50));
        
        frame.add(topbtn, BorderLayout.NORTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setPreferredSize(new Dimension(500, 550));
        
        frame.setLocationRelativeTo(null); 
        frame.add(bpanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    public static void main(String[] args)
    {
       createWindow();
    }
} 





