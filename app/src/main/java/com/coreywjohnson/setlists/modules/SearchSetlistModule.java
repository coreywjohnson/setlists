package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.views.SearchSetlistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class SearchSetlistModule {
    SearchSetlistView mSearchSetlistView;
    SetlistAdapter.AdapterListener mAdapterListener;

    public SearchSetlistModule(SearchSetlistView searchSetlistView, SetlistAdapter.AdapterListener adapterListener) {
        mSearchSetlistView = searchSetlistView;
        mAdapterListener = adapterListener;
    }

    @Provides
    SearchSetlistView searchView() {
        return mSearchSetlistView;
    }

    @Provides
    SetlistAdapter.AdapterListener adapterListener() {
        return mAdapterListener;
    }
}
