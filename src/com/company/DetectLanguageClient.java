package com.company;

import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Scanner;

public class DetectLanguageClient {

    private static HashMap<String, String> languages = new HashMap<>();


    private void getLanguagesValues() {
        Unirest.config().setObjectMapper(new JacksonObjectMapper());

        JSONArray jsonArray = Unirest.get("https://ws.detectlanguage.com/0.2/languages")
                .asJson()
                .getBody()
                .getArray();


        jsonArray.forEach(o -> {
            JSONObject jsonObject = (JSONObject) o;
            languages.put(jsonObject.getString("code"), jsonObject.getString("name"));
        });
    }

    public void detectLanguageProgramm() {
        if (languages.isEmpty()) {
            getLanguagesValues();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите текст:");
        String string = sc.nextLine();
        System.out.println("Язык введенного текста:" + detectLanguage(string));

    }

    private String detectLanguage(String string) {
        String language = Unirest.post("https://ws.detectlanguage.com/0.2/detect")
                .header("Authorization", "Bearer ef07271881c72360dddb2f307f1b0c23")
                .queryString("q", string)
                .asJson()
                .getBody()
                .getObject()
                .getJSONObject("data")
                .getJSONArray("detections")
                .getJSONObject(0)
                .getString("language");

        return languages.get(language);
    }
}
