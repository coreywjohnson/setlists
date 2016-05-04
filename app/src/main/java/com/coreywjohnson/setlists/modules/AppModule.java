package com.coreywjohnson.setlists.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coreywjohnson.setlists.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class AppModule {
    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mApp);
    }
}
