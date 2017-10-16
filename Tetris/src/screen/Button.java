package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import main.Inputs;

//class controlling all butons
public class Button {
	
	//stores text
    private final String text;
    //stores font
    private Font font;
    //stores colour
    private Color c,c2;

    //stores position and size
	private int x, y, width, height;
	//stores mouse position
    private int mx=0, my=0;

    //state of mouse, whether the class has been initialized
    private boolean hover = false, clicked = false, init = false;

    //button constructor
    public Button(String text, int x, int y) {
        //sets text
    	this.text = text;
        //sets fornt
    	font = new Font("TimesNewRoman",0,40);
        //sets colour
    	c = new Color(120, 3, 20);
        //sets position
    	this.x = x;
    	this.y = y;
        //sets colour when hovering
    	c2=new Color(80, 1, 8);
    }
    
    //second constructor
    public Button(String text, int x, int y,Color c,Color c2,int size) {
    	//sets text
    	this.text = text;
        //sets font
    	font = new Font("TimesNewRoman",0,size);
        //sets colours and position
    	this.c = c;
        this.c2 = c2;
        this.x = x;
        this.y = y;
    }
    
    //update void
    public void update() {
    	//calls updateInputs function
    	updateInputs();
    	//checks if the program has been initialized
        if (!init) return;
        //checks if the mouse is on top of the text or not
        if(mx>x&&mx<x+width&&my<y&&my>y-height/2){
            hover=true;
        }else{
            hover = false;
        }
    }
    
    //updateInputs function
    public void updateInputs(){
    	//checks if the mouse is hovering and clicked
    	if(hover && Inputs.mleft){
        	clicked = true;
        	Inputs.mleft = false;
        }else{
        	clicked = false;
        }
    	//updates mouse positions
    	mx = Inputs.mx;
    	my = Inputs.my;
    }
    
    //draw function
    public void draw(Graphics g) {
        //if not initialized
    	if (!init) {
    		//creates and initializes a fontmetrics
            FontMetrics fm = g.getFontMetrics(font);
            //finds and stores width of text
            width = fm.stringWidth(text);
            //finds and stores height of text
            height = fm.getHeight();
            //set initialization to true
            init = true;
        }
    	//sets button font
        g.setFont(font);
        //sets button colour
        g.setColor(c);
        //sets secondary colour if hovering
        if(hover)g.setColor(c2);
        //draws text
        g.drawString(text, x, y);
    }
    
    //isClicked method, returns whether the button has been clicked
    public boolean isClicked(){
        return clicked;
    }
    
    //getText method returns button text
    public String getText(){
    	return text;
    }
    
}
