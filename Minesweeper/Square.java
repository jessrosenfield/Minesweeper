public abstract class Square {
    private boolean revealed;
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int[] getRC() {
        int[] RC = {row, column};
        return RC;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public int reveal() {
        revealed = true;
        return getNum();
    }

    public abstract int getNum();
}
