package main;

import javax.swing.JFrame;

/*
 * Author: Finn Macdonald
 * Class: Gr. 11 Computer Science
 * Date: January 20, 2016
 * Discription: A tetris game for a computer science culminating
 * 
 */

//main class for the program
public class Main {
	
	//stores the game title
	public static String Title = "Tetris";
	
	//main void for the program
	public static void main(String[] args){
		
		//creates the frame for the game
		JFrame j = new JFrame(Title);
		//creates a game object
		GamePanel game = new GamePanel(j);
		
		//adds game to the frame
		j.add(game);
		
		//sets necessary aspects of frame
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setResizable(false);
		j.pack();
		j.setLocationRelativeTo(null);
		
		//calls start method in game
		game.start();
		
		//sets visibility to true
		j.setVisible(true);
		
	}
	
}
