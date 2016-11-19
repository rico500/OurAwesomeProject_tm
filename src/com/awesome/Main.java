package com.awesome;
import com.awesome.cognitive.Colours;
import com.awesome.cognitive.Emotion;
import com.awesome.cognitive.Colour;

import java.lang.Thread;

import com.awesome.hardware.Keyboard;
import com.awesome.hardware.WebcamSupport;

public class Main {

    public static void main(String[] args) {
	// write your code here

        WebcamSupport.showWebCamLiveFeed();
        Keyboard.init();
        Mouse.init();
        Headset.init();

        try {

            while (true) {
                    Thread.sleep(500);
                    String s = Emotion.getEmotion(WebcamSupport.getSnapshot());
                    s = s.substring(1, s.length() - 1);
                    System.out.println(s);

                    if (!s.equalsIgnoreCase("")) {

                        try {

                            //Colour c = Colours.generateColour(s);
                            Colour c = Colours.multiConvert(s);
                            System.out.println(c.toString());

                            Keyboard.setColor(c);
                            Mouse.setColor(c);
                            Headset.setColor(c);
                        }catch(org.json.JSONException e){
                            System.err.println("Parsing error! JSON : " + s);
                        }

                    }


                    //Keyboard.setColor(0, 255, 0);
            }

        }
        catch(InterruptedException e){
            Keyboard.shutdown();
            System.out.print(e.getMessage());
            }
    }
}
