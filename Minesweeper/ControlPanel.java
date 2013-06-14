import javax.imageio.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.IOException;
public class ControlPanel extends JPanel
{
    static JPanel panel = new JPanel();
    JButton leftbtn = new JButton();
    JButton rightbtn = new JButton();
    public ControlPanel()
    {
      leftbtn.setPreferredSize(new Dimension(250, 50));
      rightbtn.setPreferredSize(new Dimension(250, 50));
      panel.add(leftbtn, BorderLayout.EAST);
      panel.add(rightbtn, BorderLayout.WEST);
      panel.setVisible(true);
      panel.setSize(new Dimension(500,500));
    }  
        public static void main(String[] args)
    {
        new ControlPanel();
    }
}


