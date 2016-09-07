package com.sdf.www.popularmovies;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by shadow on 16/9/7.
 */
public class ImagesAdapter extends BaseAdapter {
    private Pair<String,Integer>[] urls;
    private Context context;
    public ImagesAdapter(Context context , Pair<String,Integer>[] urls){
        this.urls = urls;
        this.context = context;
    }
    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int i) {
        return urls[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.grid_item_popmovies_img,null);
            viewHolder.iv = (ImageView) view.findViewById(R.id.grid_item_popmovies_imgview);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(urls[i].first).into(viewHolder.iv);
        return view;
    }
    public class ViewHolder{
        ImageView iv;
    }
}
