package com.awesome;

import com.awesome.hardware.Keyboard;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Keyboard.initKeyboard();
        Keyboard.setColor(0, 255, 0);
        Keyboard.setUpperRow(0, 0, 255, 255);
        //Keyboard.shutdownKeyboard();
    }
}
