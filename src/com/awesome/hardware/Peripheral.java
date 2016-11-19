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
        prevColour = new Colour(r, g, b, 255);
        double red = (double)r/255.0;
        double green = (double)g/255.0;
        double blue = (double)b/255.0;
        System.out.println(red + " " + green + " " + blue);
        LogiLED.LogiLedSetLighting((int) (red*100),(int) (green*100),(int) (blue*100));
        //LogiLED.LogiLedPulseLighting((r/255)*100, (g/255)*100,(b/255)*100, 2000, transitionMilliSecondsInterval);
    }

    public static void setColourSmoothly(Colour c, long dMillis){

        int dR = computeStep(prevColour.r, c.r, (int) dMillis);
        int dG = computeStep(prevColour.g, c.g, (int) dMillis);
        int dB = computeStep(prevColour.b, c.b, (int) dMillis);
        int dA = computeStep(prevColour.a, c.a, (int) dMillis);

        while (prevColour.r <= c.r || prevColour.r <= c.r || prevColour.r <= c.r){
            if (prevColour.r <= c.r){
                prevColour.r += dR;
            }

            if (prevColour.g <= c.g){
                prevColour.g += dG;
            }

            if (prevColour.b <= c.b){
                prevColour.b += dB;
            }
        }


    }

    private static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int computeStep(int from, int to, int totSteps){
        return (to-from)/totSteps;
    }

    public static void setColor(Colour c){
        prevColour = new Colour(c);
        setColor(c.r, c.g, c.b);
    }
}
