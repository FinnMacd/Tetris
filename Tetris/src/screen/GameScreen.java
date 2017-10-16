package screen;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

//class managing the program while in it's game state
public class GameScreen {
	
	//creates a mainboard, the three boards showing pieces coming up, and the board holding pieces
	public static Board mainBoard, p1Board, p2Board, p3Board, holdBoard;
	
	//integer storing the games score
	public static int score = 0;
	
	//stores the gamestate, stores the time left
	private int state = 1, time = 60;
	//stores the starting time
	private long startTime = 0;
	
	//initialization funciton, takes gametype
	public void init(int i){
		
		//initializes all boards
		p3Board = new Board(4, 4, 270, 215, true, null);
		p2Board = new Board(4, 4, 270, 140, true,p3Board);
		p1Board = new Board(4, 4, 270, 65, true,p2Board);
		mainBoard = new Board(10, 20, 100, 20, false,p1Board);
		holdBoard = new Board(4, 4, 20, 20, true,null);
		//resets score
		score = 0;
		//resets time
		time = 60;
		//sets the game state
		if(i != -1)state = i;
		//sets the start time
		startTime = System.currentTimeMillis();
		
	}
	
	//update funciton
	public void update(){
		//updates main board
		mainBoard.update();
		//if in a timed game state, remove time
		if(state == 2)time = 30 - (int)((System.currentTimeMillis() - startTime)/1000);
		//once time is up
		if(time < 0){
			//sets end state
			EndScreen.ending = 2;
			//sets game state
			GamePanel.state = 2;
                        //sets score
                        EndScreen.score = score;
		}
	}
	
	//graphics function
	public void draw(Graphics g){
		//sets font
		g.setFont(GamePanel.small);
		//draws all boards
		mainBoard.draw(g);
		p1Board.draw(g);
		p2Board.draw(g);
		p3Board.draw(g);
		holdBoard.draw(g);
		//sets colour
		g.setColor(Color.black);
		//draws score
		g.drawString("Score: " + score, 270, 20);
		//draws time if in timed state
		if(state == 2)g.drawString("Time: " + time, 270, 60);
	}
	
}
