package entity;

import java.awt.Color;
import java.awt.Graphics;

import screen.Board;

//class for each tile in a board
public class Tile {
	
	//position on board, current colour
	private int x, y, current;
	//board the tile is on
	private Board board;
	
	//size of a tile
	public static final int size = 15;
	
	//constructor without colour already set
	public Tile(int x, int y, Board board){
		//sets position and board
		this.x = x;
		this.y = y;
		this.board = board;
	}
	
	//constructor with colour already set
	public Tile(int x, int y, Board board, int current){
		//sets position, board, and colour
		this.x = x;
		this.y = y;
		this.board = board;
		this.current = current;
	}
	
	public void draw(Graphics g){
		
		//if the current tile is not empty
		if(current != 0){
			
			//switch for the current colour of the tile
			switch(current-1){
			
			case 0: g.setColor(Color.red);
			break;
			case 1: g.setColor(Color.blue);
			break;
			case 2: g.setColor(Color.orange);
			break;
			case 3: g.setColor(Color.green);
			break;
			case 4: g.setColor(Color.pink);
			break;
			case 5: g.setColor(Color.cyan);
			break;
			case 6: g.setColor(Color.yellow);
			break;
			
			}
			
			//draws the tile
			g.fillRect(board.x + x*size, board.y + y*size, size, size);
			
		}
		
		//draws the tile's border
		g.setColor(Color.gray);
		g.drawRect(board.x + x*size, board.y + y*size, size, size);
		
	}
	
	//gets the current state of the tile
	public int getCurrent(){
		return current;
	}
	
	//sets the current state of the tile
	public void setCurrent(int i){
		current = i;
	}
	
	//gets the x position of the tile
	public int getX() {
		return x;
	}
	
	//sets the x position of the tile
	public void setX(int x) {
		this.x = x;
	}
	
	//gets the y position of the tile
	public int getY() {
		return y;
	}
	
	//sets the y position of the tile
	public void setY(int y) {
		this.y = y;
	}
	
}
