public class Game {
	
	/*LEVEL INFO*/
	private int rows;
 	private int columns;
 	private int mines;
 	private int flags;
	
 	public int getRows() {
 		return rows;
 	}
 	public int getColumns() {
		return columns;
	}
	public int getMines() {
		return mines;
	}
	public int[] getRC() {
	return {rows, columns};
	}
	public int getFlags() {
		return flags;
	}
	public enum Level {BEGINNER, ADVANCED, EXPERT, CUSTOM};
	private Level level;
	private static Level lastLevel = BEGINNER;
	private static int customRows;
	private static int customColumns;
	private static int customMines;
	public int[] customInfo() {
		return {customRows, customColumns, customMines};
	}
	/*END LEVEL INFO*/
	
	public Game() {
		
	}
	
	public Game(Level l) {
		
		
	}
	
	private Game(r, c, m) {
		rows = r;
		columns = c;
		mines = m;
	}
	
	private void levelSetter(Level l){
		switch (l) {
			case BEGINNER:
				// TODO set beginner game parameters
				break;
			
			case ADVANCED:
				// TODO set advanced game parameters
				break;
			
			case EXPERT:
				// TODO set expert game parameters
				break;
				
			case CUSTOM:
				// TODO collect custom game parameters
				// TODO set custom game parameters
				break;
			
			case default:
				// TODO setup static variable for level last played (initially beginner?)
				// TODO get last level and impliment it
	}
	
	private static class SquareSetup {
		static void main(String[] args){
			Square[][] squares;
			
		}
	}
	
}

	
