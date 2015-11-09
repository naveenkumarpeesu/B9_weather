package com.example.naveenkumar.b9_weather.parser;

import com.google.gson.Gson;

/**
 * Created by Naveenkumar on 11/6/2015.
 */
public class ParserUtil {

    public  static  Info getParsedData(String rawJson){
        Gson gson =new Gson();
        Info info =gson.fromJson(rawJson,Info.class);

        return info;

    }
}
