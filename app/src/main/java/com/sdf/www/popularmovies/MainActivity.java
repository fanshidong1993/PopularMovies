package com.sdf.www.popularmovies;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    //FetchPopMoviesImgTask fetchPopMoviesImgTask;
    GridView gridView;
    ImagesAdapter imagesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Constant.API_KEY != null)
            init();
        else {
            Dialog dialog = new Dialog(this);
            TextView tv = new TextView(this);
            tv.setText("请先在 com.sdf.www.popularmovies.Constant 把 API_KEY 设置为有效的 API_KEY");
            dialog.setContentView(tv);
            dialog.show();
        }
    }

    private void init() {
        setTitle("Pop Movies");
        FetchPopMoviesImgTask fetchPopMoviesImgTask = new FetchPopMoviesImgTask(this);
        gridView = (GridView) findViewById(R.id.gridview_popmovies);
        fetchPopMoviesImgTask.execute();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                Pair<String,Integer> item = (Pair<String, Integer>) imagesAdapter.getItem(i);
                intent.putExtra(Constant.JSONOBJECT_ID,item.second);
                Log.v(MainActivity.class.getSimpleName(),String.valueOf(item.second));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setImgsUrl(Pair<String,Integer>[] strings) {
        if(strings != null){
            imagesAdapter = new ImagesAdapter(this,strings);
            gridView.setAdapter(imagesAdapter);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_setting:
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivityForResult(intent,Constant.REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == Constant.REQUEST_CODE){
                new FetchPopMoviesImgTask(this).execute();
            }
        }
    }

    @Override
    protected void onDestroy() {
        Log.v(MainActivity.class.getSimpleName(),"onDestroy");
        super.onDestroy();
    }
}
