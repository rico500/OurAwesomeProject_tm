package com.awesome;

import com.logitech.gaming.LogiLED;
import com.sun.org.apache.xml.internal.security.keys.content.KeyName;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Usuari on 20/11/2016.
 */
class MyKeyListener implements KeyListener {

    private final Set<Character> pressedKeys = new HashSet<>();
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressedIndex = (int)(e.getKeyChar())-97;
        byte foo = (byte) ((int) e.getKeyChar());
        byte keyCodes[] = {0x1E, 0x30, 0x2E, 0x20, 0x12, 0x21, 0x22, 0x23, 0x17, 0x24, 0x25, 0x26, 0x32, 0x31, 0x18, 0x19, 0x10, 0x13, 0x1F, 0x14, 0x16, 0x2F, 0x11, 0x2D, 0x15, 0x2C};
        System.out.println(keyPressedIndex);

        if (!pressedKeys.contains(e.getKeyChar())){
            pressedKeys.add(e.getKeyChar());
            new Thread(() -> {
                LogiLED.LogiLedSaveLightingForKey((byte) ((int) e.getKeyChar()));
                LogiLED.LogiLedSetLightingForKeyWithKeyName((byte) ((int) e.getKeyChar()), 100, 0, 0);
                try{
                    Thread.sleep(750);
                } catch(InterruptedException e1){
                    e1.printStackTrace();
                }
                pressedKeys.remove(e.getKeyChar());
                LogiLED.LogiLedRestoreLightingForKey((byte) ((int) e.getKeyChar()));
            }).start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}