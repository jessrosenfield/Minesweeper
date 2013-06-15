import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.io.InputStream;
/**
 * The main class starts and runs the majority of the minesweeper game.
 * 
 * @author Thomas Finch
 * @version 5/24/10
 */
public class Main implements ActionListener
{
   public static final JFrame mainWin = new JFrame();
   public static ArrayList<Block> blocks = new ArrayList<Block>();
   public static int gridWidth = 10, gridHeight = 10, mines = 20, numMarked = 0; // initalize game variables
   public static ArrayList<Location> mineLocs, unhiddenLocs = new ArrayList<Location>(), locsToX = new ArrayList<Location>();
   public static boolean readyToPaint = false, gameOver = false;
   public static Image mine = null, flag = null;
   
   /**
    * Starts the game.
    */
   public static void main(String[] args)
   {
       Main main = new Main(); // makes a new main object, then executes the main method (have to do because the main method couldn't be static)
       main.start();
    }
    
    /**
     * Does most of the game processing and logic, except for painting the game.
     */
   public void start()
   {
       InputStream mineIS = getClass().getClassLoader().getResourceAsStream("mine.png");
       InputStream flagIS = getClass().getClassLoader().getResourceAsStream("flag.png");
       try
       {
            mine = ImageIO.read(mineIS);
            flag = ImageIO.read(flagIS);
        }
       catch (Exception e)
        {
             e.printStackTrace();
          }
       JMenuBar menuBar = new JMenuBar();
       JMenu gameMenu = new JMenu("Game");
       JMenu helpMenu = new JMenu("Help"); // makes a new menu bar
       final JMenuItem newGame = new JMenuItem("New");
       final JMenuItem changeSize = new JMenuItem("Change size"); // creates menu items
       final JMenuItem exit = new JMenuItem("Exit");
       final JMenuItem help = new JMenuItem("How to play");
       final JMenuItem about = new JMenuItem("About");
       newGame.addActionListener(this);
       changeSize.addActionListener(this); // adds action listeners for the menu items
       exit.addActionListener(this);
       help.addActionListener(this);
       about.addActionListener(this);
       gameMenu.add(newGame);
       gameMenu.add(changeSize);
       gameMenu.add(exit); 
       helpMenu.add(help);
       helpMenu.add(about); // adds the menus to the menu bar
       menuBar.add(gameMenu);
       menuBar.add(helpMenu);
       mainWin.setJMenuBar(menuBar);
       
       mainWin.setTitle("Minesweeper");
       mainWin.setSize(500, 500);
       final Painter painter = new Painter(); // sets attributes for the main window
       mainWin.add(painter);
       mainWin.setLocationRelativeTo(null);
       mainWin.setIconImage(mine);
       mainWin.setVisible(true);
       mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Painter.winWidth = SwingUtilities.convertPoint(mainWin, new Point(mainWin.getWidth(), mainWin.getHeight()), painter).getX(); // calculates the window's height and width in pixels for the painter
       Painter.winHeight = SwingUtilities.convertPoint(mainWin, new Point(mainWin.getWidth(), mainWin.getHeight()), painter).getY();
       
       class WindowListener implements ComponentListener
       {
           public void componentHidden(ComponentEvent e) {}

           public void componentMoved(ComponentEvent e) {}

           public void componentResized(ComponentEvent e) {
               Painter.winWidth = SwingUtilities.convertPoint(mainWin, new Point(mainWin.getWidth(), mainWin.getHeight()), painter).getX();
               Painter.winHeight = SwingUtilities.convertPoint(mainWin, new Point(mainWin.getWidth(), mainWin.getHeight()), painter).getY();
               Painter.resetGrid(); // listens for window resizes, then repaints the grid to the proper size to fit the window
               mainWin.repaint();
            }

            public void componentShown(ComponentEvent e) {}
        }
        
        class mouseListener implements MouseListener
        {
            public void mousePressed(MouseEvent e)
            {
                MouseEvent event = SwingUtilities.convertMouseEvent(mainWin, e, painter);
                double clickX = event.getX();
                double clickY = event.getY(); // stores click coordinates
                Location loc = getClickLocation(event.getX(), event.getY()); // gets click location in the minesweeper grid
                if (!gameOver) // does this if the game is still going
                {
                if (e.getButton() == MouseEvent.BUTTON3 && !getBlockAt(loc).isMarked()) // checks if the click was a right click and if the clicked block isn't marked
                {
                    if (getBlockAt(loc).isHidden()) // checks if the block is hidden
                    {
                    getBlockAt(loc).mark(); // marks the block as a flag, increases the number of marked blocks, and unhides the block
                    numMarked++;
                    getBlockAt(loc).unhide();
                }
                }
                else if (e.getButton() == MouseEvent.BUTTON3 && getBlockAt(loc).isMarked()) // checks if the click is a right click, and if the block is marked
                {
                    getBlockAt(loc).unMark(); // unmarks the block, decrements the number of marked blocks, and hides it (marking a block unhides it, so it must be rehidden)
                    numMarked--;
                    getBlockAt(loc).hide();
                }
                else
                {
                getBlockAt(loc).unhide();  // unhides the block
                if (getBlockAt(loc).isMine()) // checks if the block is a mine
                {
                    if (!getBlockAt(loc).isMarked()) // if it isn't marked
                    {
                    gameOver = true; // end the game, and display a message to the user, and unhide all the blocks
                    new JOptionPane().showMessageDialog(mainWin, "Game over.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    for (Block block:blocks)
                    {
                        block.unhide();
                        if (block.isMarked() && !block.isMine())
                            locsToX.add(block.getLocation());
                    }
                }
                }
                if (getBlockAt(loc).getNumber() == 0) // unhides all adjacent blocks if the number is a zero
                    unhideAllAdjacent(loc);
                }
                mainWin.repaint(); // repaints
                if (numMarked == mines && markedLocsAreMines()) // checks if there are equal marked locations and mines, and checks if the marked locations are the mines
                {
                    new JOptionPane().showMessageDialog(mainWin, "You won!", "Win", JOptionPane.INFORMATION_MESSAGE); // alert the user that they won
                }
            }
            }
            public void mouseReleased(MouseEvent event){}
            public void mouseClicked(MouseEvent event){}
            public void mouseEntered(MouseEvent event){}
            public void mouseExited(MouseEvent event){}
        }
        MouseListener listener = new mouseListener();
        mainWin.addMouseListener(listener); // add the mouse listener
       mainWin.addComponentListener(new WindowListener());
       newGame(); // start a new game
    }
    
    /**
     * The new game method sets all the game variables to the correct values, generates random mine locations, and redisplays
     */
    private void newGame()
    {
        boolean added = false;
        gameOver = false; // resets all game variables to correct values
        numMarked = 0;
        mineLocs = makeRandMineLocs(mines); // generates random mine locations
        blocks.clear(); // clears the blocks
        unhiddenLocs.clear(); // clears the unhidden locations
        locsToX.clear();
        for (int i = 0; i < gridHeight; i++)
            for (int j = 0; j < gridWidth; j++) 
            {
                added = false;
                for(Location loc:mineLocs) 
                    if (loc.equals(new Location(i, j)) && !added) // checks if the current location being added should be a mine, and if it's already been added
                    {
                        blocks.add(new Block(new Location(i, j), "mine")); // adds a new mine, and sets added to true
                        added = true;
                    }
                
                if (!added) // only does this if a mine hasn't already been added
                    blocks.add(new Block(new Location(i, j),getTouchingMines(new Location(i, j))+"")); // makes a new number block with the number of touching mines
                    
            }
            
         for(Block block:blocks)
            block.hide(); // hides all blocks
        readyToPaint = true; // ready to paint!
        Painter.resetGrid(); // resets the grid to the proper size
        mainWin.repaint(); // repaints
    }
    
    /**
     * Generates random, valid mine locations.
     * @param The number of mine locations to generate
     * @return An arraylist of the locations
     */
    private static ArrayList<Location> makeRandMineLocs(int mines)
    {
        ArrayList<Location> locs = new ArrayList<Location>(); // makes an arraylist
        for (int i = 0; i < mines; i++) // does this for each of the mines
        {
            boolean done = false;
            while (!done)
            {
                done = false;
            Location loc = Location.randomLoc(gridWidth - 1, gridHeight - 1); // makes a random location
            boolean contains = false;
            for (Location loc1:locs) // checks if the location is already in the list
                if (loc1.equals(loc))
                    contains = true;
            if (!contains) // if it's not in the list, add it to the list
            {
                locs.add(loc);
                done = true;
            }
            }
        }
        return locs;
    }
    
    /**
     * This class is tied to the menu bar listeners. It executes code depending on what menu item is clicked.
     * @param An ActionEvent
     */
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand(); // gets the menu item that was clicked
        if (command.equals("New")) // if new game was clicked, start a new game
        {
            newGame();
        }
        else if(command.equals("Change size")) // if change size was clicked
        {
        }
        else if(command.equals("Exit")) // exits the game
        {
            System.exit(0);
        }
        else if(command.equals("How to play")) // tells you how to play
        {
             new JOptionPane().showMessageDialog(mainWin, "The object of Minesweeper is to locate all the mines as quickly as \npossible without uncovering any of them. If you uncover a mine, you \nlose the game. You can uncover a square by clicking it. If you uncover \na mine, you lose the game. If a number appears on a square, it indicates \nhow many mines are in the eight squares that surround the numbered \none. To mark a square you suspect contains a mine, right-click it. Once \nall mines are marked correctly, you win the game.", "How to Play",  JOptionPane.INFORMATION_MESSAGE);
        }
        else if(command.equals("About")) // displays information about the game's creator
        {
            new JOptionPane().showMessageDialog(mainWin, "Created by Thomas Finch, 2010.", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Gets the number of mines touching the given location
     * @param A location
     * @return The number of touching mines
     */
    private static int getTouchingMines(Location loc)
    {
        int mines = 0;
        ArrayList<Location> adjacentLocs = loc.getAdjacentLocs(); // stores the list of adjacent locations
        for (Location loc1:adjacentLocs)
            if (mineLocs.indexOf(loc1) != -1) // checks if the current location is a mine
                mines++;
        return mines; // returns the number of mines
    }
    
    /**
     * Gets the location in the minesweeper grid of a click with the given coordinates.
     * @param x- the x coordinate of the click, y- the y coordinate of the click
     * @return The location of the click
     */
    private static Location getClickLocation(int x, int y)
    {
        int x1 = (x/(int)(Painter.winWidth/gridWidth)); // divides the x coordinate by the square width
        int y1 = (y/(int)(Painter.winHeight/gridHeight)); // divides the y coordinate by the square height
        return new Location(x1, y1); // returns the location
    }
    
    /**
     * Gets the block at the given location
     * @param The location of the desired block
     * @return The block object at that location
     */
    private static Block getBlockAt(Location loc)
    {
        for (Block block:blocks)
            if (block.getLocation().equals(loc)) // goes through the block list and checks if the block has the given location
                return block; // returns the block
        return null; // or returns null if no block is found
    }
    
    /**
     * This method unhides all the adjacent blocks to the given block that have no mines touching them. The given block should have no mines touching it.
     * @param The location to begin unhiding at
     */
    private static void unhideAllAdjacent(Location loc)
    {
        unhiddenLocs.add(loc);
        if (getBlockAt(loc).getNumber() == 0)
        {
            getBlockAt(loc).unhide();
        for (Location loc1:loc.getAdjacentLocs())
        {   
            getBlockAt(loc1).unhide();
            if (!unhiddenLocs.contains(loc1))  
                unhideAllAdjacent(loc1);
            }
            }
    }
    
    /**
     * Gets the locations marked as mines by the user.
     * @return An arraylist of the marked locations
     */
    private static ArrayList<Location> getMarkedLocs()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Block block:blocks) 
            if (block.isMarked()) // goes through each block, checks if it's marked, and adds it to the list
                locs.add(block.getLocation());
        return locs;
    }

    /**
     * Checks if all the marked locations are mines. If they are, the game is won.
     * @return <code>True</code> if all the locations marked by the user are the same as the mine locations, <code>false</code> otherwise.
     */
    private static boolean markedLocsAreMines()
    {
        if (numMarked != mines)
            return false;
        
       for (Location loc:mineLocs)
       {
           if (!getMarkedLocs().contains(loc))
                return false;
            }
            return true;
        }
}
