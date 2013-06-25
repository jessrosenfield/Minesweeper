package graphics;

import game.Square;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class MButton extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Square square;
	private int row;
	private int col;

	public MButton(Square sq) {
		super();
		square = sq;
		row = sq.getRow();
		col = sq.getCol();
		super.setPreferredSize( new Dimension(35, 35) );
		super.setIcon(blue());
	}

	public MButton(ImageIcon imgi) {
		super();
		super.setPreferredSize(new Dimension(35,35));
		super.setIcon(mine1());
	}
	
	public void update() {
		//square = getGame().getsq(row, col);
		setIcon();
	}

	private ImageIcon numImg() {
		ImageIcon imgi;
		switch(square.getNum()) {
		case 1:	imgi = one();
		break;
		case 2: imgi = two();
		break;
		case 3: imgi = three();
		break;
		case 4: imgi = four();
		break;
		case 5: imgi = five();
		break;
		case 6: imgi = six();
		break;
		case 7: imgi = seven();
		break;
		case 8: imgi = eight();
		break;
		default: imgi = blank();
		break;
		}
		return imgi;
	}

	public ImageIcon getImgi() {
		ImageIcon imgi = blue();
		if(!square.isRevealed()) {
			if(square.isFlagged())
				imgi = flag();
		}
		if(square.isRevealed()) {
			if(square.getNum() == 0)
				imgi = blank();
			if(square.getNum() > 0)
				imgi = numImg();
			if(square.getNum() == -1)
				imgi = mine();
			if(square.getNum() == -2)
				imgi = mine1();
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
		super.setIcon(getImgi());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//getGame().reveal(row, col);
		update();
	}

	
	/*------------IMAGES----------*/
	private ImageIcon blue(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/BlueSquare.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon blank(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/BlankSquare.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon mine(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/MineBlack.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	public ImageIcon mine1(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/MineRed.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon flag(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/NewFlag.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon one(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/1.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon two(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/2.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon three(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/3.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon four(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/4.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon five(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/5.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon six(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/6.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon seven(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/7.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
	private ImageIcon eight(){
		BufferedImage BI = null;
		try {
			BI = ImageIO.read(getClass().getResource("img/8.png"));
		} catch (IOException e) {
			System.out.println("Error: file not found");
		}
		return new ImageIcon(BI);
	}
}