package com.example.naveenkumar.b9_weather.tasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.naveenkumar.b9_weather.interfaces.TaskCalback;
import com.example.naveenkumar.b9_weather.parser.Info;
import com.example.naveenkumar.b9_weather.parser.ParserUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Naveenkumar on 11/6/2015.
 */
public class DataTask extends AsyncTask<String, Void,Info> {
    private final static  String TAG ="DataTask";

    private TaskCalback taskCalback;
    public DataTask(TaskCalback taskCalback){
        this.taskCalback =taskCalback;

    }


    @Override
    protected Info doInBackground(String... params) {

        String link = params[0];


        try {
            URL url =new URL(link);

            HttpURLConnection conn =(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(1500);

            conn.setRequestMethod("GET");
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader =new BufferedReader( new InputStreamReader(is,"UTF-8"));

            String data= null;
            String rawJson ="";

            while ((data =reader.readLine())!=null){
                rawJson +=data+"\n";
            }

            Log.i(TAG, "rawJson" + rawJson);

            Info info = ParserUtil.getParsedData(rawJson);
            return info;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Info info){
        taskCalback.getData(info);

    }
}
