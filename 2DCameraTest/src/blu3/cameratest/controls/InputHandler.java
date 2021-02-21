package blu3.cameratest.controls;

import java.awt.event.*;
import java.util.Arrays;

	//------------------------------------------------------------------------------
	// This is a terribly oversized way of handling input, most of these methods 
	// do absolutely nothing, but are required to be there for some reason.
	// TODO: MAJOR refactoring and cleaning
	//------------------------------------------------------------------------------

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

	public boolean[] key = new boolean[68836];
	public static int MouseX;
	public static int MouseY;
	public boolean mouseDown = false;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TOOD: autoresume setting
	}

	@Override
	public void focusLost(FocusEvent e) {
		Arrays.fill(key, false);
		// TODO: pause
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO: something to do with typing rofl
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
