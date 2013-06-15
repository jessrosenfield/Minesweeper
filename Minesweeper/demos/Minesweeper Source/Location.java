import java.util.ArrayList;
import java.util.Random;
/**
 * A location object represents a location (x, y) in the minesweeper coordinate system.
 * 
 * @author Thomas Finch
 * @version 5/24/10
 */
public class Location
{
   private int x, y; // creates instance variables for the row and column
    
    public Location(int x, int y)
    {
        this.x = x; // initializes the instance variables with values
        this.y = y;
    }
    
    /**
     * Returns the location's x coordinate (column).
     * @return The x-coordinate
     */
    public int getX()
    {
        return x; // returns the x-coordinate (column)
    }
    
    /**
     * Returns the location's y-coordinate (row).
     * @return The y-coordinate
     */
    public int getY()
    {
        return y; // returns the y-coordinate (row)
    }
    
    /**
     * Checks whether this location is valid in the grid defined in the main class.
     * @return Whether or not this location is valid.
     */
    public boolean isValid()
    {
        return (getX() >= 0 && getY() >= 0 && getX() < Main.gridWidth && getY() < Main.gridHeight); 
        // checks whether this location is valid (in the grid) in the coordinates of the grid in the main class
    }
    
    /**
     * Checks whether this location is "equal" to another (they have the same x and y coordinates).
     * @return Whether or not this location is equal to the other
     */
    public boolean equals(Object loc1)
    {
        Location loc = (Location) loc1;
        return (loc.getY() == y && loc.getX() == x);
        // checks whether this location is "equal" to another (they have the same x and y coordinates)
    }
    
    /**
     * Returns a string representation of this location.
     * @return A string representation of this location
     */
    public String toString()
    {
        return "("+x+", "+y+")";
        //returns a string representation of this location
    }
    
    /**
     * Generates a random, valid location.
     * @return A random, valid location
     */
    public static Location randomLoc(int maxX, int maxY)
    {
        Random generator = new Random();
        Location loc = new Location(generator.nextInt(maxX + 1), generator.nextInt(maxY + 1));
        return loc;
        //returns a random, valid location
    }
    
     /**
      * Returns all the locations adjacent to this location that are valid.
      * @return An arraylist of valid adjacent locations
      */
    public ArrayList<Location> getAdjacentLocs()
    {
        Location loc = this;
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.add(new Location(loc.getX()-1, loc.getY()-1));
        locs.add(new Location(loc.getX(), loc.getY()-1));
        locs.add(new Location(loc.getX()+1, loc.getY()-1));
        locs.add(new Location(loc.getX()-1, loc.getY()));
        locs.add(new Location(loc.getX()+1, loc.getY())); // returns all the valid locations adjacent to this location
        locs.add(new Location(loc.getX()-1, loc.getY()+1));
        locs.add(new Location(loc.getX(), loc.getY()+1));
        locs.add(new Location(loc.getX()+1, loc.getY()+1));
        
        return Location.removeInvalidLocs(locs);
    }
    
    /**
     * Removes all invalid locations from the given arraylist.
     * @return An arraylist identical to the given one, but with the invalid locations removed.
     */
    public static ArrayList<Location> removeInvalidLocs(ArrayList<Location> locs)
    {
        ArrayList<Location> newLocs = new ArrayList<Location>();
        for (Location loc:locs) 
            if (loc.isValid()) // removes all invalid locations from the given arraylist, then returns the results
                newLocs.add(loc);
        return newLocs;
    }
}
