package graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.*;

@SuppressWarnings("serial")
public class ButtonGrid extends JPanel implements ActionListener {
	static JPanel panel = new JPanel();
	Button[][] bGrid;
	JButton[][] grid;
	int width;
	int length;
	Game game;

	public ButtonGrid(Game g) {
		game = g;
		width = game.level.rows;
		length = game.level.columns;
		setLayout(new GridLayout(width,length));
		bGrid = new Button[width][length];
		grid = new JButton[width][length];
		for(int y = 0; y < length; y++) {
			for(int x = 0; x < width; x++) {
				bGrid[x][y] = new Button(game.getsq(x, y));
				JButton b = bGrid[x][y].getJB();
				b.setActionCommand(y+","+x);
				grid[x][y] = b;
				grid[x][y].addActionListener(this);
				add(grid[x][y]);
			}
		}

		setVisible(true);
		setSize(new Dimension(35*width,35*length));
	}

	public void click(int x, int y) {
		game.reveal(x, y);
		grid[x][y] = bGrid[x][y].getJB();
	}

	public static JPanel returnPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		this.click(Integer.parseInt(s.substring(0, 1)), Integer.parseInt(s.substring(2, 3)));

	}
	public class Button {
		Square square;
		private int row;
		private int col;


		public Button(Square sq) {
			square = sq;
			row = sq.getRow();
			col = sq.getCol();
		}

		public JButton basicButton() {
			JButton button = getJB();
			return button;
		}
		public int getRow() {
			return row;
		}
		public int getCol() {
			return col;
		}
		public JButton getJB() {
			JButton jb = new JButton();
			if(!square.isRevealed())
				if(square.isFlagged())
					jb = new JButton(flag());
				else
					jb.setIcon(blue());
			if(square.isRevealed()) {
				if(square.getNum() == 0)
					jb = new JButton(blank());
				if(square.getNum()>0)
					jb = new JButton( numImg() );
				if(square.getNum() == -1)
					jb = new JButton(mine());
				if(square.getNum() == -2)
					jb = new JButton(mine1());
			}
			jb.setPreferredSize(new Dimension(35, 35));
			return jb;
		}
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
	}

}