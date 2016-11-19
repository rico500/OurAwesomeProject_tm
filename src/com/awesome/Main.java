package com.awesome;

import com.awesome.hardware.Keyboard;
import com.awesome.hardware.WebcamSupport;

public class Main {

    public static void main(String[] args) {
        //Test
        WebcamSupport.getSnapshot();
        Keyboard myKeyboard = new Keyboard();
        myKeyboard.setColor(100,0,0);
    }
}
