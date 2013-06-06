 import java.awt.*; 
 import javax.swing.*; 
 public class GUI
 { 
    private static void createWindow() {
       JFrame frame = new JFrame("Minesweeper");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       JLabel textLabel = new JLabel("Minesweeper", SwingConstants.CENTER); 
       textLabel.setPreferredSize(new Dimension(500, 500)); 
       frame.getContentPane().add(textLabel, BorderLayout.NORTH); 
       frame.setLocationRelativeTo(null); 
       frame.pack();
       frame.setVisible(true);
    }
    public static void main(String[] args) {
       createWindow();
    }
 } 





import java.awt.*;
import javax.swing.*; 
public class GUI
{
    private static JFrame createWindow()
    {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        JLabel textLabel = new JLabel("Minesweeper", SwingConstants.CENTER); 
        textLabel.setPreferredSize(new Dimension(600, 500)); 
        frame.getContentPane().add(textLabel, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
        frame.pack();
        new ButtonGrid(9,9);
        return frame;
    }
    public static void main(String[] args)
    {
       new ButtonGrid(9,9);
    }
} 




