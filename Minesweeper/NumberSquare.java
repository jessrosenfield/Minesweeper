public class NumberSquare implements Square {
	private int squareNum;
	
	public NumberSquare(int r, int c, int sqNum){
		super(r, c);
		squareNum = sqNum;
	}
	@Override
	public int getNum(){
		return squareNum;
	}
}
