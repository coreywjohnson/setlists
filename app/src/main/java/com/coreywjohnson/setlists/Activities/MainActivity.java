package com.coreywjohnson.setlists.activities;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.SetlistsApp;
import com.coreywjohnson.setlists.components.DaggerMainComponent;
import com.coreywjohnson.setlists.components.MainComponent;
import com.coreywjohnson.setlists.databinding.ActivityMainBinding;
import com.coreywjohnson.setlists.fragments.SearchSetlistFragment;
import com.coreywjohnson.setlists.fragments.SetlistFragment;
import com.coreywjohnson.setlists.fragments.UserFragment;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlist;
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

        boolean restoringState = false;
        if (savedInstanceState != null) {
            restoringState = true;
        }
        mainPresenter.onCreate(restoringState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    public void showSetlist(Setlist setlist, SharedViewWidget sharedViewWidget) {
        final SetlistFragment setlistFragment = SetlistFragment.newInstance(setlist);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition returnTransition = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.explode);

            Transition enterTransition = TransitionInflater.from(this)
                    .inflateTransition(R.transition.setlist_enter_transition);

            Transition sharedTransition = TransitionInflater.from(this)
                    .inflateTransition(R.transition.setlist_shared_transition);

            setlistFragment.setSharedElementEnterTransition(sharedTransition);
            setlistFragment.setSharedElementReturnTransition(null);

            setlistFragment.setEnterTransition(enterTransition);
            setlistFragment.setReturnTransition(returnTransition);

            enterTransition.setStartDelay(sharedTransition.getDuration());

            enterTransition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    setlistFragment.revealToolbar();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, setlistFragment)
                    .addToBackStack("Setlist")
                    .addSharedElement(sharedViewWidget.getSharedView(), sharedViewWidget.getSharedViewName())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, setlistFragment)
                    .addToBackStack("Setlist")
                    .commit();
        }
    }

    @Override
    public void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget) {
        showSetlist(setlist, sharedViewWidget);
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
                    case R.id.action_user:
                        showUserFragment();
                        return true;
                    default:
                        return false;
                }
            }
        });
        mBinding.navigationView.setCheckedItem(R.id.action_setlists);
        mBinding.navigationView.addHeaderView(new HeaderWidget(this));
    }

    public void showUserFragment() {
        // Show user fragment
        // TODO dynamic username
        UserFragment fragment = UserFragment.newInstance("cordogz");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
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
        SearchSetlistFragment searchFragment = SearchSetlistFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, searchFragment)
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
            if (mToggle != null) {
                mToggle.setDrawerIndicatorEnabled(false);
            }
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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
