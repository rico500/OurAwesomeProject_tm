package com.awesome.hardware;
import com.awesome.cognitive.Colour;
import com.logitech.gaming.LogiLED;


/**
 * Created by ramon on 19/11/16.
 */
public class Keyboard extends Peripheral{

    private static final int ROWS = 6, COLS = 21, NB_OF_BYTES_PER_KEY = 4;

    public static void setUpperRow(int r, int g, int b, int a){
        byte[] RGBAarray = new byte[NB_OF_BYTES_PER_KEY*COLS*ROWS];
        for (int i = 0; i < NB_OF_BYTES_PER_KEY*COLS*ROWS; i++) {
            if (i < NB_OF_BYTES_PER_KEY*20) {
                if (i%NB_OF_BYTES_PER_KEY==0)
                    RGBAarray[i] = (byte) b;
                else if(i%NB_OF_BYTES_PER_KEY==1)
                    RGBAarray[i] = (byte) g;
                else if(i%NB_OF_BYTES_PER_KEY==2)
                    RGBAarray[i] = (byte) r;
                else if(i%NB_OF_BYTES_PER_KEY==3)
                    RGBAarray[i] = (byte) a;
            } else {
                RGBAarray[i] = 0x00 & 0x00 & 0x00 & 0x00;
            }
        }
        LogiLED.LogiLedSetLightingFromBitmap(RGBAarray);
    }

    public static void setCyclingColors(int time, Colour... colors){
        for (Colour c : colors){

        }
    }

    public static void setDrawing(byte[][] drawing, Colour c0, Colour c1){
        if (drawing.length != COLS && drawing[0].length != ROWS){
            throw new IllegalArgumentException("Wrong drawing dimensions !!!");
        }
        byte[] RGBAarray = new byte[NB_OF_BYTES_PER_KEY*COLS*ROWS];
        int i = 0;
        for (int row = 0; row<drawing.length; row++){
            for (int col = 0; col<drawing[row].length; col++){

                Colour c;
                if (drawing[row][col] == 0){
                    c = c0;
                } else {
                    c = c1;
                }

                RGBAarray[i] = (byte) c.b;
                RGBAarray[i+1] = (byte) c.g;
                RGBAarray[i+2] = (byte) c.r;
                RGBAarray[i+3] = (byte) c.a;

                i += 4;

            }
        }

        LogiLED.LogiLedSetLightingFromBitmap(RGBAarray);
    }
}
