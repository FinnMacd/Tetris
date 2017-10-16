package screen;

import java.awt.Graphics;
import java.util.Random;

import main.GamePanel;
import main.Inputs;
import entity.Piece;
import entity.Tile;

//class controlling all boards on screen
public class Board {
	
	//array of tiles 
	private Tile[][] tiles;
	
	//width and height of board
	private int width, height;
	
	//boolean controlling whether board needs to update
	private boolean isStatic;
	
	//x and y position on the screen of top left corner of board
	public int x, y;
	
	//current piece on the board
	private int[][] piece;
	
	//id of current piece
	private int id = -1;
	
	//location of piece, location of piece's shadow
	private int px,py,sx,sy;
	
	//counters to make the piece ffall, counter to make the piece stick to the board once it touches another tile
	private int offset = 40, counter = 0, endoffset = 40, endCounter = 0;
	
	//random object for selecting pieces
	private Random r;
	
	//inheritance board
	private Board inherit;
	
	//boolean storing whether the piece is touching the bottom
	private boolean endingPiece = false;
	
	//board constructor
	public Board(int width, int height, int x, int y, boolean isStatic, Board inherit){
		//initializes tile array
		tiles = new Tile[height][width];
		//sets board width and height
		this.width = width;
		this.height = height;
		//sets position of board
		this.x = x;
		this.y = y;
		//sets whether board id static
		this.isStatic = isStatic;
		//sets inheritance board
		this.inherit = inherit;
		
		//initializes each tile
		for(int yc = 0; yc < height; yc++){
			for(int xc = 0; xc < width; xc++){
				tiles[yc][xc] = new Tile(xc, yc, this);
			}
		}
		
		//initializes random
		r = new Random();
		
		//if board is dynamic
		if(!isStatic){
			//inherit tiles until chain is filled
			while(id == -1){
				//gets id if inheritance board
				id = inherit.getID();
			}
			//updates piece
			piece = Piece.pieces[id];
			//sets location of piece
			px = 3;
			py = -2;
			//updates the shadow
			updateShadow();
			
		}else{
			//if the board is static, set piece to top corner
			px = 0; 
			py = 0;
		}
		
	}
	
	//update function
	public void update(){
		
		//if the board is static, return
		if(isStatic)return;
		
		//increment both counters
		counter++;
		endCounter++;
		//if the counter is reached
		if(counter >= offset){
			//if the piece hasn't collided with anything
			if(!isCollided(0,1,piece,px,py)){
				//move the piece down
				py++;
				//when the piece is moving faster, points are added
				if(offset == 5)GameScreen.score += 2;
				//if the piece was ending, stop it
				if(endingPiece)endingPiece = false;
			}
			//if the piece has collided and hasn't been recorded yet
			else if(isCollided(0,1,piece,px,py)&&endingPiece == false){
				//sets ending to true
				endingPiece = true;
				//restart end counter
				endCounter = 0;
			}
			//restart counter
			counter = 0;
		}
		
		//if the piece is ending and the timer is up
		if(endingPiece == true && endCounter>=endoffset){
			//end the piece
			endPiece();
			//restart end sequence
			endingPiece = false;
		}
		
		//if up is pressed
		if(Inputs.up){
			//set up to false so this is only called once
			Inputs.up = false;
			//if the  piece can rotate without colliding
			if(!isCollided(0,0,Piece.rotate(piece),px,py)){
				//rotate piece
				piece = Piece.rotate(piece);
				//update shadow
				updateShadow();
			}
		}
		
		//if left is pressed
		if(Inputs.left){
			//set left to false so this is only called once
			Inputs.left = false;
			//if the piece can move left without colliding
			if(!isCollided(-1,0,piece,px,py)){
				//move left
				px--;
				//update shadow
				updateShadow();
			}
		}
		
		//if right is pressed
		if(Inputs.right){
			//set right to false
			Inputs.right = false;
			//if the piece can move right without colliding
			if(!isCollided(1,0,piece,px,py)){
				//move right
				px++;
				//update shadow
				updateShadow();
			}
		}
		
		//if space is pressed
		if(Inputs.space){
			//set space to false so this will only run once
			Inputs.space = false;
			//while the piece can move down
			while(!isCollided(0,1,piece,px,py)){
				//move piece down
				py++;
				//add points to score
				GameScreen.score += 4;
			}
			//end the piece
			endPiece();
		}
		
		//if down is pressed
		if(Inputs.down){
			//shorten offset
			offset = 5;
		}
		//otherwise set it to 40
		else offset = 40;
		
		//if c is pressed
		if(Inputs.c){
			//temporarily stores hold piece
			Board temp = GameScreen.holdBoard;
			
			//if the holds board was empty
			if(temp.id == -1){
				//set hold board's piece to current piece
				GameScreen.holdBoard.piece = Piece.pieces[id];
				GameScreen.holdBoard.id = id;
				//gets next piece
				getNextPiece();
			}else{
				//stores hold boards id
				int t = temp.id;
				//sets hold boards piece to current piece
				temp.piece = Piece.pieces[id];
				temp.id = id;
				//sets current piece to hold boards piece
				piece = Piece.pieces[t];
				id = t;
				//resets position
				px = 3;
				py = -2;
			}
			//sets c to false so this will only run once
			Inputs.c = false;
		}
		
	}
	
