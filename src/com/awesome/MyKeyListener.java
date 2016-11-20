package com.awesome;

import com.logitech.gaming.LogiLED;
import com.sun.org.apache.xml.internal.security.keys.content.KeyName;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Usuari on 20/11/2016.
 */
class MyKeyListener implements KeyListener {

    private final Set<Character> pressedKeys = new HashSet<>();
    private final Map<Integer, Byte> ASCIItoScanCodes = new HashMap<>();

    public MyKeyListener(){
        ASCIItoScanCodes.put(49, (byte) LogiLED.ONE);
        ASCIItoScanCodes.put(50, (byte) LogiLED.TWO);
        ASCIItoScanCodes.put(51, (byte) LogiLED.THREE);
        ASCIItoScanCodes.put(52, (byte) LogiLED.FOUR);
        ASCIItoScanCodes.put(53, (byte) LogiLED.FIVE);
        ASCIItoScanCodes.put(54, (byte) LogiLED.SIX);
        ASCIItoScanCodes.put(55, (byte) LogiLED.SEVEN);
        ASCIItoScanCodes.put(56, (byte) LogiLED.EIGHT);
        ASCIItoScanCodes.put(57, (byte) LogiLED.NINE);
        ASCIItoScanCodes.put(48, (byte) LogiLED.ZERO);
        ASCIItoScanCodes.put(39, (byte) LogiLED.MINUS);
        ASCIItoScanCodes.put(161, (byte) LogiLED.EQUALS);
        ASCIItoScanCodes.put(8, (byte) LogiLED.BACKSPACE);
        ASCIItoScanCodes.put(47, (byte) LogiLED.NUM_SLASH);
        ASCIItoScanCodes.put(42, (byte) LogiLED.NUM_ASTERISK);
        ASCIItoScanCodes.put(45, (byte) LogiLED.NUM_MINUS);
        ASCIItoScanCodes.put((int)'Q', (byte) LogiLED.Q);
        ASCIItoScanCodes.put((int)'q', (byte) LogiLED.Q);
        ASCIItoScanCodes.put((int)'W', (byte) LogiLED.W);
        ASCIItoScanCodes.put((int)'w', (byte) LogiLED.W);
        ASCIItoScanCodes.put((int)'E', (byte) LogiLED.E);
        ASCIItoScanCodes.put((int)'e', (byte) LogiLED.E);
        ASCIItoScanCodes.put((int)'R', (byte) LogiLED.R);
        ASCIItoScanCodes.put((int)'r', (byte) LogiLED.R);
        ASCIItoScanCodes.put((int)'T', (byte) LogiLED.T);
        ASCIItoScanCodes.put((int)'t', (byte) LogiLED.T);
        ASCIItoScanCodes.put((int)'Y', (byte) LogiLED.Y);
        ASCIItoScanCodes.put((int)'y', (byte) LogiLED.Y);
        ASCIItoScanCodes.put((int)'U', (byte) LogiLED.U);
        ASCIItoScanCodes.put((int)'u', (byte) LogiLED.U);
        ASCIItoScanCodes.put((int)'I', (byte) LogiLED.I);
        ASCIItoScanCodes.put((int)'i', (byte) LogiLED.I);
        ASCIItoScanCodes.put((int)'O', (byte) LogiLED.O);
        ASCIItoScanCodes.put((int)'o', (byte) LogiLED.O);
        ASCIItoScanCodes.put((int)'P', (byte) LogiLED.P);
        ASCIItoScanCodes.put((int)'p', (byte) LogiLED.P);
        ASCIItoScanCodes.put(96, (byte) LogiLED.OPEN_BRACKET);
        ASCIItoScanCodes.put(43, (byte) LogiLED.CLOSE_BRACKET);
        ASCIItoScanCodes.put(231, (byte) LogiLED.BACKSLASH);
        ASCIItoScanCodes.put(127, (byte) LogiLED.KEYBOARD_DELETE);
        ASCIItoScanCodes.put(55, (byte) LogiLED.NUM_SEVEN);
        ASCIItoScanCodes.put(56, (byte) LogiLED.NUM_EIGHT);
        ASCIItoScanCodes.put(57, (byte) LogiLED.NUM_NINE);
        ASCIItoScanCodes.put(43, (byte) LogiLED.NUM_PLUS);
        ASCIItoScanCodes.put((int)'A', (byte) LogiLED.A);
        ASCIItoScanCodes.put((int)'a', (byte) LogiLED.A);
        ASCIItoScanCodes.put((int)'S', (byte) LogiLED.S);
        ASCIItoScanCodes.put((int)'s', (byte) LogiLED.S);
        ASCIItoScanCodes.put((int)'D', (byte) LogiLED.D);
        ASCIItoScanCodes.put((int)'d', (byte) LogiLED.D);
        ASCIItoScanCodes.put((int)'F', (byte) LogiLED.F);
        ASCIItoScanCodes.put((int)'f', (byte) LogiLED.F);
        ASCIItoScanCodes.put((int)'G', (byte) LogiLED.G);
        ASCIItoScanCodes.put((int)'g', (byte) LogiLED.G);
        ASCIItoScanCodes.put((int)'H', (byte) LogiLED.H);
        ASCIItoScanCodes.put((int)'h', (byte) LogiLED.H);
        ASCIItoScanCodes.put((int)'J', (byte) LogiLED.J);
        ASCIItoScanCodes.put((int)'j', (byte) LogiLED.J);
        ASCIItoScanCodes.put((int)'K', (byte) LogiLED.K);
        ASCIItoScanCodes.put((int)'k', (byte) LogiLED.K);
        ASCIItoScanCodes.put((int)'L', (byte) LogiLED.L);
        ASCIItoScanCodes.put((int)'l', (byte) LogiLED.L);
        ASCIItoScanCodes.put(209, (byte) LogiLED.SEMICOLON);
        ASCIItoScanCodes.put(180, (byte) LogiLED.APOSTROPHE);
        ASCIItoScanCodes.put(10, (byte) LogiLED.ENTER);
        ASCIItoScanCodes.put(52, (byte) LogiLED.NUM_FOUR);
        ASCIItoScanCodes.put(53, (byte) LogiLED.NUM_FIVE);
        ASCIItoScanCodes.put(54, (byte) LogiLED.NUM_SIX);
        ASCIItoScanCodes.put((int)'Z', (byte) LogiLED.Z);
        ASCIItoScanCodes.put((int)'z', (byte) LogiLED.Z);
        ASCIItoScanCodes.put((int)'X', (byte) LogiLED.X);
        ASCIItoScanCodes.put((int)'x', (byte) LogiLED.X);
        ASCIItoScanCodes.put((int)'C', (byte) LogiLED.C);
        ASCIItoScanCodes.put((int)'c', (byte) LogiLED.C);
        ASCIItoScanCodes.put((int)'V', (byte) LogiLED.V);
        ASCIItoScanCodes.put((int)'v', (byte) LogiLED.V);
        ASCIItoScanCodes.put((int)'B', (byte) LogiLED.B);
        ASCIItoScanCodes.put((int)'b', (byte) LogiLED.B);
        ASCIItoScanCodes.put((int)'N', (byte) LogiLED.N);
        ASCIItoScanCodes.put((int)'n', (byte) LogiLED.N);
        ASCIItoScanCodes.put((int)'M', (byte) LogiLED.M);
        ASCIItoScanCodes.put((int)'m', (byte) LogiLED.M);
        ASCIItoScanCodes.put(44, (byte) LogiLED.COMMA);
        ASCIItoScanCodes.put(46, (byte) LogiLED.PERIOD);
        ASCIItoScanCodes.put(-45, (byte) LogiLED.FORWARD_SLASH);
        ASCIItoScanCodes.put(49, (byte) LogiLED.NUM_ONE);
        ASCIItoScanCodes.put(50, (byte) LogiLED.NUM_TWO);
        ASCIItoScanCodes.put(51, (byte) LogiLED.NUM_THREE);
        ASCIItoScanCodes.put(10, (byte) LogiLED.NUM_ENTER);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //int keyPressedIndex = (int)(e.getKeyChar())-97;
        //byte foo = (byte) ((int) e.getKeyChar());
        //byte keyCodes[] = {0x1E, 0x30, 0x2E, 0x20, 0x12, 0x21, 0x22, 0x23, 0x17, 0x24, 0x25, 0x26, 0x32, 0x31, 0x18, 0x19, 0x10, 0x13, 0x1F, 0x14, 0x16, 0x2F, 0x11, 0x2D, 0x15, 0x2C};
        if (!pressedKeys.contains(e.getKeyChar())){
            pressedKeys.add(e.getKeyChar());
            new Thread(() -> {
                System.out.println(ASCIItoScanCodes.get((int) e.getKeyChar()));
                LogiLED.LogiLedSaveLightingForKey(ASCIItoScanCodes.get((int) e.getKeyChar()));
                LogiLED.LogiLedSetLightingForKeyWithKeyName(ASCIItoScanCodes.get((int) e.getKeyChar()), 100, 0, 0);
                try{
                    Thread.sleep(750);
                } catch(InterruptedException e1){
                    e1.printStackTrace();
                }
                pressedKeys.remove(e.getKeyChar());
                LogiLED.LogiLedRestoreLightingForKey(ASCIItoScanCodes.get((int) e.getKeyChar()));
            }).start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}