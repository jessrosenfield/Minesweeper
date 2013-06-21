package graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

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
				try {
					bGrid[x][y] = new Button(game.getsq(x, y));
					JButton b = bGrid[x][y].getJB();
					b.setActionCommand(y+","+x);
					grid[x][y] = b;
					grid[x][y].addActionListener(this);
					add(grid[x][y]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		setVisible(true);
		setSize(new Dimension(35*width,35*length));
	}
	
	private void refresh() {
		for(int y = 0; y < length; y++) {
			for(int x = 0; x < width; x++) {
				try {
					bGrid[x][y] = new Button(game.getsq(x, y));
					JButton b = bGrid[x][y].getJB();
					b.setActionCommand(y+","+x);
					grid[x][y] = b;
					grid[x][y].addActionListener(this);
					add(grid[x][y]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void click(int x, int y) {
		int i = game.reveal(x, y);
		refresh();
		System.out.println(i);
		grid[x][y] = bGrid[x][y].getJB();
		if(i == -1)
			for(Square sq : game.getMines() ) {
				int r = sq.getRow();
				int c = sq.getCol();
				grid[r][c] = bGrid[r][c].getJB();
			}
		if(i == 0)
			for(Square sq : game.surroundings(game.getsq(x, y)) ) {
				int r = sq.getRow();
				int c = sq.getCol();
				grid[r][c] = bGrid[r][c].getJB();
			}
		
	}

	public static JPanel returnPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		this.click(Integer.parseInt(s.substring(0, 1)), Integer.parseInt(s.substring(2, 3)));
		
	}
}