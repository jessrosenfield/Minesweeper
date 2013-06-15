import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import java.lang.Math;
import java.awt.image.*;
import javax.imageio.*;
import java.io.InputStream;
/**
 * The painter class draws the minesweeper grid and everything in it.
 * 
 * @author Thomas Finch
 * @version 5/24/10
 */
public class Painter extends JComponent
{
   private static ArrayList<Rectangle2D.Double> grid = new ArrayList<Rectangle2D.Double>();
   public static double winWidth, winHeight; // initializes the variables for the grid and jframe width and height, in pixels
   
   /**
    * The paintComponent method paints the entire minesweeper game.
    * @param A graphics object
    */
   public void paintComponent (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;  // Set up graphics.
        
        if (grid.size() == 0) // fills the grid if it's empty
            resetGrid(); 
            
        if (Main.readyToPaint) // makes sure everything is ready to begin painting before painting
        {
        for (int i = 0; i < grid.size(); i++) // does this for every square in the grid
        {
            if (Main.blocks.get(i).isHidden())
                g2.setColor(Color.LIGHT_GRAY); // paints a square light gray if it's hidden
            else
                g2.setColor(Color.WHITE); // otherwise (if it's not hidden), paint it white
            g2.fill(grid.get(i)); // fill in the squares
            g2.setColor(Color.BLACK); // set the color to black and draw the squares (outlines)
            g2.draw(grid.get(i));
        }
        
        for (int i = 0; i < Main.blocks.size(); i++) // do this for every block in the grid
        {
            if (!Main.blocks.get(i).isHidden()) // does this only if the square isn't hidden
            {
            Rectangle2D.Double block = grid.get(i); // store the current grid square as a variable
            if (Main.blocks.get(i).isMarked()) // checks if the block is marked as a mine
            {
                g2.setColor(Color.BLACK); // set the color to black
                Image image = Main.flag;
                g2.drawImage(image,  // draws the flag image to the correct square, centered.
                (int)((Main.blocks.get(i).getLocation().getX()*(winWidth/Main.gridWidth)) + (((winWidth/Main.gridWidth)-image.getWidth(null))/2)), 
                (int)((Main.blocks.get(i).getLocation().getY()*(winHeight/Main.gridHeight)) + (((winHeight/Main.gridHeight)-image.getHeight(null))/2)), null);
            }
            else if (Main.blocks.get(i).getNumber() == 1) // does this if the block has the number "1"
            {
                g2.setColor(Color.BLUE); // sets color and draws the number to the square
                g2.drawString("1", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 2) // does this if the block has the number "2"
            {
                g2.setColor(Color.GREEN); // sets color and draws the number to the square
                g2.drawString("2", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 3) // does this if the block has the number "3"
            {
                g2.setColor(Color.RED); // sets color and draws the number to the square
                g2.drawString("3", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 4) // does this if the block has the number "4"
            {
                g2.setColor(new Color(0, 0, 128)); // sets color and draws the number to the square
                g2.drawString("4", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 5) // does this if the block has the number "5"
            {
                g2.setColor(new Color(153, 77, 0)); // sets color and draws the number to the square
                g2.drawString("5", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 6) // does this if the block has the number "6"
            {
                g2.setColor(Color.CYAN); // sets color and draws the number to the square
                g2.drawString("6", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 7) // does this if the block has the number "7"
            {
                g2.setColor(Color.BLACK); // sets color and draws the number to the square
                g2.drawString("7", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == 8) // does this if the block has the number "8"
            {
                g2.setColor(Color.GRAY); // sets color and draws the number to the square
                g2.drawString("8", (float) ((block.getWidth()/2)+block.getX()), (float) ((block.getHeight()/2)+block.getY()));
            }
            else if (Main.blocks.get(i).getNumber() == -1) // does this if the block is a mine
            {
                g2.setColor(Color.BLACK);
                Image image = Main.mine;
                g2.drawImage(image,  // draws the mine image to the correct square, centered.
                (int)((Main.blocks.get(i).getLocation().getX()*(winWidth/Main.gridWidth)) + (((winWidth/Main.gridWidth)-image.getWidth(null))/2)), 
                (int)((Main.blocks.get(i).getLocation().getY()*(winHeight/Main.gridHeight)) + (((winHeight/Main.gridHeight)-image.getHeight(null))/2)), null);
            }
        }
        
        if (Main.gameOver)
        {
            for (Location loc:Main.locsToX)
            {
            }
        }
        }
    }
    }
    
    /**
     * Resets the grid by filling it with new, blank squares with the correct size and location based on the window's height and width.
     */
    public static void resetGrid()
    {
        ArrayList<Rectangle2D.Double> grid1 = new ArrayList<Rectangle2D.Double>(); // makes a new arraylist
        for (int i = 0; i < Main.gridHeight; i++)
            for (int j = 0; j < Main.gridWidth; j++) // fills the new arraylist with blank squares
                grid1.add(new Rectangle2D.Double((winWidth/Main.gridWidth) * i, (winHeight/Main.gridHeight) * j, (winWidth/Main.gridWidth), (winHeight/Main.gridHeight)));
        grid = grid1; // overwrites the current grid.
    }
}
