package com.kyleli.factsms;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class FactGenerator {
    public enum LANG { EN, DE };
    private final HttpClient httpClient;
    private String url;

    public FactGenerator(LANG lang) {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        url = "https://uselessfacts.jsph.pl/random.json?language=";
        switch (lang) {
            case EN:
                url += "en";
                break;
            case DE:
                url += "de";
                break;
        }
    }

    public String getFact() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "SMS Fact Bot")
                .build();
        HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            throw new Exception("Bad response, status code: " + resp.statusCode());
        }

        JSONObject respJson = new JSONObject(resp.body());
        return respJson.getString("text");
    }
}

