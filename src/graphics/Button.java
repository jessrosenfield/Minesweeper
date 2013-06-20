package graphics;

import game.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.sun.medialib.mlib.Image;


@SuppressWarnings("unused")
public class Button {
	Square square;
	private int row;
	private int col;
	private BufferedImage stone;


	public Button(Square sq) throws IOException {
		square = sq;
		row = sq.getRow();
		col = sq.getCol();
		stone = ImageIO.read(getClass().getResource("Stone.png"));
	}

	public JButton basicButton() {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(stone));
		return button;
	}
	
	public JButton getJB() {
		JButton jb = new JButton();
		if(!square.isRevealed())
			jb = basicButton();
		if(square.isRevealed())
			if(square instanceof EmptySquare)
				jb = new JButton();
			if(square instanceof NumberSquare)
				jb = new JButton( Integer.toString( square.getNum() ) );
			if(square instanceof MineSquare)
				jb = new JButton("M");
		jb.setPreferredSize(new Dimension(35, 35));
		return jb;
	}

}
