package com.awesome;
import com.awesome.cognitive.Colours;
import com.awesome.cognitive.Emotion;
import com.awesome.cognitive.EmotionEnum;
import com.awesome.cognitive.Colour;
import com.awesome.gui.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread;

import com.awesome.hardware.*;

import javax.swing.*;

public class Main {

    private static final boolean hardware_on = true;
    private static final long delay = 2000;

    public static void main(String[] args) {

        if(hardware_on) {
            Keyboard.init();
            Mouse.init();
            Headset.init();
        }

        demoTypingTrails();
        demoEmotions();
    }

    private static void shutdownHardware(){
        Keyboard.shutdown();
        Mouse.shutdown();
        Headset.shutdown();
    }

    private static void demoEmotions(){
        //GUI gui = new GUI(WebcamSupport.init());
        WebcamSupport.init();
        WebcamSupport.showWebCamLiveFeed();

        testEmotionRecognition();

        //runThroughEmotions(); //Loop through the different emotions

        //Keyboard.setDrawing(KeyboardPresets.ARROW_RIGHT, Colours.singleEmotion(EmotionEnum.ANGER), Colours.singleEmotion(EmotionEnum.FEAR));
        //testBanner(KeyboardPresets.ARROW_RIGHT);
    }

    private static void demoTypingTrails(){
        JFrame f=new JFrame();
        f.addKeyListener(new MyKeyListener());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private static void testBanner(byte[][] drawing) {
        Keyboard.bannerFullSpinRight(drawing, Colours.singleEmotion(EmotionEnum.ANGER), Colours.singleEmotion(EmotionEnum.FEAR), 20000);
    }

    private static void testEmotionRecognition() {
        try {

            while (true) {
                Thread.sleep(0);
                    String s = Emotion.getEmotionJSON(WebcamSupport.getSnapshot());
                    s = s.substring(1, s.length() - 1);
                    System.out.println(s);

                    if (!s.equalsIgnoreCase("")) {

                        try {

                            //Colour c = Colours.generateColour(s);
                            Colour c = Colours.historyMeanColour(s);
                            System.out.println(c.toString());

                            if(hardware_on) {
                                if(Colours.isSuddenChange()){
                                    Keyboard.setColor(Colours.getLastColor());
                                }else {
                                    Keyboard.setColor(c);
                                    Mouse.setColor(c);
                                    Headset.setColourSmoothly(c, delay);
                                }
                            }
                        }catch(org.json.JSONException e){
                            System.err.println("Parsing error! JSON : " + s);
                        }

                    }


                    //Keyboard.setColor(0, 255, 0);
            }

        }
        catch(InterruptedException e){
            if(hardware_on) {
                Keyboard.shutdown();
            }
            System.out.print(e.getMessage());
        }
    }

    private static void setPeripheralsColor(Colour c) {
        Keyboard.setColourSmoothly(c, delay);
        Mouse.setColourSmoothly(c, delay);
        Headset.setColourSmoothly(c, delay);
    }

    private static void runThroughEmotions(){
        for (EmotionEnum e : EmotionEnum.values()){
            setPeripheralsColor(Colours.singleEmotion(e));
            try {
                Thread.sleep(0);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
