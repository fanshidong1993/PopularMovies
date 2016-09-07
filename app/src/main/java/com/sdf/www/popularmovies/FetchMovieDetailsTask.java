package com.sdf.www.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shadow on 16/9/8.
 */
public class FetchMovieDetailsTask extends AsyncTask<Integer,Void,String> {
    private DetailActivityInterface detailActivityInterface;
    public FetchMovieDetailsTask(DetailActivityInterface detailActivityInterface){
        this.detailActivityInterface = detailActivityInterface;
    }
    @Override
    protected String doInBackground(Integer... integers) {
        Uri builtUri = Uri.parse(Constant.HTTP_BASE_URL+"/"+integers[0]).buildUpon()
                .appendQueryParameter(Constant.API_KEY_PARAM,Constant.API_KEY).build();
        return Utils.getHttpResult(builtUri.toString());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            detailActivityInterface.setJsonData(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
