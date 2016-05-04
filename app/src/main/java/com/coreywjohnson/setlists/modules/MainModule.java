package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.views.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class MainModule {
    MainView mMainView;

    public MainModule(MainView mainView) {
        mMainView = mainView;
    }

    @Provides
    MainView searchView() {
        return mMainView;
    }
}
