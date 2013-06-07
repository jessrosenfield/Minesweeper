public class NumberSquare implements Square {
	private boolean revealed;
	private int row;
	private int column;
	private int squareNum;
	
	public NumberSquare(int sqNum, int r, int c){
		revealed = false;
		row = r;
		column = c;
		squareNum = sqNum;
	}
	
	public int getNum(){
		return squareNum;
	}
	
	@Override
	public int reveal() {
		revealed = true;
		return squareNum;
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
	@Override
	public int[] getRC() {
		return {row, column};
	}
}
