package com.sdf.www.popularmovies;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow on 16/9/7.
 */
public class FetchPopMoviesImgTask extends AsyncTask<Void,Void,Pair<String,Integer>[]> {

    private MainActivityInterface mainActivityInterface;

    public FetchPopMoviesImgTask(MainActivityInterface mainActivityInterface){
        this.mainActivityInterface = mainActivityInterface;
    }
    @Override
    protected Pair<String,Integer>[] doInBackground(Void... voids) {
        List<Pair<String,Integer>> result = new ArrayList<>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivityInterface.getContext());
        String sortWay = sharedPreferences.getString(mainActivityInterface.getContext().getString(R.string.pref_way_key)
                ,mainActivityInterface.getContext().getString(R.string.pref_way));
        String baseUrl;
        if(sortWay.equals(mainActivityInterface.getContext().getString(R.string.pref_way)))
            baseUrl = Constant.HTTP_POPULAR_URL;
        else
            baseUrl = Constant.HTTP_TOP_RATE_URL;
        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(Constant.API_KEY_PARAM,Constant.API_KEY).build();

        String JSONString = Utils.getHttpResult(builtUri.toString());

        if(JSONString == null) return null;

        try {
            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray results = jsonObject.getJSONArray(Constant.JSONARRAY_NAME);
            for(int i = 0 ; i < results.length(); ++ i ){
                JSONObject object = results.getJSONObject(i);
                String first = Constant.IMG_SIZE_W185 + object.getString(Constant.JSONOBJECT_IMGPATH_NAME);
                Integer second = object.getInt(Constant.JSONOBJECT_ID);
                result.add(new Pair<String, Integer>(first,second));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Pair<String,Integer>[] res = new Pair[result.size()];
        for(int i = 0 ; i < res.length ; ++ i ){
            res[i] = result.get(i);
        }
        return res;
    }

    @Override
    protected void onPostExecute(Pair<String,Integer>[] strings) {
        super.onPostExecute(strings);

        mainActivityInterface.setImgsUrl(strings);
    }
}
