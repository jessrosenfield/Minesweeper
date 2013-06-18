public class MineSquare extends Square {
	
	public MineSquare(int r, int c) {
		super(r, c);
	}
	
	@Override
	public int reveal() {
		super.reveal();
		return -1;
	}
	@Override
	public int getNum() {
		return -1;
	}

}
