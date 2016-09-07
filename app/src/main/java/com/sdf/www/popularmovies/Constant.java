package com.sdf.www.popularmovies;

/**
 * Created by shadow on 16/9/7.
 */
public class Constant {
    /*
    * API_KEY 需要添加你的API_KEY 程序才能正常 运行
    * */
    public static final String API_KEY = "";
    public static final String HTTP_BASE_URL = "http://api.themoviedb.org/3/movie";
    public static final String HTTP_POPULAR_URL = HTTP_BASE_URL + "/popular";
    public static final String HTTP_TOP_RATE_URL = HTTP_BASE_URL + "/top_rated";
    public static final String IMG_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String IMG_SIZE_W92 = IMG_BASE_URL + "/w92";
    public static final String IMG_SIZE_W154 = IMG_BASE_URL + "/w154";
    public static final String IMG_SIZE_W185 = IMG_BASE_URL + "/w185";
    public static final String IMG_SIZE_W342 = IMG_BASE_URL + "/w342";
    public static final String IMG_SIZE_W500 = IMG_BASE_URL + "/w500";
    public static final String IMG_SIZE_W780 = IMG_BASE_URL + "/w780";
    public static final String IMG_SIZE_ORIGINAL = IMG_BASE_URL + "/original";

    public static final String API_KEY_PARAM = "api_key";

    public static final String CONNECT_ERROR = "Connect Error";
    public static final String RESULT_IS_NONE = "Result Is None";

    public static final String JSONARRAY_NAME = "results";
    public static final String JSONOBJECT_IMGPATH_NAME = "poster_path";

    public static final int REQUEST_CODE = 0x01;
    public static String JSONOBJECT_ID = "id";
}
