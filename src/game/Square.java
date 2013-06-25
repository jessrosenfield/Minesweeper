package game;
public class Square {
    private boolean revealed;
    private int row;
    private int column;
    private boolean flagged;
    
    Square(int r, int c) {
        row = r;
        column = c;
        revealed = false;
        flagged = false;
    }
    
    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public int reveal() {
        revealed = true;
        return getNum();
    }
    
    public void flag() {
        flagged = !flagged;
    }
    
    public boolean isFlagged() {
    	return flagged;
    }

    public int getNum() {
        int i = 100;
    	return i;
    }
}
