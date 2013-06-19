package graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

import game.*;

@SuppressWarnings("serial")
public class noframegrid extends JPanel implements ActionListener {
	static JPanel panel = new JPanel();
	Button[][] bGrid;
	JButton[][] jGrid;
	int width;
	int length;
	Game game;
	
	public noframegrid(Game g, int w, int l) {
		width = w;
		length = l;
		game = g;
		setLayout(new GridLayout(width,length));
		bGrid = new Button[width-1][length-1];
		jGrid = new JButton[width-1][length-1];
		for(int y = 0; y < length; y++) {
			for(int x = 0; x < width; x++) {
				try {
					bGrid[x][y] = new Button(game.getsq(x, y));
					jGrid[x][y] = bGrid[x][y].JB;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		setVisible(true);
		setSize(new Dimension(25*width,25*length));
	}

	public static JPanel returnPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}