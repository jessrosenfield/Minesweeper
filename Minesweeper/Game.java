import java.util.Scanner;

public class Game {
    public enum Level {
        BEGINNER    (9, 9, 10), 
        ADVANCED    (16, 16, 40), 
        EXPERT      (16, 30, 99),
        CUSTOM      ();
        public final int rows;
        public final int columns;
        public final int mines;

        Level(int r, int c, int m) {
            rows = r;
            columns = c;
            mines = m;
        }

        Level(){
            rows = getInt();
            columns = getInt();
            mines = getInt();
        }

        int getInt() {
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            return i;
        }
    }
    private Level level;
    private static Level lastLevel = Level.BEGINNER;
    private static int [] customRCM;
    public int flags; 

    public Game(Level l) {
        level = l;
        lastLevel = l;
    }
    
    public Game() {
        level = lastLevel;
    }
}
