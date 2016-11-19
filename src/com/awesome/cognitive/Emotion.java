package com.awesome.cognitive;

import java.net.URI;
import java.nio.file.*;
import java.io.File;

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

    public static void getEmotion(byte[] imageByteArray){

        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "b2e7ad2e399f4afb9be3096d50a98a93");


            // Request body
            ByteArrayEntity reqEntity = new ByteArrayEntity(imageByteArray,
                    ContentType.create("application/octet-stream"));

            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getEmotion(){

        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "b2e7ad2e399f4afb9be3096d50a98a93");


            // Request body

            // File Entity test
            Path imagepath = Paths.get(System.getProperty("user.dir").concat("/test.jpg"));
            File imagefile = new File(imagepath.toString());

            System.out.println("Found Image at: " + imagepath.toString());

            System.out.println(Files.readAllBytes(imagefile.toPath()));
            ByteArrayEntity reqEntity = new ByteArrayEntity(Files.readAllBytes(imagefile.toPath()),
                    ContentType.create("application/octet-stream"));

            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
