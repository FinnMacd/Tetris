package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import screen.Board;
import screen.EndScreen;
import screen.GameScreen;
import screen.MenuScreen;

//Game class running the basic loop and graphics
public class GamePanel extends JPanel implements Runnable{
	
	//stores width and hight variables
	public static int WIDTH = 350, HEIGHT = 340;
	
	//initiates two different sizes of fonts
	public static Font small = new Font("TimesNewRoman",0,12), large = new Font("TimesNewRoman",0,36);
	
	//creates several board objects
	public static Board mainBoard, p1Board, p2Board, p3Board, holdBoard;
	
	//stores the current gamestate(game, menu, end)
	public static int state = 0;
	
	//menu state
	private MenuScreen menu = new MenuScreen();
	//end state
	private EndScreen end = new EndScreen();
	//game state
	private static GameScreen game = new GameScreen();
	
	public GamePanel(JFrame j){
		
		//sets size of frame
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//creates an input object
		Inputs input = new Inputs();
		
		//adds input class as a listener to the JPanel
		addMouseListener(input);
		addKeyListener(input);
		addMouseMotionListener(input);
		
		//adds input class as a listener to the JFrame
		j.addMouseListener(input);
		j.addKeyListener(input);
		j.addMouseMotionListener(input);
		
	}
	
	//start void
	public void start(){
		
		//starts a new tread
		new Thread(this, "Main").start();
		
	}
	
	//init void
	public static void init(int i){
		//initializes game for a certain game mode
		game.init(i);
	}
	
	//run class for main thread
	public void run(){
		
		//stores the last time update was called
		long lastTime = System.nanoTime();
		//timer for ups and fps
		long timer = System.currentTimeMillis();
		//nanoseconds in between updates(60 updates a second)
		final double ns = 1000000000.0/60.0;
		//number of times program needs to update
		double catchUp = 0;
		//stores number of frames
		int frames = 0;
		//stores number of updates
		int updates = 0;
		
		while(true){
			
			//current time
			long now = System.nanoTime();
			//number of times the update method must be called
			catchUp += (now-lastTime)/ns;
			//resets time
			lastTime = now;
			
			//runs once for each time it needs to be updated
			while(catchUp >= 1){
				//updates program
				update();
				//increases update counter
				updates++;
				//decreases number of times update function needs to be called
				catchUp--;
			}
			
			//paints objects to screen
			repaint();
			
			//increases frames counter
			frames++;
			
			//runs once a second
			if(System.currentTimeMillis()-timer >= 1000){
				timer += 1000;
				//prints ups and fps
				System.out.println(updates+"ups, "+ frames + "fps");
				updates = frames = 0;
			}
			
			//sets thread to sleep for 10 milliseconds to limit fps
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	//main update void
	private void update(){
		
		//switch to update different class depending on the state
		switch(state){
		case 0:
			menu.update();
			break;
		case 1:
			game.update();
			break;
		case 2:
			end.update();
			break;
		}
		
	}
	
	//main paint method
	public void paint(Graphics g){
		super.paint(g);
		
		//draws a white background
		g.setColor(Color.white);
		g.fillRect(0,0,WIDTH+12,HEIGHT+12);
		
		//switch to update different classes depending on the state
		switch(state){
		case 0:
			menu.draw(g);
			break;
		case 1:
			game.draw(g);
			break;
		case 2:
			end.draw(g);
			break;
		}
		
	}
	
}
