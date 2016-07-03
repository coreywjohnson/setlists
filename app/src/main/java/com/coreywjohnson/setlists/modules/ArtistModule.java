package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.views.ArtistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by corey on 03-Jul-16.
 */

@Module
public class ArtistModule {
    private ArtistView mView;

    public ArtistModule(ArtistView view) {
        mView = view;
    }

    @Provides
    ArtistView artistView() {
        return mView;
    }
}