	//updates shadow position
	private void updateShadow(){
		
		//sets shadows position to current position
		sx = px;
		sy = py;
		
		//while the shadow can still move down
		while(!isCollided(0,1,piece,sx,sy)){
			//move shadow down
			sy++;
		}
		
	}
	
	//end piece
	private void endPiece(){
		//try to put all populated tiles in piece on to board
		try{
			//loops through position of piece
			for(int yc = 0; yc < piece.length; yc++){
				for(int xc = 0; xc < piece.length; xc++){
					//if the piece is populated, put it on the board
					if(piece[yc][xc] == 1)tiles[yc + py][xc + px].setCurrent(id+1);
				}
			}
		}
		//if tiles are not on the boards
		catch(Exception e){
			//set end state to lost
			EndScreen.ending = 1;
			//set game state to end
			GamePanel.state = 2;
                        //sets score
                        EndScreen.score = GameScreen.score;
		}
		
		//gets next piece
		getNextPiece();
		
		//checks for lines
		checkScore();
		
		//updates shadow
		updateShadow();
		
	}
	
	//gets next piece
	private void getNextPiece(){
		//inherits piece
		id = inherit.getID();
		piece = Piece.pieces[id];
		
		//sets piece position
		px = 3;
		py = -2;
	}
	
	//checks for lines completed
	private void checkScore(){
		
		//line counter
		int lines = 0;
		
		//for each row
		for(int yc = 0; yc < height; yc++){
			//boolean to see if every tile in row is filled
			boolean pass = true;
			//loops through every tile in the row
			xcounter:
			for(int xc = 0; xc < width; xc++){
				//if the tile isn't populated
				if(tiles[yc][xc].getCurrent() < 1){
					//the row doesnt pass
					pass = false;
					//breaks the search
					break xcounter;
				}
			}
			
			//if the line passed
			if(pass){
				//add a line
				lines++;
				//advances this line
				advanceLine(yc);
			}
		}
		
		//if there are less than three lines scored add 100 points for each line
		if(lines < 4)GameScreen.score += lines * 100;
		//is 4 lines are scored add 80 points
		else GameScreen.score += 800;
		
	}
	
	//advances a certain line
	private void advanceLine(int line){
		
		//moves every line above the completed one down one
		for(int yc = line; yc > 0; yc--){
			//loops through every tile in each line
			for(int xc = 0; xc < width; xc++){
				//sets tile to the one above it
				tiles[yc][xc] = new Tile(tiles[yc-1][xc].getX(), yc, this, tiles[yc-1][xc].getCurrent());
			}
		}
		
		//initializes top line
		for(int xc = 0; xc < width; xc++){
			tiles[0][xc] = new Tile(xc, 0, this);
		}
		
	}
	
	//draw function
	public void draw(Graphics g){
		
		//if the piece is initialized
		if(piece != null){
			//if the board isn't static draw the shadow
			if(!isStatic)Piece.draw(g, sx, sy, x, y, piece, 7);
			//draw the piece
			Piece.draw(g, px, py, x, y, piece, id);
		}
		
		//for each tile array in tiles
		for(Tile[] arry:tiles){
			//for each tile in the tile array
			for(Tile t:arry){
				//draw the tile
				t.draw(g);
			}
		}
		
	}
	
	//checks if the piece is collided
	private boolean isCollided(int xoffset, int yoffset, int[][] piece, int px, int py){
		
		//for each row in the piece
		for(int yc = 0; yc < piece.length; yc++){
			//for each tile in the row
			for(int xc = 0; xc < piece.length; xc++){
				//if the tile isn't on the board continue
				if(py + yc + yoffset < 0)continue;
				//if the tile is off the screen return true
				if(piece[yc][xc] == 1 && (py + yc + yoffset >= height || px + xc + xoffset < 0 || px + xc + xoffset >= width))return true;
				//if the tile is on a populated square return true
				if(piece[yc][xc] == 1 && (tiles[py + yc + yoffset][px + xc + xoffset].getCurrent() > 0))return true;
				
			}
		}
		
		//else return false
		return false;
		
	}
	
	//gets the pieces id
	public int getID(){
		//int to store the id
		int i;
		//if the board has an inheritance
		if(inherit!=null){
			//return current id
			i = id;
			//get id from inheritance
			id = inherit.getID();
		}
		//if the board doesn't have an inheritance
		else{
			//return current id
			i = id;
			//get a new, random piece
			id = r.nextInt(7);
		}
		//if the piece is real
		if(id != -1)
			//set the current piece to the current id
			piece = Piece.pieces[id];
		//return i
		return i;
	}
	
}
