package blu3.cameratest.controls;

import java.awt.event.*;
import java.util.Arrays;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

	public boolean[] key = new boolean[68836];
	public static int MouseX;
	public static int MouseY;
	public boolean mouseDown;
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		Arrays.fill(key, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length) {
			key[keyCode] = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length) {
			key[keyCode] = false;
		}
		
	}

}