public class MineSquare implements Square {
	private boolean revealed;
	private int row;
	private int column;
	
	public MineSquare(int r, int c) {
		revealed = false;
		row = r;
		column = c;
	}
	
	@Override
	public void reveal() {
		revealed = true;
		//TODO reveal all mines
		//TODO loses game
		//TODO end game
	}
	@Override
	public boolean isRevealed() {
		return revealed;
	}
	@Override
	public int getRow() {
		return row;
	}
	@Override
	public int getColumn() {
		return column;
	}

}