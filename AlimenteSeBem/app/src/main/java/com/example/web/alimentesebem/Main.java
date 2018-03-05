package com.example.web.alimentesebem;

import android.app.Application;
import android.content.Context;

/**
 * Created by Felipe on 03/03/2018.
 */

public class Main extends Application {
    public static Main app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Context getContext(){
        return app.getBaseContext();
    }
}
