package com.awesome;
import com.awesome.cognitive.Colours;
import com.awesome.cognitive.Emotion;
import com.awesome.cognitive.EmotionEnum;
import com.awesome.cognitive.Colour;

import java.lang.Thread;

import com.awesome.hardware.Headset;
import com.awesome.hardware.Keyboard;
import com.awesome.hardware.Mouse;
import com.awesome.hardware.WebcamSupport;

public class Main {

    private static final boolean hardware_on = false;
    private static final long delay = 100;

    public static void main(String[] args) {
	// write your code here
        WebcamSupport.init();
        WebcamSupport.showWebCamLiveFeed();

        if(hardware_on) {
            Keyboard.init();
            Mouse.init();
            Headset.init();
        }

        try {

            while (true) {
                Thread.sleep(delay);
                    String s = Emotion.getEmotionJSON(WebcamSupport.getSnapshot());
                    s = s.substring(1, s.length() - 1);
                    System.out.println(s);

                    if (!s.equalsIgnoreCase("")) {

                        try {

                            //Colour c = Colours.generateColour(s);
                            Colour c = Colours.multiConvert(s);
                            System.out.println(c.toString());

                            if(hardware_on) {
                                Keyboard.setColor(c);
                                Mouse.setColor(c);
                                Headset.setColourSmoothly(c, delay);
                            }
                        }catch(org.json.JSONException e){
                            System.err.println("Parsing error! JSON : " + s);
                        }

                    }


                    //Keyboard.setColor(0, 255, 0);
            }

        }
        catch(InterruptedException e){
            if(hardware_on) {
                Keyboard.shutdown();
            }
            System.out.print(e.getMessage());
        }

        runThroughEmotions();

        Keyboard.shutdown();
        Mouse.shutdown();
        Headset.shutdown();

    }

    private static void setPeripheralsColor(Colour c) {
        Keyboard.setColourSmoothly(c, delay);
        Mouse.setColourSmoothly(c, delay);
        Headset.setColourSmoothly(c, delay);
    }

    private static void runThroughEmotions(){
        for (EmotionEnum e : EmotionEnum.values()){
            setPeripheralsColor(Colours.singleEmotion(e));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
