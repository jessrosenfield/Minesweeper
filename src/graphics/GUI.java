package graphics;

import javax.swing.*;

import game.Game;
import game.Level;

import java.awt.*;


public class GUI
{
   private static JFrame createWindow() {
		JFrame frame = new JFrame("Minesweeper");
		Game game = new Game();
		int rows = game.level.rows;
		int cols = game.level.columns;
		ButtonGrid bpanel = new ButtonGrid(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setPreferredSize(new Dimension(35*rows, 35*cols+100));       
		frame.setLocationRelativeTo(null); 
		frame.add(bpanel, BorderLayout.NORTH);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		return frame;
	}

	public static JFrame createWindow(Level level) {
		JFrame frame = new JFrame("Minesweeper");
		Game game = new Game(level);
		int rows = game.level.rows;
		int cols = game.level.columns;
		ButtonGrid bpanel = new ButtonGrid(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setPreferredSize(new Dimension(35*rows, 35*cols+100));       
		frame.setLocationRelativeTo(null); 
		frame.add(bpanel, BorderLayout.NORTH);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		return frame;
	}
	public static void main(String[] args) {
		createWindow();
	}
}
/*private JPanel createMenu(int x) {
	   JPanel jp = new JPanel();
	   jp.setPreferredSize(new Dimension(35*x, 100));
	   jp.setLayout(new GridLayout(1, 3));
	   JButton jb;
	   jb = BegButton();
	   jb.addActionListener(this);
	   jp.add(BegButton());
	   jp.add(AdvButton());
	   jp.add(ExpButton());
	   return jp;
   }

   private JButton BegButton(){
	   JButton jb = new JButton("Beginner");
	   jb.addActionListener();
	   return jb;
   }

   private JButton AdvButton() {
	   JButton jb = new JButton("Advanced");
	   jb.addActionListener();
	   return jb;
   }

   private JButton ExpButton() {
	   JButton jb = new JButton("Expert");
	   jb.addActionListener();
	   return jb;
   }

   private void actionPerformed(ActionEvent ae) {
	   String action = ae.getActionCommand();
	   if (action.equals("Beginner"))
		   createWindow(Level.BEGINNER);
	   if (action.equals("Advnaced"))
		   createWindow(Level.ADVANCED);
	   if (action.equals("Expert"))
		   createWindow(Level.EXPERT);
       }*/ 


