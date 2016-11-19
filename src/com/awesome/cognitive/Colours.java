package com.awesome.cognitive;

import org.json.*;
/**
 * Created by ignat on 20/11/2016.
 */
public class Colours {

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
     * @return EmotionEnum corresponding to the dominant emotion of first face in picture.
     */
    public static EmotionEnum singleConvert(String jsonString){
        JSONObject js = new JSONObject(jsonString);
        double a, c, d, f, h, n, sa, su;
        a = js.getJSONObject("scores").getDouble("anger");
        EmotionEnum e = EmotionEnum.ANGER;
        double t = a;
        c = js.getJSONObject("scores").getDouble("contempt");
        e = (a < c)? EmotionEnum.CONTEMPT : e;
        d = js.getJSONObject("scores").getDouble("disgust");
        e = (c < d)? EmotionEnum.DISGUST : e;
        f = js.getJSONObject("scores").getDouble("fear");
        e = (d < f)? EmotionEnum.FEAR : e;
        h = js.getJSONObject("scores").getDouble("happiness");
        e = (f < h)? EmotionEnum.HAPPINESS : e;
        n = js.getJSONObject("scores").getDouble("neutral");
        e = (h < n)? EmotionEnum.NEUTRAL : e;
        sa = js.getJSONObject("scores").getDouble("sadness");
        e = (n < sa)? EmotionEnum.SADNESS : e;
        su = js.getJSONObject("scores").getDouble("surprise");
        e = (sa < su)? EmotionEnum.SURPRISE : e;

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
                c = new Colour(255, 255, 0);
                break;
            case DISGUST:
                c = new Colour(0, 255, 0);
                break;
            case FEAR:
                c = new Colour(0, 255, 255);
                break;
            case HAPPINESS:
                c = new Colour(255/2, 255/2, 255/2);
                break;
            case NEUTRAL:
                c = new Colour(0, 0, 0);
                break;
            case SADNESS:
                c = new Colour(0, 0, 255);
                break;
            case SURPRISE:
                c = new Colour(255, 0, 255);
                break;
            default:
                c = new Colour(0,0,0,0);
                break;
        }
        return c;
    }


}
