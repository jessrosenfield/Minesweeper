package graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

import game.*;

@SuppressWarnings("serial")
public class ButtonGrid extends JPanel implements ActionListener {
	public static int xClick;
	public static int yClick;
	public static JPanel panel = new JPanel();
	private MButton[][] grid;
	public final int width;
	public final int length;
	public final Game game;

	public ButtonGrid(Game g) {
		game = g;
		width = game.level.rows;
		length = game.level.columns;
		game.levelSetup((int)Math.random()*width, (int)Math.random()*length);
		setLayout(new GridLayout(width,length));
		grid = new MButton[width][length];
		for(int y = 0; y < length; y++) {
			for(int x = 0; x < width; x++) {
				grid[x][y] = new MButton(game.getsq(x, y));
				grid[x][y].addActionListener(this);
				add(grid[x][y]);				
			}
		}
		setVisible(true);
		setSize(new Dimension(35*width,35*length));
	}

	private void refresh() {
		for(int y = 0; y < length; y++)
			for(int x = 0; x < width; x++)
				grid[x][y].update(game.getsq(x, y));
	}


	public void click(int x, int y) {
		/*int i = game.reveal(x, y);
		refresh();
		System.out.println(i);*/
		System.out.println(game.getsq(x, y).getNum());
	}

	public static JPanel returnPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		click(xClick, yClick);
	}
}