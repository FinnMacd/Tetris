package entity;

import java.awt.Color;
import java.awt.Graphics;

//class for controlling individual pieces
public class Piece {
	
	//initializes all pieces used in game
	public static int[][][] pieces = new int[][][]{
		
		{
			{0,1,0},
			{1,1,1},
			{0,0,0}
		},
		
		{
			{0,0,0},
			{0,1,1},
			{1,1,0}
		},
		
		{
			{0,0,0},
			{1,1,0},
			{0,1,1}
		},
		
		{
			{0,1,0},
			{0,1,0},
			{1,1,0}
		},
		
		{
			{0,1,0},
			{0,1,0},
			{0,1,1}
		},
		
		{
			{1,1},
			{1,1}
		},
		
		{
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0}
		},
		
	};
	
	//static rotate method to turn pieces
	public static int[][] rotate(int start[][]){
		
		//depending on size of piece correct method is used to turn piece
		switch(start.length){
			case 4:return rotate4(start);
			case 3:return rotate3(start);
			case 2:return rotate2(start);
		}
		
		//default return
		return null;
		
	}
	
	//method to rotate array of size 4
	private static int[][]rotate4(int start[][]){
		
		//creates and returns new rotated piece
		return new int[][]{
			
			{start[3][0],start[2][0],start[1][0],start[0][0]},
			{start[3][1],start[2][1],start[1][1],start[0][1]},
			{start[3][2],start[2][2],start[1][2],start[0][2]},
			{start[3][3],start[2][3],start[1][3],start[0][3]}
			
		};
		
	}
	
	//method to rotate array of size 3
	private static int[][]rotate3(int start[][]){
		
		//creates and returns new rotated piece
		return new int[][]{
			
			{start[2][0],start[1][0],start[0][0]},
			{start[2][1],start[1][1],start[0][1]},
			{start[2][2],start[1][2],start[0][2]}
			
		};
		
	}
	
	//method to rotate array of size 2
	private static int[][]rotate2(int start[][]){
		
		//the only piece of size 2 is a square, so no rotation is needed
		return start;
		
	}
	
	//draw function
	public static void draw(Graphics g, int x, int y, int bx, int by, int[][] piece, int id){
		
		//switch for colour depending on the type of piece
		switch(id){
			
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
		case 7: g.setColor(Color.gray);
		break;
		
		}
		
		//loops through y axis of piece
		for(int yc = 0; yc < piece.length; yc++){
			//loops through x axis of piece
			for(int xc = 0; xc < piece.length; xc++){
				//checks if individual square is populated or if the square is outside of the board
				if(piece[yc][xc] == 0 || yc + y < 0)continue;
				//draws individual square of piece
				g.fillRect(bx + ((x+xc)*Tile.size), by + ((y + yc)*Tile.size), Tile.size, Tile.size);
				
			}
		}
		
	}
	
	
}
