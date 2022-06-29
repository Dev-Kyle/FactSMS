package com.kyleli.factsms;


import com.google.common.io.Resources;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        JSONObject config;
        try {
            String configStr = Resources.toString(
                    Objects.requireNonNull(Main.class.getResource("/config.json")),
                    StandardCharsets.UTF_8
            );
            config = new JSONObject(configStr);
        } catch (IOException e) {
            System.out.println("Could not find config file.");
            System.exit(1);
            return;
        }

        FactGenerator gen = new FactGenerator(FactGenerator.LANG.EN);
        SMSManager sms = new SMSManager();

        try {
            String fact = gen.getFact();
            JSONArray numbers = config.getJSONArray("numbers");
            for (Object number : numbers) {
                sms.sendText(fact, (String) number);
            }
        } catch (Exception e) {
            System.out.println("Failed to get fact: " + e.getMessage());
            System.exit(1);
        }
    }
}
