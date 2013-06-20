package game;
import java.util.ArrayList;

/**
 * Game class creates the setup for the game
 * and has methods for revealing squares that depend on what type of square it is
 **/
public class Game {

	public final Level level;

	private static Level lastLevel = Level.BEGINNER; //sets lastLevel to beginner in case there was no previous game

	public int flags;   //to be used with counter of mines left

	private Square[][] squares; //the grid of squares

	private ArrayList<Square> mines;	//keeps track of all of the squares that are mines

	/*******************************************************************************************/

	public Game(Level l) {
		level = l;
		lastLevel = l;
		squares = new Square[level.rows][level.columns];
		mines = new ArrayList<Square>();
		placeSquares();
	}

	//the default constructer uses the last level used as the level for the new game
	public Game() {
		this(lastLevel);
	}

	public void placeSquares() {
		for(int r = 0; r < level.rows; r++)
			for(int c = 0; c < level.columns; c++)
				squares[r][c] = new EmptySquare(r, c);
	}

	/********************************************************************************************/       

	/**
	 * badNums() takes an arraylist of squares and location
	 * and checks if any of the squares are in the location in question
	 * @param sq 	array of squares that are "bad"
	 * @param r		row in question
	 * @param c		column in question
	 * @return		returns true if the location is not a location that a mine can be placed in
	 * 				returns false if the location is okay to put a mine in
	 */
	private boolean badNums(ArrayList<Square> sq, int r, int c) {
		for(Square s : sq) {
			if(s.getRow() == r && s.getCol() == c)
				return true;
		}
		return false;
	}

	/**
	 * surroundings returns all of the squares surrounding the square in the location of the parameter
	 * @param r	the row of the square
	 * @param c	the column of the square
	 * @return an arraylist of all the surrounding squares
	 */
	private ArrayList<Square> surroundings(int r, int c) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		int[] rs = {r-1, r, r+1};
		int[] cs = {c-1, c, c+1};

		for(int i : rs) {
			for(int j : cs) {
				if( (i >= 0 && j >= 0 && i < squares.length && j < squares[1].length)
				    && (i!=r && j!=c) )
					neighbors.add(squares[i][j]);
			}
		}
		return neighbors;        
	}

	/**
	 * placeNums takes he row and column of the mine being placed
	 * and adds a number the each of the squares surrounding it to account for the mine
	 * If a surrounding square is an EmptySquare it will change it into a Number Square
	 * If it is a NumberSquare it calls the method to add one to its number
	 * If it is already a mine it leaves it alone.
	 * @param r 	row of the mine to be placed
	 * @param c 	column of mine to be place
	 */
	private void placeNums(int r, int c) {
		ArrayList<Square> neighbors = surroundings(r, c);
		for (Square s : neighbors) {
			if(s instanceof NumberSquare)
				((NumberSquare) s).addNum();
			if(s instanceof EmptySquare) { 
				squares[s.getRow()][s.getCol()] = new NumberSquare( s.getRow(), s.getCol() );            
			}
		}
	}

	/**
	 * levelSetup places all of the mines and calls other methods to place the numbers and prevent errors 
	 * @param row	row of the first square that was clicked
	 * @param col	column of the first square that was clicked
	 */
	public void levelSetup(int row, int col) {
		for(int i = 0; i < level.rows; i++)
			for(int j = 0; j < level.columns; j++) {
				squares[i][j] = new EmptySquare(i, j);
			}
		ArrayList<Square> offLim = new ArrayList<Square>();	//makes an arraylist of squares not to be messed with
		offLim.add(squares[row][col]);	//adds first square to offLim to make sure it stays an EmptySquare

		for(int m = 0; m < level.mines; m++) {
			int i; int j;

			do {
				i = (int)(Math.random()*level.rows);
				j = (int)(Math.random()*level.columns);
			} while( badNums(offLim, i, j) );

			squares[i][j] = new MineSquare(i, j);
			offLim.add(squares[i][j]);  //adds new mine to offLim to make sure that another mine is not put there
			mines.add(squares[i][j]);	//adds new mine to the collection of mines
			placeNums(i, j);
		}

	}

	public ArrayList<Square> getMines() {
		return mines;
	}

	public int reveal(Square sq) {
		if(sq instanceof EmptySquare)
			return reveal((EmptySquare)sq);
		if(sq instanceof NumberSquare)
			return reveal((NumberSquare)sq);
		if(sq instanceof MineSquare)
			return reveal((MineSquare)sq);
		if(sq instanceof Square) {
			int r = sq.getRow();
			int c = sq.getCol();
			levelSetup(r, c);
			reveal(r, c);
		}
		return (Integer)null;
	}

	public Square getsq(int r, int c) {
		return squares[r][c];
	}

	/*---------------------------------JESSICA GOES CRAZY WITH POLYMORPHISM ;)-----------------------------*/
	// (SHE TOUGHT IT WOULD COME IN HANDY BUT IT PROBABLY WON'T)

	public void levelSetup(Square firstSquare) {
		Square fs = firstSquare;
		int r = fs.getRow();
		int c = fs.getCol();
		levelSetup(r, c);
	}

	public ArrayList<Square> surroundings(Square sq) {
		return surroundings(sq.getRow(), sq.getCol());
	}

	public int reveal(EmptySquare eSq) {
		eSq.reveal();
		for(Square sq : surroundings(eSq))
			reveal(sq);
		return 0;
	}

	public int reveal(MineSquare mSq) {
		return mSq.reveal();
	}

	public int reveal(NumberSquare nSq) {
		return nSq.reveal();
	}

	public int reveal(int r, int c) {
		return reveal(squares[r][c]);
	}


}
