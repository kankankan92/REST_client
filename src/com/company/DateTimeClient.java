package com.company;

import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;

public class DateTimeClient {

    public void detTime() {
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
        Date date = Unirest.get("http://date.jsontest.com/")
                .asObject(Date.class)
                .getBody();
        System.out.println(date.getDate());
        System.out.println(date.getTime());
    }
}
