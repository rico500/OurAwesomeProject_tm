package com.awesome.hardware;

import com.awesome.cognitive.Colour;
import com.logitech.gaming.LogiLED;

/**
 * Created by Danil on 19.11.16.
 */
public abstract class Peripheral {

    private static Colour prevColour;

    public static void init() {
        LogiLED.LogiLedInit();
        try{
            Thread.sleep(500);
        } catch(Exception e){
        }
        LogiLED.LogiLedSaveCurrentLighting();
        prevColour = new Colour(0, 0, 0, 0);
        setColor(prevColour);
    }

    public static void shutdown() {
        LogiLED.LogiLedShutdown();
    }

    public static void setColor(int r, int g, int b) {
        double red = (double)r/255.0;
        double green = (double)g/255.0;
        double blue = (double)b/255.0;
        System.out.println(red + " " + green + " " + blue);
        LogiLED.LogiLedSetLighting((int) (red*100),(int) (green*100),(int) (blue*100));
        //LogiLED.LogiLedPulseLighting((r/255)*100, (g/255)*100,(b/255)*100, 2000, transitionMilliSecondsInterval);
    }

    public static void setColor(Colour c){
        setColor(c.r, c.g, c.b);
    }
}
