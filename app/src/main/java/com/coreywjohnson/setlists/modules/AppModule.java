package com.coreywjohnson.setlists.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coreywjohnson.setlists.SetlistsApp;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class AppModule {
    private SetlistsApp mSetlistsApp;

    public AppModule(SetlistsApp setlistsApp) {
        mSetlistsApp = setlistsApp;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mSetlistsApp);
    }

    @Provides
    @Singleton
    public FirebaseAnalytics provideFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(mSetlistsApp);
    }
}
