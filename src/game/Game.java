package game;
import java.util.ArrayList;

/**
 * Game class creates the setup for the game
 * and has methods for revealing squares that depend on what type of square it is
 **/
public class Game {

	public final Level level;

	private boolean setup;

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
		setup = false;
	}

	//the default constructor uses the last level used as the level for the new game
	public Game() {
		this(lastLevel);
	}

	public void placeSquares() {
		for(int r = 0; r < level.rows; r++)
			for(int c = 0; c < level.columns; c++)
				squares[r][c] = new Square(r, c, false);
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
						&& (i!=r || j!=c) )
					neighbors.add(squares[i][j]);
			}
		}
		return neighbors;        
	}

	/**
	 * placeNums takes he row and column of the mine being placed
	 * and tests the squares surrounding it if they are mines
	 * if they are not, their number is incremented by one
	 * 
	 * @param r 	row of the mine to be placed
	 * @param c 	column of mine to be place
	 */
	private void placeNums(int r, int c) {
		ArrayList<Square> neighbors = surroundings(r, c);
		for (Square s : neighbors) {
			if(!s.isMine())
				s.addNum();
		}
	}


	/**
	 * levelSetup places all of the mines and calls other methods to place the numbers and prevent errors 
	 * @param row	row of the first square that was clicked
	 * @param col	column of the first square that was clicked
	 */
	private void levelSetup(int row, int col) {
		ArrayList<Square> offLim = new ArrayList<Square>();	//makes an arraylist of squares not to be messed with
		offLim.add(squares[row][col]);	//adds first square to offLim to make sure it stays an EmptySquare
		for(int m = 0; m < level.mines; m++) {
			int i; int j;

			do {
				i = (int)(Math.random()*level.rows);
				j = (int)(Math.random()*level.columns);
			} while( badNums(offLim, i, j) );

			squares[i][j] = new Square(i, j, true);
			offLim.add(squares[i][j]);  //adds new mine to offLim to make sure that another mine is not put there
			mines.add(squares[i][j]);	//adds new mine to the collection of mines
			placeNums(i, j);
		}
		setup = true;
		reveal(row, col);
		printSquares();
	}

	public ArrayList<Square> getMines() {
		return mines;
	}

	public void reveal(int row, int col) {
		reveal(squares[row][col]);
	}

	public void reveal(Square sq) {
		if(sq.isRevealed())
			return;
		if(!setup)
			levelSetup(sq.getRow(), sq.getCol());
		else
			switch(sq.getNum()){
			case -1:	//square is a mine
				sq.reveal();
				sq.firstMine();
				for(Square square : mines)
					reveal(square);
						break;
			case 0:		//square is an empty square
				sq.reveal();
				for(Square square : surroundings(sq))
					if( !sq.isMine() && !sq.isRevealed() )
						reveal(square);
						break;
			default:
				sq.reveal();
				break;
			}
	}

	public Square getsq(int r, int c) {
		return squares[r][c];
	}


	public void levelSetup(Square firstSquare) {
		Square fs = firstSquare;
		int r = fs.getRow();
		int c = fs.getCol();
		levelSetup(r, c);
	}

	public ArrayList<Square> surroundings(Square sq) {
		return surroundings(sq.getRow(), sq.getCol());
	}

	public void printSquares() {
		for(Square[] sqs : squares) {
			System.out.print("\n");
			for(Square sq : sqs)
				System.out.print("|" + sq.getNum());
		}
	}

}