package game;


public class EmptySquare extends Square {
		
	public EmptySquare(int r, int c){
		super(r, c);
	}

	@Override
	public int reveal() {
		super.reveal();
		return 0;
	}
	
	@Override
	public int getNum(){
		return 0;
	}
}