package graphics;

import javax.swing.*;

import game.Game;
import game.Level;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 

@SuppressWarnings("unused")
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
   
   /*private JPanel addMenu() {
	   JPanel jp = new JPanel();
	   jp.setLayout(new GridLayout(3,1));
	   JButton b = new JButton("Beginner");
	   b.addActionListener( 
	   void ActionPerformed(ActionEvent arg0) {
		   new Game(Level.BEGINNER);
		   createWindow();
	   });
	   }
   }*/
      
   public static void main(String[] args) {
      createWindow();
   }
} 


