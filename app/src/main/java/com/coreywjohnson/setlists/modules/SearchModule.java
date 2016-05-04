package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.views.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class SearchModule {
    SearchView mSearchView;

    public SearchModule(SearchView searchView) {
        mSearchView = searchView;
    }

    @Provides
    SearchView searchView() {
        return mSearchView;
    }
}
