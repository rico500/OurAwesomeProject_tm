package com.awesome.hardware;

import com.awesome.cognitive.Colour;
import com.logitech.gaming.LogiLED;

/**
 * Created by Danil on 19.11.16.
 */
public abstract class Peripheral {

    private static final int transitionMilliSecondsInterval = 1000;


    public static void init() {
        LogiLED.LogiLedInit();
        try{
            Thread.sleep(500);
        } catch(Exception e){
        }
        LogiLED.LogiLedSaveCurrentLighting();
        setColor(0,0,0);
    }

    public static void shutdown() {
        LogiLED.LogiLedShutdown();
    }

    public static void setColor(int r, int g, int b) {
        LogiLED.LogiLedPulseLighting((r/255)*100, (g/255)*100,(b/255)*100, LogiLED.LOGI_LED_DURATION_INFINITE, transitionMilliSecondsInterval);
    }

    public static void setColor(Colour c){
        setColor(c.r, c.g, c.b);
    }
}
