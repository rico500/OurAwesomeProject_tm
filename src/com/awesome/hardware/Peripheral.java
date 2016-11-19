package com.awesome.hardware;

import com.logitech.gaming.LogiLED;

/**
 * Created by Danil on 19.11.16.
 */
public abstract class Peripheral {

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
        LogiLED.LogiLedSetLighting((r/255)*100, (g/255)*100,(b/255)*100);
    }
}
