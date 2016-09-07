package com.sdf.www.popularmovies;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shadow on 16/9/7.
 */
public class Utils {
    public static String getHttpResult(String urlString){
        String result = null;
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if(inputStream == null) return null;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine())!=null){
                stringBuffer.append(line + "\n");
            }
            if(stringBuffer.length() == 0){
                return null;
            }
            result = stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        return result;
    }
}
