public class EmptySquare extends NumberSquare {
		
	public EmptySquare(int r, int c){
		super(r, c);
	}

	@Override
	public void reveal() {
		super.reveal();
		//TODO reveal surrounding squares
	}
}
