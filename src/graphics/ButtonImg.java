package graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ButtonImg {
	public static Image blue = blueButton();
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
	
	public static Image blueButton() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("img.BlueSquare"));
		} catch (IOException e) {
		}
		return img;
	}

}
