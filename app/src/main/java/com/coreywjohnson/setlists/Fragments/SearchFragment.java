package com.coreywjohnson.setlists.fragments;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.presenters.SearchPresenter;
import com.coreywjohnson.setlists.views.SearchView;

import java.util.List;

/**
 * Created by corey on 24-Apr-16.
 */
public class SearchFragment extends BaseFragment implements SearchView {
    private SearchPresenter mPresenter;

    public SearchFragment() {
        mPresenter = new SearchPresenter(this);
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
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
