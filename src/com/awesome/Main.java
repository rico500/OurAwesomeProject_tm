package com.awesome;
import com.awesome.cognitive.Colours;
import com.awesome.cognitive.Emotion;
import com.awesome.cognitive.Colour;

import java.lang.Thread;

import com.awesome.hardware.Keyboard;
import com.awesome.hardware.WebcamSupport;
import com.oracle.javafx.jmx.json.JSONException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        WebcamSupport.showWebCamLiveFeed();
        //Keyboard.initKeyboard();

        try {

            while (true) {
                    Thread.sleep(2000);
                    String s = Emotion.getEmotion(WebcamSupport.getSnapshot());
                    s = s.substring(1, s.length() - 1);
                    System.out.println(s);

                    if (!s.equalsIgnoreCase("")) {

                        try {

                            Colour c = Colours.generateColour(s);
                            System.out.println(c.toString());

                            Keyboard.setColor(c);
                        } catch (org.json.JSONException e) {
                            System.err.println("Parsing error! JSON : " + s);
                        }
                    }
                    else{
                            System.out.println("Nobody there!");
                        }
                    }

            }
        catch(InterruptedException e){
                System.out.print(e.getMessage());
            }
    }
        //Keyboard.shutdownKeyboard();
}
