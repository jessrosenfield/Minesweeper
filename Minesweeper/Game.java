import java.util.ArrayList;

public class Game {
      
    public final Level level;
    
    private static Level lastLevel = Level.BEGINNER; //sets lastLevel to beginner in case there was no previous game
    
    public int flags;   //to be used with counter of mines left
    
    private Square[][] squares; //the grid of squares
    
    private ArrayList<Square> mines;

/*******************************************************************************************/
    
    public Game(Level l) {
        level = l;
        lastLevel = l;
        squares = new Square[level.rows][level.columns];
        mines = new ArrayList<Square>();
        placeSquares();
    }
    
    public Game() {
        this(lastLevel);
    }    
    
/********************************************************************************************/    
        
    public void placeSquares() {
        for(int r = 0; r < level.rows; r++)
            for(int c = 0; c < level.columns; c++)
                squares[r][c] = new EmptySquare(r, c);
    }
    
    private boolean badNums(ArrayList<Square> sq, int r, int c) {
        for(Square s : sq) {
            if(s.getRow() == r && s.getCol() == c)
                return true;
        }
        return false;
    }
    private ArrayList<Square> surroundings(int r, int c) {
        ArrayList<Square> neighbors = new ArrayList<Square>();
        int[] rs = {r-1, r, r+1};
        int[] cs = {c-1, c, c+1};
        
        for(int i : rs) {
            for(int j : cs) {
            if( 
    		(i >= 0 && j >= 0 && i < squares.length && j < squares[1].length)
    		&&(i!=r || j!=c)){
                    neighbors.add(squares[i][j]);
                }
            }
        }
        return neighbors;        
    }
    private ArrayList<Square> surroundings(Square sq) {
    	return surroundings(sq.getRow(), sq.getCol());
    }
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
    
    public void levelSetup(int row, int col) {
        ArrayList<Square> offLim = new ArrayList<Square>();
        offLim.add(squares[row][col]);
        
        for(int m = 0; m < level.mines; m++) {
            int i; int j;
            
            do {
                i = (int)(Math.random()*level.rows);
                j = (int)(Math.random()*level.columns);
            } while( badNums(offLim, i, j) );
            
            squares[i][j] = new MineSquare(i, j);
            offLim.add(squares[i][j]);
            mines.add(squares[i][j]);
            placeNums(i, j);
        }
        
    }
    
    public ArrayList<Square> getMines() {
    	return mines;
    }
    
    public void levelSetup(Square firstSquare) {
        Square fs = firstSquare;
        int r = fs.getRow();
        int c = fs.getCol();
        levelSetup(r, c);
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
    
    private int reveal(Square sq) {
		if(sq instanceof EmptySquare)
			return reveal((EmptySquare)sq);
		if(sq instanceof NumberSquare)
			return reveal((NumberSquare)sq);
		if(sq instanceof MineSquare)
			return reveal((MineSquare)sq);
		return (Integer)null;
	}
    
    public int reveal(int r, int c) {
    	return reveal(squares[r][c]);
    }
}
