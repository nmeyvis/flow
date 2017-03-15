package com.rfgbot.anywhereparse.addon.translate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by nickm on 3/14/2017.
 */
public class GoogleTranslate {
    private static Gson gson = new Gson();

    private String source;
    private String target;

    public GoogleTranslate(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String get(String text) throws IOException {
        try {
            URIBuilder uriBuilder = new URIBuilder("https://translate.googleapis.com/translate_a/single");
            uriBuilder
                    .setParameter("client", "gtx")
                    .setParameter("sl", source)
                    .setParameter("tl", target)
                    .setParameter("dt", "t")
                    .setParameter("q", text);

            Response resp = Request.Get(uriBuilder.build()).execute();
            HttpResponse httpResponse = resp.returnResponse();
            String result = EntityUtils.toString(httpResponse.getEntity());

            JsonArray json = gson.fromJson(result, JsonArray.class);
            JsonArray a1 = (JsonArray) json.get(0);
            JsonArray a2 = (JsonArray) a1.get(0);
            return a2.get(0).getAsString();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
