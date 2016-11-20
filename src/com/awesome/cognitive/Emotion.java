package com.awesome.cognitive;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.io.File;
import java.util.concurrent.Future;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;


/**
 * Created by ebrunner on 19/11/16.
 */
public class Emotion {

    /**
     * getEmotion
     *
     * Get intent in image stored in byte arrray.
     * See https://dev.projectoxford.ai/docs/services/5639d931ca73072154c1ce89/operations/563b31ea778daf121cc3a5fa
     *
     * @param byte[] imageByteArray image as an array of bytes (can't exceed 4Mb or )
     *
     * @return String image intent json as a string
     */


    public static String getEmotionJSON(byte[] imageByteArray){
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "d9b29a6ab43649d0af4d99c0ba59e8e3");


            // Request body

            ByteArrayEntity reqEntity = new ByteArrayEntity(imageByteArray,
                    ContentType.create("application/octet-stream"));

            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String jsonOutput = EntityUtils.toString(entity);
                //System.out.println(jsonOutput);
                return jsonOutput;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * getEmotion
     *
     * test function that uses test.jpg to see if the service is working and byte conversion is ok.
     */
    public static void getEmotionJSON(){
        Path imagepath = Paths.get(System.getProperty("user.dir").concat("/test.jpg"));
        File imagefile = new File(imagepath.toString());

        System.out.println("Found Image at: " + imagepath.toString());

        try {
            System.out.println(Files.readAllBytes(imagefile.toPath()));
            getEmotionJSON(Files.readAllBytes(imagefile.toPath()));
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
