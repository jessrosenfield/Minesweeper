

public class EmptySquare implements Square {
	private boolean revealed;
	private int row;
	private int column;
	
	public EmptySquare(int r, int c) {
		revealed = false;
		row = r;
		column = c;
	}
	
	@Override
	public void reveal() {
		revealed = true;
		//TODO reveal surrounding squares
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