package com.example.app_005.Tool;

import android.util.Log;

public class MyGoogleTranslationTool {
    public void googleTranslate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //https://translation.googleapis.com/language/translate/v2?key=AIzaSyBXRIKUoez8W7QUWUi8Hh770uTPIKUdcnw&q=Hello%20world&target=zh

            }
        }).start();
    }
}
