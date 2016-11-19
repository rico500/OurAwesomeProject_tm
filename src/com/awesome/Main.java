package com.awesome;
import com.awesome.cognitive.Emotion;

import com.awesome.hardware.Headset;
import com.awesome.hardware.Keyboard;
import com.awesome.hardware.WebcamSupport;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WebcamSupport.showWebCamLiveFeed();
        Keyboard.init();
        Headset.init();

        while(true){
            WebcamSupport.getSnapshot();
            Keyboard.setColor(0, 255, 0);
            Headset.setColor(0, 255, 0);
        }
        //Keyboard.shutdown();
    }
}
