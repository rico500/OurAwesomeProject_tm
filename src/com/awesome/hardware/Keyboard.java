package com.awesome.hardware;
import com.awesome.cognitive.Colour;
import com.logitech.gaming.LogiLED;


/**
 * Created by ramon on 19/11/16.
 */
public class Keyboard extends Peripheral{

    private static final int ROWS = 6, COLS = 21, NB_OF_BYTES_PER_KEY = 4, CENTER_KEYS = 14;

    public static void setUpperRow(int r, int g, int b, int a){
        setDrawing(KeyboardPresets.UPPER_ROW, Colours.singleEmotion(EmotionEnum.ANGER), Colours.singleEmotion(EmotionEnum.DISGUST));
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

    private static byte[][] offsetDrawingHorizontaly(byte[][] drawing, int offset){
        byte[][] array = new byte[drawing.length][drawing[0].length];
        for (int r = 0; r<drawing.length; ++r){
            for(int c = 0; c<drawing[c].length; ++c){
                array[r][(c+offset) % CENTER_KEYS] = drawing[r][c];
            }
        }

        return array;
    }

    public static void bannerFullSpinRight(byte[][] drawing, Colour c0, Colour c1, long duration){

        duration = duration/CENTER_KEYS;

        for (int i = 0; i<CENTER_KEYS; ++i){
            setDrawing(offsetDrawingHorizontaly(drawing, i), c0, c1);
            sleep(duration);
        }

    }

    private static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
