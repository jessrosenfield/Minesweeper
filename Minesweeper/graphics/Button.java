package graphics;

import game.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


@SuppressWarnings("unused")
public class Button {
	Square square;
	private int row;
	private int col;
	private BufferedImage img;
	public JButton JB;
	
	
	public Button(Square sq) {
		square = sq;
		row = sq.getRow();
		col = sq.getCol();
		JB = new JButton(setBG());
	}
	
	private ImageIcon setBG() {
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File("Stone.png"));
		} catch (IOException e) {
			buttonIcon = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {}
		ImageIcon img = new ImageIcon(buttonIcon);
		return img;
	}

}
