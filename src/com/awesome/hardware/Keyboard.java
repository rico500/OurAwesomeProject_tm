package com.awesome.hardware;
import com.logitech.gaming.LogiLED;

/**
 * Created by ramon on 19/11/16.
 */
public class Keyboard {

    public void setColor(int r, int g, int b){
        LogiLED.LogiLedInit();
        LogiLED.LogiLedSetLighting(r, g, b);
        LogiLED.LogiLedShutdown();
    }
}
