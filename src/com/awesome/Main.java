package com.awesome;

import com.awesome.hardware.Keyboard;
import com.awesome.hardware.WebcamSupport;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WebcamSupport.showWebCamLiveFeed();
        Keyboard.initKeyboard();
        Keyboard.setColor(0, 255, 0);
        //Keyboard.shutdownKeyboard();
    }
}
