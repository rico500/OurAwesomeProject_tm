package com.awesome.hardware;
import com.logitech.gaming.LogiLED;

/**
 * Created by ramon on 19/11/16.
 */
public class Keyboard {

    public static void initKeyboard()
    {
        LogiLED.LogiLedInit();
        try{
            Thread.sleep(500);
        } catch(Exception e){
        }
    }

    public static void shutdownKeyboard()
    {
        LogiLED.LogiLedShutdown();
    }

    public static void setColor(int r, int g, int b){
        LogiLED.LogiLedSetLighting((r/255)*100, (g/255)*100,(b/255)*100);
    }
}
