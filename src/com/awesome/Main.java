package com.awesome;
import com.awesome.cognitive.Emotion;

import com.awesome.hardware.Keyboard;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Keyboard.initKeyboard();
        Keyboard.setColor(0, 255, 0);
        //Keyboard.shutdownKeyboard();
    }
}
