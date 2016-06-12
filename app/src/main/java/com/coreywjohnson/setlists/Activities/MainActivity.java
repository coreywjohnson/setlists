package com.coreywjohnson.setlists.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.components.DaggerMainComponent;
import com.coreywjohnson.setlists.components.MainComponent;
import com.coreywjohnson.setlists.fragments.SearchSetlistFragment;
import com.coreywjohnson.setlists.fragments.SetlistFragment;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.modules.MainModule;
import com.coreywjohnson.setlists.presenters.MainPresenter;
import com.coreywjohnson.setlists.views.MainView;

import javax.inject.Inject;

/**
 * Created by corey on 24-Apr-16.
 */
public class MainActivity extends BaseActivity implements MainView, SearchSetlistFragment.SearchFragmentListener {
    @Inject MainPresenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent mainComponent = DaggerMainComponent.builder()
                .appComponent(App.getAppComponent(getApplicationContext()))
                .mainModule(new MainModule(this))
                .build();
        mainComponent.inject(this);

        // Create Search Fragment
        SearchSetlistFragment searchFragment = SearchSetlistFragment.newInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, searchFragment)
                    .commit();
        }
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
}
