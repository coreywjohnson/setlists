package com.coreywjohnson.setlists;

import android.app.Application;
import android.content.Context;

import com.coreywjohnson.setlists.components.AppComponent;
import com.coreywjohnson.setlists.components.DaggerAppComponent;
import com.coreywjohnson.setlists.modules.AppModule;

/**
 * Created by coreyjohnson on 4/05/16.
 */
public class App extends Application {
    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        if (app.mAppComponent == null) {
            app.mAppComponent = DaggerAppComponent.create();
        }
        return app.mAppComponent;
    }

    protected AppModule getAppModule() {
        return new AppModule(this);
    }
}
