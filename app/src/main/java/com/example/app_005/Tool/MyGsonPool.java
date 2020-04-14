package com.example.app_005.Tool;

import com.google.gson.Gson;

public class MyGsonPool {
    public static Gson gson=new Gson();

    public static Gson Sta_getGson(){
        return gson;
    }

}
