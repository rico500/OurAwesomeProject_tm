package com.awesome.hardware;
import com.logitech.gaming.LogiLED;

/**
 * Created by ramon on 19/11/16.
 */
public class Keyboard extends Peripheral{

    public static void setUpperRow(int r, int g, int b, int a){
        byte[] RGBAarray = new byte[4*21*6];
        for (int i = 0; i < 4*21*6; i++) {
            if (i < 4*20) {
                if (i%4==0)
                    RGBAarray[i] = (byte) b;
                else if(i%4==1)
                    RGBAarray[i] = (byte) g;
                else if(i%4==2)
                    RGBAarray[i] = (byte) r;
                else if(i%4==3)
                    RGBAarray[i] = (byte) a;
            } else {
                RGBAarray[i] = 0x00 & 0x00 & 0x00 & 0x00;
            }
        }
        LogiLED.LogiLedSetLightingFromBitmap(RGBAarray);
    }
}
