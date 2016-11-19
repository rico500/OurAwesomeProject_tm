package com.awesome;
import com.awesome.cognitive.Emotion;

import java.lang.Thread;

import com.awesome.hardware.Keyboard;
import com.awesome.hardware.WebcamSupport;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WebcamSupport.showWebCamLiveFeed();
        //Keyboard.initKeyboard();

        try {

            while (true) {
                Thread.sleep(2000);
                System.out.println(Emotion.getEmotion(WebcamSupport.getSnapshot()));
                //Keyboard.setColor(0, 255, 0);
            }

        }catch(InterruptedException e){
                System.out.print(e.getMessage());
            }
    }
        //Keyboard.shutdownKeyboard();
}
