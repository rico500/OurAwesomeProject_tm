package com.awesome.cognitive;

import org.json.*;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Deque;

/**
 * Created by ignat on 20/11/2016.
 */
public class Colours {



    private static int emotionQueueLength = 10;
    private static boolean detectSuddenEmotions = true;
    private static int[] emotionCounter = {0, 0, 0, 0, 0, 0, 0, 0};
    private static ArrayDeque<EmotionEnum> emotionQueue = new ArrayDeque<>();
    private static int emotionQueueCounter = 0;
    private static EmotionEnum prevEmotion;

    public static void setEmotionQueueLength(int value){
        emotionQueueLength = value;
    }

    public static int getEmotionQueueLength(){
        return emotionQueueLength;
    }

    public static void setDetectSuddenEmotions(boolean value){
        detectSuddenEmotions = value;
    }

    public static boolean getDetectSuddenEmotions(){
        return detectSuddenEmotions;
    }

    /**
     *
     * @param json String formatted as valid JSON containing emotion data from Cognitive Emotion API.
     * @return Colour object containing interpreted colour.
     */
    public static Colour generateColour(String json){
        return singleEmotion(singleConvert(json));
    }

    /**
     *
     * @param jsonString String formatted as valid JSON containing emotion data from Cognitive Emotion API.
     * @return Average colour as per the singleEmotion method, with individual colour influences scaled proportionally \
     * to their individual scales
     */
    public static Colour multiConvert(String jsonString){
        JSONObject js = new JSONObject(jsonString);
        Colour[] cs = new Colour[8];

        cs[0] = singleEmotion(EmotionEnum.ANGER);
        cs[0].scale = js.getJSONObject("scores").getDouble("anger");
        cs[1] = singleEmotion(EmotionEnum.CONTEMPT);
        cs[1].scale = js.getJSONObject("scores").getDouble("contempt");
        cs[2] = singleEmotion(EmotionEnum.DISGUST);
        cs[2].scale = js.getJSONObject("scores").getDouble("disgust");
        cs[3] = singleEmotion(EmotionEnum.FEAR);
        cs[3].scale = js.getJSONObject("scores").getDouble("fear");
        cs[4] = singleEmotion(EmotionEnum.HAPPINESS);
        cs[4].scale = js.getJSONObject("scores").getDouble("happiness");
        cs[5] = singleEmotion(EmotionEnum.NEUTRAL);
        cs[5].scale = js.getJSONObject("scores").getDouble("neutral");
        cs[6] = singleEmotion(EmotionEnum.SADNESS);
        cs[6].scale = js.getJSONObject("scores").getDouble("sadness");
        cs[7] = singleEmotion(EmotionEnum.SURPRISE);
        cs[7].scale = js.getJSONObject("scores").getDouble("surprise");

        return Colour.scaleAdd(cs);
    }

    /**
     * @param jsonString restful api response from cognitive services in String format
     * @return a Colour Object
     *
     * determine colour based on historically most dominant colour
     */

    public static Colour historyDominantColour(String jsonString){

        // Declare variables
        EmotionEnum latestEmotion = singleConvert(jsonString);
        int dominantEmotionIndex = 0;
        updateEmotionCounter(latestEmotion);

        for(int i=0; i < emotionCounter.length; i++){
            if(emotionCounter[i] > emotionCounter[dominantEmotionIndex]){
                dominantEmotionIndex = i;
            }
        }

        // print all emotions in Deque
        for(EmotionEnum e : emotionQueue){
            System.out.print(e.name() + " ");
        }
        System.out.println();

        return singleEmotion(EmotionEnum.values()[dominantEmotionIndex]);

    }


    /**
     * @param jsonString restful api response from cognitive services in String format
     * @return a Colour Object
     *
     * determine colour based on content of history
     */
    public static Colour historyMeanColour(String jsonString){
        // Declare variables
        EmotionEnum latestEmotion = singleConvert(jsonString);
        updateEmotionCounter(latestEmotion);

        Colour meanColour = new Colour(0,0,0);
        for(EmotionEnum e : emotionQueue) {
            meanColour.add(singleEmotion(e));
        }
        meanColour.divide(emotionQueueLength);

        return meanColour;
    }

    public static boolean isSuddenChange(){
        if(detectSuddenEmotions) {
            if (emotionQueue.peekLast() != prevEmotion) {
                System.out.println("Sudden Change in Emotion detected!");
                prevEmotion = emotionQueue.peekLast();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static Colour getLastColor(){
        return singleEmotion(emotionQueue.peekLast());
    }

    /**
     *
     * @param jsonString String formatted as valid JSON containing emotion data from Cognitive Emotion API.
     * @return EmotionEnum corresponding to the dominant emotion of first face in picture.
     */
    public static EmotionEnum singleConvert(String jsonString){
        JSONObject js = new JSONObject(jsonString);
        double a, c;
        a = js.getJSONObject("scores").getDouble("anger");
        EmotionEnum e = EmotionEnum.ANGER;
        double t = a;
        c = js.getJSONObject("scores").getDouble("contempt");
        e = (a < c)? EmotionEnum.CONTEMPT : e;
        a = (a < c)? c : a;
        c = js.getJSONObject("scores").getDouble("disgust");
        e = (a < c)? EmotionEnum.DISGUST : e;
        a = (a < c)? c : a;
        c = js.getJSONObject("scores").getDouble("fear");
        e = (a < c)? EmotionEnum.FEAR : e;
        a = (a < c)? c : a;
        c = js.getJSONObject("scores").getDouble("happiness");
        e = (a < c)? EmotionEnum.HAPPINESS : e;
        a = (a < c)? c : a;
        c = js.getJSONObject("scores").getDouble("neutral");
        e = (a < c)? EmotionEnum.NEUTRAL : e;
        a = (a < c)? c : a;
        c = js.getJSONObject("scores").getDouble("sadness");
        e = (a < c)? EmotionEnum.SADNESS : e;
        a = (a < c)? c : a;
        c = js.getJSONObject("scores").getDouble("surprise");
        e = (a < c)? EmotionEnum.SURPRISE : e;

        return e;
    }

    /**
     *
     * @param e EmotionEnum representing emotion to be represented.
     * @return Colour associated with emotion.
     */
    public static Colour singleEmotion(EmotionEnum e){
        Colour c;
        switch (e){

            case ANGER:
                c = new Colour(255, 0, 0);
                break;
            case CONTEMPT:
                c = new Colour(216, 0, 116);
                break;
            case DISGUST:
                c = new Colour(136, 0, 177);
                break;
            case FEAR:
                c = new Colour(253, 241, 71);
                break;
            case HAPPINESS:
                c = new Colour(0, 210, 72);
                break;
            case NEUTRAL:
                c = new Colour(0, 0, 0);
                break;
            case SADNESS:
                c = new Colour(0, 69, 147);
                break;
            case SURPRISE:
                c = new Colour(236, 180, 0);
                break;
            default:
                c = new Colour(0,0,0,0);
                break;
        }
        return c;
    }

    private static void updateEmotionCounter(EmotionEnum latestEmotion){
        if(emotionQueueCounter < emotionQueueLength-1) {
            emotionQueue.add(latestEmotion);
            emotionCounter[latestEmotion.ordinal()]++;
            emotionQueueCounter++;
        }else{
            emotionCounter[emotionQueue.remove().ordinal()]--;
            emotionQueue.add(latestEmotion);
            emotionCounter[latestEmotion.ordinal()]++;
        }
    }

}

