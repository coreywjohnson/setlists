package com.coreywjohnson.setlists.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.SetlistsApp;
import com.coreywjohnson.setlists.components.DaggerMainComponent;
import com.coreywjohnson.setlists.components.MainComponent;
import com.coreywjohnson.setlists.databinding.ActivityMainBinding;
import com.coreywjohnson.setlists.fragments.SearchArtistFragment;
import com.coreywjohnson.setlists.fragments.SearchSetlistFragment;
import com.coreywjohnson.setlists.fragments.SetlistFragment;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.modules.MainModule;
import com.coreywjohnson.setlists.presenters.MainPresenter;
import com.coreywjohnson.setlists.views.MainView;
import com.coreywjohnson.setlists.widgets.HeaderWidget;

import javax.inject.Inject;

/**
 * Created by corey on 24-Apr-16.
 */
public class MainActivity extends BaseActivity implements MainView, SearchSetlistFragment.SearchFragmentListener {
    @Inject MainPresenter mainPresenter;
    private ActivityMainBinding mBinding;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent mainComponent = DaggerMainComponent.builder()
                .appComponent(SetlistsApp.getAppComponent(getApplicationContext()))
                .mainModule(new MainModule(this))
                .build();
        mainComponent.inject(this);

        mBinding = DataBindingUtil.setContentView(this, getLayout());

        mainPresenter.onCreate();
    }

    public void showSetlist(Setlists.Setlist setlist) {
        SetlistFragment setlistFragment = SetlistFragment.newInstance(setlist);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, setlistFragment)
                .addToBackStack("Setlist")
                .commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSetlistClick(Setlists.Setlist setlist) {
        showSetlist(setlist);
    }

    @Override
    public void setupView() {
        mBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mBinding.drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.action_artists:
                        showArtistsSearch();
                        return true;
                    case R.id.action_setlists:
                        showSetlistsSearch();
                        return true;
                    default:
                        return false;
                }
            }
        });
        mBinding.navigationView.setCheckedItem(R.id.action_setlists);
        mBinding.navigationView.addHeaderView(new HeaderWidget(this));
    }

    @Override
    public void showSetlistsSearch() {
        // Create Search Fragment
        SearchSetlistFragment searchFragment = SearchSetlistFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, searchFragment)
                .commit();
    }

    @Override
    public void showArtistsSearch() {
        SearchArtistFragment fragment = SearchArtistFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void setToolbar(Toolbar toolbar, boolean showDrawerIndicator, String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (showDrawerIndicator) {
            mToggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, toolbar, R.string.txt_drawer_opened, R.string.txt_drawer_closed);
            mBinding.drawerLayout.addDrawerListener(mToggle);
            mToggle.syncState();
        } else {
            mToggle.setDrawerIndicatorEnabled(false);
        }
        if (title != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar, boolean showDrawerIndicator, int resId) {
        setToolbar(toolbar, showDrawerIndicator, getString(resId));
    }

    @Override
    public void setToolbarText(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setToolbarText(int resId) {
        setToolbarText(getString(resId));
    }
}
