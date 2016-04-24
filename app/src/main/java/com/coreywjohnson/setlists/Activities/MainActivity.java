package com.coreywjohnson.setlists.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.presenters.MainPresenter;
import com.coreywjohnson.setlists.views.MainView;

/**
 * Created by corey on 24-Apr-16.
 */
public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mainPresenter.onSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }
}
