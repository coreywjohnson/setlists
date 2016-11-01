package com.coreywjohnson.setlists;

import android.app.Application;
import android.content.Context;

import com.coreywjohnson.setlists.components.AppComponent;
import com.coreywjohnson.setlists.components.DaggerAppComponent;
import com.coreywjohnson.setlists.modules.AppModule;
import com.coreywjohnson.setlists.modules.InteractorsModule;

import io.realm.Realm;

/**
 * Created by coreyjohnson on 4/05/16.
 */
public class SetlistsApp extends Application {
    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Context context) {
        SetlistsApp setlistsApp = (SetlistsApp) context.getApplicationContext();
        if (setlistsApp.mAppComponent == null) {
            setlistsApp.mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(setlistsApp))
                    .interactorsModule(new InteractorsModule())
                    .build();
        }
        return setlistsApp.mAppComponent;
    }

    protected AppModule getAppModule() {
        return new AppModule(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
