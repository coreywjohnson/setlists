package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.views.ArtistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by corey on 03-Jul-16.
 */

@Module
public class ArtistModule {
    private ArtistView mView;
    private SetlistAdapter.AdapterListener mListener;

    public ArtistModule(ArtistView view, SetlistAdapter.AdapterListener listener) {
        mView = view;
        mListener = listener;
    }

    @Provides
    ArtistView artistView() {
        return mView;
    }

    @Provides
    SetlistAdapter setlistAdapter() {
        return new SetlistAdapter(mListener);
    }
}
