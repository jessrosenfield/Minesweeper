package game;
public class Square {
    private boolean revealed;
    private int row;
    private int column;
    private boolean flagged;
    private int squareNum;
    private boolean mine;
    
    Square(int r, int c, boolean m) {
        row = r;
        column = c;
        revealed = false;
        flagged = false;
        mine = m;
        if(mine)
            squareNum = -1;
        else
        	squareNum = 0;
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
        flagged = false;
        return getNum();
    }
    
    public void flag() {
        flagged = !flagged;
    }
    
    public boolean isFlagged() {
    	return flagged;
    }
    
    public boolean isMine() {
    	return mine;
    }

    public int getNum() {
        return squareNum;
    }
    
    public void addNum() {
    	squareNum++;
    }
    
    public void firstMine() {
    	squareNum = -2;
    }
}
