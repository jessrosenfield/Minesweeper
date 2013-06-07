public class Level {
  private int rows;
  private int columns;
  private int mines;
  
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
}
