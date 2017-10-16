package screen;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

//class controlling graphics at the end of the game
public class EndScreen {
	
	//creates and initiates two buttons
	private Button restart = new Button("Restart", 20, 140), menu = new Button("Menu", 20, 180);
	
	//stores the two potential titles
	private final String title1 = "You Lose :(", title2 = "Times Up!";
	
	//stores the type of ending
	public static int ending = 1;
	
	//stores the players score
	public static int score = 0;
	
	//draw function
	public void draw(Graphics g){
		
		//sets font
		g.setFont(GamePanel.large);
		//sets colour
		g.setColor(Color.black);
		//switch for ending, draws appropriate title
		switch(ending){
		case 1:
			g.drawString(title1, 20, 40);
			break;
		case 2:
			g.drawString(title2, 20, 40);
			break;
		}
                
                g.drawString("Score: " + score, 20, 90);
                
		//draws both buttons
		restart.draw(g);
		menu.draw(g);
		
	}
	
	//update function
	public void update(){
		
		//updates both buttons
		restart.update();
		menu.update();
		//checks if either button has been clicked
		if(restart.isClicked()){
			//sets game state
			GamePanel.init(-1);
			GamePanel.state = 0;
		}
		if(menu.isClicked()){
			//sets game state
			GamePanel.state = 0;
		}
		
	}
	
}
