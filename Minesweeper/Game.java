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
    private static Level lastLevel = Level.BEGINNER;
    public int flags;
    private Square[][] squares;

    public Game(Level l) {
        level = l;
        lastLevel = l;
        squares = new Square[l.rows][l.columns]
    }
    
    public Game() {
        level = lastLevel;
        squares = new Square[lastLevel.rows][lastLevel.columns]
    }
}
