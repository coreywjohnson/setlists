package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.views.SetlistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 12/05/16.
 */

@Module
public class SetlistModule {
    SetlistView mSetlistView;

    public SetlistModule(SetlistView setlistView) {
        mSetlistView = setlistView;
    }

    @Provides
    SetlistView setlistView() {
        return mSetlistView;
    }
}
