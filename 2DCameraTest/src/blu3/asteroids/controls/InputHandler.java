package blu3.asteroids.controls;

import blu3.asteroids.games.asteroids.Game;

import java.awt.event.*;
import java.util.Arrays;

//------------------------------------------------------------------------------
// This is a terribly oversized way of handling input, most of these methods
// do absolutely nothing, but are required to be there for some reason.
// TODO: MAJOR refactoring and cleaning
// for i am useless and do not know how 2 code good
// (more accurately i didn't know what i wanted to do with this project until a lot of unnecessary bullshit had piled up)
//------------------------------------------------------------------------------


// ...
// when did i write this? i don't remember writing half these comments
public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

    public final boolean[] key = new boolean[68836];
    public boolean mouseDown = false;

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

    @Override
    public void mousePressed(MouseEvent e) {
        if (Game.paused()) return; // don't accidentally shoot or something when regaining focus ie. unpausing
        mouseDown = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        Arrays.fill(key, false);
        Game.setPaused(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void focusGained(FocusEvent e) {
        Game.setPaused(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // do nothing
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
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // do nothing
    }
}
