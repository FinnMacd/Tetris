package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//class controlling all inputs
public class Inputs implements KeyListener,MouseListener,MouseMotionListener{
	
	//all booleans containing info on the four movement buttons, space button, c button, and the mouse
	public static volatile boolean left = false, right = false, up = false, down = false, space = false, c = false, mleft = false;
	//integers containing mouses position
	public static int mx = 0,my = 0;
	
	//called when a key is pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//checks if any important button is pressed , sets the appropriate boolean to true
		if(key == KeyEvent.VK_UP)up = true;
		if(key == KeyEvent.VK_DOWN)down = true;
		if(key == KeyEvent.VK_RIGHT)right = true;
		if(key == KeyEvent.VK_LEFT)left = true;
		if(key == KeyEvent.VK_SPACE)space = true;
		if(key == KeyEvent.VK_C)c = true;
		
	}
	
	//called when a key is released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//checks if any important button is pressed , sets the appropriate boolean to false
		if(key == KeyEvent.VK_UP)up = false;
		if(key == KeyEvent.VK_DOWN)down = false;
		if(key == KeyEvent.VK_RIGHT)right = false;
		if(key == KeyEvent.VK_LEFT)left = false;
		if(key == KeyEvent.VK_SPACE)space = false;
		if(key == KeyEvent.VK_C)c = false;
	}

	public void keyTyped(KeyEvent e) {	}
	
	//called when the mouse is dragged
	public void mouseDragged(MouseEvent e) {
		//stores the position of the mouse
		mx = e.getX();
		my = e.getY();
	}
	
	//called when the mouse is moved
	public void mouseMoved(MouseEvent e) {
		//stores the position of the mouse
		mx = e.getX();
		my = e.getY();

	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	//called when the mouse is pressed
	public void mousePressed(MouseEvent e) {
		//if it is the left button, set the appropriate boolean to true
		if(e.getButton() == MouseEvent.BUTTON1)mleft = true;
	}
	
	//called when the mouse is released
	public void mouseReleased(MouseEvent e) {
		//if it is the left button, set the appropriate boolean to false
		if(e.getButton() == MouseEvent.BUTTON1)mleft = false;
	}

}
