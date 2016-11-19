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
        //System.out.println(red + " " + green + " " + blue);
        LogiLED.LogiLedSetLighting((int) (red*100),(int) (green*100),(int) (blue*100));
        //LogiLED.LogiLedPulseLighting((r/255)*100, (g/255)*100,(b/255)*100, 2000, transitionMilliSecondsInterval);
    }

    public static void setColourSmoothly(Colour c, long dMillis){

        double dR = computeStep(prevColour.r, c.r, (int) dMillis);
        double dG = computeStep(prevColour.g, c.g, (int) dMillis);
        double dB = computeStep(prevColour.b, c.b, (int) dMillis);
        double dA = computeStep(prevColour.a, c.a, (int) dMillis);
        long slR = (long) ((Math.abs(dR) / (Math.abs(dR)+Math.abs(dG)+Math.abs(dB)))*dMillis);
        long slG = (long) ((Math.abs(dG) / (Math.abs(dR)+Math.abs(dG)+Math.abs(dB)))*dMillis);
        long slB = (long) ((Math.abs(dB) / (Math.abs(dR)+Math.abs(dG)+Math.abs(dB)))*dMillis);


        System.out.println(prevColour.r + " " + c.r + " " + dR);
        System.out.println(prevColour.g + " " + c.g + " " + dG);
        System.out.println(prevColour.b + " " + c.b + " " + dB);
        System.out.println(prevColour.a + " " + c.a + " " + dA);

        if (dR > 0.0){ // We want to go up
            while(prevColour.r < c.r) {
                prevColour.r += dR;
                sleep(slR);
                setColor(prevColour);
            }
        }
        else if (dR < 0.0) // We want to go down
        {
            while(prevColour.r > c.r) {
                prevColour.r += dR;
                sleep(slR);
                setColor(prevColour);
            }
        }

        if (dG > 0.0){ // We want to go up
            while(prevColour.g < c.g) {
                prevColour.g += dG;
                sleep(slG);
                setColor(prevColour);
            }
        }
        else if (dG < 0.0) // We want to go down
        {
            while(prevColour.g > c.g) {
                prevColour.g += dG;
                sleep(slG);
                setColor(prevColour);
            }
        }

        if (dB > 0.0){ // We want to go up
            while(prevColour.b < c.b) {
                prevColour.b += dB;
                sleep(slB);
                setColor(prevColour);
            }
        }
        else if (dB < 0.0) // We want to go down
        {
            while(prevColour.b > c.b) {
                prevColour.b += dB;
                sleep(slB);
                setColor(prevColour);
            }
        }

        if (dA > 0.0){ // We want to go up
            while(prevColour.a < c.a){
                prevColour.a += dA;
                setColor(prevColour);
            }
        }
        else if (dA < 0.0) // We want to go down
        {
            while(prevColour.a > c.a) {
                prevColour.a += dA;
                setColor(prevColour);
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

    private static double computeStep(double from, double to, double totSteps){

        if ((to-from)/totSteps < 1.0 && (to-from)/totSteps > 0.0) return 1.0;
        else if ((to-from)/totSteps > -1.0 && (to-from)/totSteps < 0.0) return -1.0;
        else return (to-from)/totSteps;
    }

    public static void setColor(Colour c){
        prevColour = new Colour(c);
        setColor(c.r, c.g, c.b);
    }
}
