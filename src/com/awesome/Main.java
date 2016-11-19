package com.awesome;

import com.awesome.hardware.Keyboard;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WebcamSupport.getSnapshot();
        Keyboard.initKeyboard();
        Keyboard.setColor(0, 255, 0);
        //Keyboard.shutdownKeyboard();
    }
}
