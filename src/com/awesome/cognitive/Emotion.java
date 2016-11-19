package com.awesome.cognitive;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

/**
 * Created by ebrunner on 19/11/16.
 */
public class Emotion {

    public static void getEmotion(){

        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "b2e7ad2e399f4afb9be3096d50a98a93");


            // Request body

            // String Entity test
            StringEntity reqEntity = new StringEntity("{ \"url\": \"http://pngimg.com/upload/face_PNG11761.png\" }");

            // File Entity test
            File file = new File("");
            FileEntity entity = new FileEntity(file,
                    ContentType.create("text/plain", "UTF-8"));

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
