package graphics;

import game.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class MButton extends JButton implements ButtonImg, ActionListener {
	Square square;
	private int row;
	private int col;

	public MButton(Square sq) {
		super();
		square = sq;
		row = sq.getRow();
		col = sq.getCol();
		super.setPreferredSize( new Dimension(35, 35) );
		super.setIcon(blue);
	}

	public void update(Square sq) {
		if(sq.getRow() == row
				&& sq.getCol() == col)
			square = sq;
		setIcon();
	}

	private ImageIcon numImg(int n) {
		ImageIcon imgi;
		switch(n) {
		case 1:	imgi = one;
		break;
		case 2: imgi = two;
		break;
		case 3: imgi = three;
		break;
		case 4: imgi = four;
		break;
		case 5: imgi = five;
		break;
		case 6: imgi = six;
		break;
		case 7: imgi = seven;
		break;
		case 8: imgi = eight;
		break;
		default: imgi = blank;
		}
		return imgi;
	}

	public ImageIcon getImgi() {
		ImageIcon imgi = blue;
		if(!square.isRevealed()) {
			if(square.isFlagged())
				imgi = flag;
		}
		if(square.isRevealed()) {
			if(square.getNum() == 0)
				imgi = blank;
			if(square instanceof NumberSquare)
				imgi = numImg(square.getNum());
			if(square.getNum() == -1)
				imgi = mine;
		}
		return imgi;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setIcon() {
		setIcon(getImgi());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonGrid.xClick = row;
		ButtonGrid.yClick = col;
	}


public static class ButtonImg {
	public static final ImageIcon blue;
	public static final ImageIcon blank = new ImageIcon("img/BlankSquare.png");
	public static final ImageIcon mine = new ImageIcon("img/BlackMine.png");
	public static final ImageIcon mine1 = new ImageIcon("img/MineRed.png");
	public static final ImageIcon flag = new ImageIcon("img/NewFlag.png");
	public static final ImageIcon one = new ImageIcon("img/1.png");
	public static final ImageIcon two = new ImageIcon("img/2.png");
	public static final ImageIcon three = new ImageIcon("img/3.png");
	public static final ImageIcon four = new ImageIcon("img/4.png");
	public static final ImageIcon five = new ImageIcon("img/5.png");
	public static final ImageIcon six = new ImageIcon("img/6.png");
	public static final ImageIcon seven = new ImageIcon("img/7.png");
	public static final ImageIcon eight = new ImageIcon("img/8.png");
	
	public static void main(String args[]) {
		blue = new ImageIcon( ImageIO.read(getClass(getResource("img/BlueSquare.png") ) ) );
	}
}

}
