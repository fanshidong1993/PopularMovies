package com.sdf.www.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        /*TextView tv = (TextView) findViewById(R.id.detial_id);
        Intent intent = getIntent();
        tv.setText(""+intent.getIntExtra(Constant.JSONOBJECT_ID,0));*/
        setTitle("Movie Detail");

        init();
    }
    private int id;
    private void init() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.JSONOBJECT_ID,0);
        if (id != 0 ) {
            FetchMovieDetailsTask fetchMovieDetailsTask = new FetchMovieDetailsTask(this);
            fetchMovieDetailsTask.execute(id);
        }
    }

    @Override
    public void setJsonData(JSONObject jsonData) {
        TextView tv = (TextView) findViewById(R.id.detial_id);
        ImageView iv = (ImageView) findViewById(R.id.detial_img);
        TextView release_date = (TextView) findViewById(R.id.release_date);
        TextView vote_average = (TextView) findViewById(R.id.vote_average);
        TextView overview = (TextView) findViewById(R.id.overview);
        try {
            tv.setText(jsonData.getString("original_title").toString());
            Picasso.with(this).load(Constant.IMG_SIZE_W185+jsonData.getString("poster_path")).into(iv);
            release_date.setText(jsonData.getString("release_date").toString());
            vote_average.setText(jsonData.getString("vote_average".toString())+"/10");
            overview.setText(jsonData.getString("overview").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
