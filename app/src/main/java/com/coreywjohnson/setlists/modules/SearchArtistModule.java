package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.views.SearchArtistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by corey on 12-Jun-16.
 */

@Module
public class SearchArtistModule {
    private SearchArtistView mSearchArtistView;

    public SearchArtistModule(SearchArtistView searchArtistView) {
        mSearchArtistView = searchArtistView;
    }

    @Provides
    SearchArtistView searchArtistView() {
        return mSearchArtistView;
    }
}
