package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.views.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class SearchModule {
    SearchView mSearchView;
    SetlistAdapter.AdapterListener mAdapterListener;

    public SearchModule(SearchView searchView, SetlistAdapter.AdapterListener adapterListener) {
        mSearchView = searchView;
        mAdapterListener = adapterListener;
    }

    @Provides
    SearchView searchView() {
        return mSearchView;
    }

    @Provides
    SetlistAdapter.AdapterListener adapterListener() {
        return mAdapterListener;
    }
}
