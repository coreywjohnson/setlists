package com.coreywjohnson.setlists.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.components.DaggerSearchArtistComponent;
import com.coreywjohnson.setlists.components.SearchArtistComponent;
import com.coreywjohnson.setlists.modules.SearchArtistModule;
import com.coreywjohnson.setlists.presenters.SearchArtistPresenter;
import com.coreywjohnson.setlists.views.SearchArtistView;

import javax.inject.Inject;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistFragment extends BaseFragment implements SearchArtistView {
    @Inject
    SearchArtistPresenter mPresenter;

    public static SearchArtistFragment newInstance() {

        Bundle args = new Bundle();

        SearchArtistFragment fragment = new SearchArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchArtistComponent searchArtistComponent =
                DaggerSearchArtistComponent.builder()
                        .appComponent(App.getAppComponent(getContext()))
                        .searchArtistModule(new SearchArtistModule(this))
                        .build();
        searchArtistComponent.inject(this);
    }

    @Override
    protected int getLayout() {
        return 0;
    }
}
