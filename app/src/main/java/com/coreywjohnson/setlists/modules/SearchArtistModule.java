package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.adapter.ArtistAdapter;
import com.coreywjohnson.setlists.views.SearchArtistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by corey on 12-Jun-16.
 */

@Module
public class SearchArtistModule {
    private SearchArtistView mSearchArtistView;
    private ArtistAdapter.AdapterListener mAdapterListener;

    public SearchArtistModule(SearchArtistView searchArtistView, ArtistAdapter.AdapterListener adapterListener) {
        mSearchArtistView = searchArtistView;
        mAdapterListener = adapterListener;
    }

    @Provides
    SearchArtistView searchArtistView() {
        return mSearchArtistView;
    }

    @Provides
    ArtistAdapter.AdapterListener adapterListener() {
        return mAdapterListener;
    }
}
