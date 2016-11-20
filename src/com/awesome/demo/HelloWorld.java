package com.awesome.demo;

import com.awesome.cognitive.Colour;
import com.awesome.cognitive.Colours;
import com.awesome.cognitive.Emotion;
import com.awesome.cognitive.EmotionEnum;
import com.awesome.hardware.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    private static final long delay = 100;
    private static final boolean hardware_on = false;

    @Override
    public void start(Stage primaryStage) {

        Button btnWebCam = new Button();
        btnWebCam.setText("Run webcam emotion analysis");
        btnWebCam.setOnAction(event ->  {
            testEmotionRecognition();
        });

        Button runThroughEmotions = new Button();
        runThroughEmotions.setText("Run through emotions");
        runThroughEmotions.setOnAction(event -> {
            runThroughEmotions();
        });

        Button testBanner = new Button();
        testBanner.setText("Run arrow banner");
        testBanner.setOnAction(event -> {
            testBanner(KeyboardPresets.ARROW_RIGHT);
        });

        Button trailingKeys = new Button();
        trailingKeys.setText("Run trailing keys");
        trailingKeys.setOnAction(event -> {

        });

        VBox root = new VBox(16);
        root.getChildren().addAll(btnWebCam, runThroughEmotions, testBanner, trailingKeys);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Demo !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


    private static void testBanner(byte[][] drawing) {
        Keyboard.bannerFullSpinRight(drawing, Colours.singleEmotion(EmotionEnum.ANGER), Colours.singleEmotion(EmotionEnum.FEAR), 20000);
    }

    private static void testEmotionRecognition() {
        WebcamSupport.showWebCamLiveFeed();

        WebcamSupport.init();

        if(hardware_on) {
            Keyboard.init();
            Mouse.init();
            Headset.init();
        }

        try {

            while (true) {
                Thread.sleep(delay);
                String s = Emotion.getEmotionJSON(WebcamSupport.getSnapshot());
                s = s.substring(1, s.length() - 1);
                System.out.println(s);

                if (!s.equalsIgnoreCase("")) {

                    try {
                        Colour c = Colours.multiConvert(s);
                        System.out.println(c.toString());

                        if(hardware_on) {
                            Keyboard.setColor(c);
                            Mouse.setColor(c);
                            Headset.setColourSmoothly(c, delay);
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
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}