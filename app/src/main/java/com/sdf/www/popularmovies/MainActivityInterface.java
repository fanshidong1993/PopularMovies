package com.sdf.www.popularmovies;

import android.content.Context;
import android.util.Pair;

/**
 * Created by shadow on 16/9/7.
 */
public interface MainActivityInterface {
    public void setImgsUrl(Pair<String,Integer>[] strings);
    public Context getContext();
}
