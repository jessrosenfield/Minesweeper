import java.awt.image.BufferedImage;
/**
 * A Block object represents one location in the minesweeper grid.
 * 
 * @author Thomas Finch
 * @version 5/24/10
 */
public class Block
{
    private String text;
    private Location loc;  // creates instance variables for the text label, location, and booleans for if it's marked and hidden
    private boolean hidden = true, marked = false;
    
    public Block(Location loc, String text)
    {
        this.text = text; // sets the text and location variables
        this.loc = loc;
    }
    
    /**
     * Returns the block's text string.
     * @return The block's text.
     */
    public String getText()
    {
        return text; // returns the cell's text
    }
    
    /**
     * Returns the number of mines touching the block. If the block is a mine, it returns -1.
     * @return The number of mines touching the block, or -1 if the block is a mine.
     */
    public int getNumber()
    {
        if (!isMine())
            return Integer.parseInt(text); // if the block isn't a mine, return its text
        return -1; // if it is a mine, return -1
    }
    
    /**
     * Returns the blocks location in the (x, y) grid.
     * @returns The block's location.
     */
    public Location getLocation()
    {
        return loc; // returns the location
    }
    
    /**
     * Returns whether the block is hidden or not.
     * @return Whether or not the block is hidden.
     */
    public boolean isHidden()
    {
        return hidden; // returns whether it's hidden or not
    }
    
    /**
     * Unhides the block.
     */
    public void unhide()
    {
        hidden = false; // unhides the block
    }
    
    /**
     * Hides the block.
     */
    public void hide()
    {
        hidden = true; // hides the block
    }
    
    /**
     * Returns whether or not this block is a mine. 
     * @return Whether or not this block is a mine
     */
    public boolean isMine()
    {
        return text.equalsIgnoreCase("mine"); // returns whether or not this is a mine
    }
    
    /**
     * Returns a string representation of this block
     * @return A string representation of this block
     */
    public String toString()
    {
        return "Loc: "+loc+" Text: "+text+" Hidden: "+hidden; // returns a string representation of the cell, with its location, text, and wheter or not it's hidden
    }
    
    /**
     * Returns whether or not this block is marked as a mine.
     * @returns Whether or not this block is marked
     */
    public boolean isMarked()
    {
        return marked; // returns wheter or not this block is marked
    }
    
    /**
     * Marks this block as a mine.
     */
    public void mark()
    {
        marked = true; // marks the block as a mine with a flag
    }
    
    /**
     * Unmarks this block as a mine.
     */
    
    public void unMark()
    {
        marked = false; // unmarks the block
    }
}
