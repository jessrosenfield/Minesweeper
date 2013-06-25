package game;
public class Square {
    private boolean revealed;
    private int row;
    private int column;
    private boolean flagged;
	private boolean mine;
	private int squareNum;
    
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
        return getNum();
    }
    
    public boolean isFlagged() {
    	return flagged;
    }
    
    public void flag() {
        flagged = !flagged;
        //TODO integrate with counter and GUI
    }

    public int getNum() {
        return squareNum;
    }

	public boolean isMine() {
		return mine;
	}
	
	public void firstMine() {
		squareNum = -2;
	}
	
	public void addNum() {
		squareNum++;
	}
}
