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

    public int getColumn() {
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
        //TODO integrate with counter and GUI
    }

    public int getNum() {
        return null;
    }
}
