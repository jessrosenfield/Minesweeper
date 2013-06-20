import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
 
public class ColoredButtonsDemo {
 
  public static void main(String[] args) throws Exception {
    SwingUtilities.invokeLater(new Runnable() {
 
      @Override
      public void run() {
        new ColoredButtonsDemo().makeUI();
      }
    });
  }
 
  public void makeUI() {
    JButton coloredButton1 = new JButton("One");
    coloredButton1.setUI((ButtonUI) BasicButtonUI.createUI(coloredButton1));
    coloredButton1.setBackground(Color.CYAN);
 
    JButton coloredButton2 = new JButton("Two");
    coloredButton2.setIcon(new Icon() {
 
      @Override
      public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
      }
 
      @Override
      public int getIconWidth() {
        return 0;
      }
 
      @Override
      public int getIconHeight() {
        return 0;
      }
    });
 
    JButton coloredButton3 = new JButton("Three") {
 
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 0, 255, 128)); // semi transparent magenta
        g.fillRect(0, 0, getWidth(), getHeight());
      }
    };
 
    JPanel panel = new JPanel();
    panel.add(coloredButton1);
    panel.add(coloredButton2);
    panel.add(coloredButton3);
    JFrame frame = new JFrame();
    frame.add(panel);
 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}