package controller;

import model.ModelGrill;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyListener to play game with keyboard arrows
 */
public class KeyboardListener implements KeyListener {

    private ModelGrill model;

    public KeyboardListener(ModelGrill model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!this.model.isWin()){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP    -> model.playSouth();
            case KeyEvent.VK_DOWN  -> model.playNorth();
            case KeyEvent.VK_LEFT  -> model.playEast();
            case KeyEvent.VK_RIGHT -> model.playWest();
        }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
