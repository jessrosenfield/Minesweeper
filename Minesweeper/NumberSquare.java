public class NumberSquare implements Square {
	private int squareNum;
	
	public NumberSquare(int r, int c, int sqNum) {
		super(r, c);
		squareNum = sqNum;
	}
	
	public NumberSquare(int r, int c) {
		NumberSquare(r, c, 1);
	}
	
	@Override
	public int getNum() {
		return squareNum;
	}
	
	public void addNum() {
		squareNum++;
	}
}
