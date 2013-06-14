public class Game {
    
    public static enum Level {
        BEGINNER    (9, 9, 10), 
        ADVANCED    (16, 16, 40), 
        EXPERT      (16, 30, 99);
        public final int rows;
        public final int columns;
        public final int mines;

        Level(int r, int c, int m) {
            rows = r;
            columns = c;
            mines = m;
        }
    }
    
    public final Level level;
    
    private static Level lastLevel; //default constructor uses the same level as before.
    lastLevel = Level.BEGINNER; //sets lastLevel to beginner in case there was no previous game
    
    public int flags;   //to be used with counter of mines left
    
    private Square[][] squares; //the grid of squares

/*******************************************************************************************/
    public Game(Level l) {
        level = l;
        lastLevel = l;
        squares = new Square[l.rows][l.columns]
        placeSquares();
    }
    
    public Game() {
        Game(lastLevel);
    }
    
    
/********************************************************************************************/    
        
    public void placeSquares() {
        for(int r = 0; r < level.rows; r++)
            for(int c = 0; c < level.columns; c++)
                Square[r][c] = new emptySquare(r, c);
    }
    
    private boolean badNums(ArrayList<Square> sq, int r, int c) {
        for(Square s : sq) {
            if(s.getRow == r && s.getColumn == c)
                return true;
        }
        return false;
    }
    
    private Squares[] surroundings() {
        
    }
    
    public void levelSetup(int row, int col) {
        ArrayList<Square> offLim = new ArrayList<Square>;
        offLim.add(Squares[row][col]);
        
        for(int m = 0; m < level.mines; m++) {
            int i; int j;
            
            do {
                i = Math.random()*rows;
                j = Math.random()*columns
            } while( badNums(offLim, i, j) );
            
            Squares[i][j] = new mineSquare(i, j);
            offLim.add(Squares[i][j]);
            
        }
        
    }
    
    public void levelSetup(Square firstSquare) {
        Square fs = firstSquare;
        int r = fs.getRow();
        int c = fs.getColumn;
        levelSetup(r, c);
    }
    
    
}
