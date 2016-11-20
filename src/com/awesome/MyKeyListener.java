package com.awesome;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Usuari on 20/11/2016.
 */
class MyKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}