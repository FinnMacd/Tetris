package screen;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

//class controlling menu graphics
public class MenuScreen {
	
	//adds and initiates three buttons
	private Button freeplay = new Button("Free Play", 20, 100), timetrial = new Button("Time Trials (30s)", 20, 140), exit = new Button("Exit", 20, 180);
	
	//stores game title
	private final String title = "Tetris";
	
	//draw void
	public void draw(Graphics g){
		
		//sets font, colour
		g.setFont(GamePanel.large);
		g.setColor(Color.black);
		//draws title
		g.drawString(title, 20, 40);
		//draws all three buttons
		freeplay.draw(g);
		timetrial.draw(g);
		exit.draw(g);
		
	}
	
	//update void
	public void update(){
		
		//updates all buttons
		freeplay.update();
		timetrial.update();
		exit.update();
		//checks if any button is pressed
		if(freeplay.isClicked()){
			///changes game state
			GamePanel.init(1);
			GamePanel.state = 1;
		}else if(timetrial.isClicked()){
			//changes game state
			GamePanel.init(2);
			GamePanel.state = 1;
		}
		if(exit.isClicked()){
			//exits
			System.exit(0);
		}
		
	}
	
}
