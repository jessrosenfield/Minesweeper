/*import java.awt.*;
import javax.swing.*; 
public class GUI

{
    private static JFrame createWindow()
    {
        JFrame frame = new JFrame("Minesweeper");
        Game game = new Game();
        noframegrid bpanel = new noframegrid(game.level.rows, game.level.columns);
        JButton topbtn = new JButton();
        topbtn.setPreferredSize(new Dimension(50, 50));
        
        frame.add(topbtn, BorderLayout.NORTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setPreferredSize(new Dimension(50*16, 55*16));
        
        frame.setLocationRelativeTo(null); 
        frame.add(bpanel);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    public static void main(String[] args)
    {
       createWindow();
    }
} 





*/
import java.awt.*;
import javax.swing.*; 
public class GUI
//setPreferredSize(new Dimension(200, 300)
{
   private static JFrame createWindow()
   {
       JFrame frame = new JFrame("Minesweeper");
       //noframegrid bpanel = new noframegrid(9, 9);
       ControlPanel cpanel = new ControlPanel();
       Game game = new Game(Level.ADVANCED);
       int rows = game.level.rows;
       int cols = game.level.columns;
       noframegrid bpanel = new noframegrid(rows, cols);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
       frame.setPreferredSize(new Dimension(56*rows, 56*cols+100));       
       frame.setLocationRelativeTo(null); 
       frame.add(bpanel, BorderLayout.NORTH);
       frame.add(cpanel, BorderLayout.NORTH);
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


