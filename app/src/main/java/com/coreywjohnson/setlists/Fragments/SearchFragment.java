package com.coreywjohnson.setlists.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.components.DaggerSearchComponent;
import com.coreywjohnson.setlists.components.SearchComponent;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.modules.SearchModule;
import com.coreywjohnson.setlists.presenters.SearchPresenter;
import com.coreywjohnson.setlists.views.SearchView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by corey on 24-Apr-16.
 */
public class SearchFragment extends BaseFragment implements SearchView {
    @Inject
    SearchPresenter mPresenter;

    @Inject
    SearchArtistInteractor searchArtistInteractor;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchComponent searchComponent =
                DaggerSearchComponent.builder()
                        .appComponent(App.getAppComponent(getContext()))
                        .searchModule(new SearchModule(this))
                        .build();
        searchComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected boolean hasMenu() {
        return true;
    }

    @Override
    protected int getMenu() {
        return R.menu.menu_search;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.onSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void addItems(List<Setlist> setlistList) {

    }
}
