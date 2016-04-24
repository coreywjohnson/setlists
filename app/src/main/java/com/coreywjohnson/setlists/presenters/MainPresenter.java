package com.coreywjohnson.setlists.presenters;

import android.util.Log;

import com.coreywjohnson.setlists.views.MainView;

/**
 * Created by corey on 24-Apr-16.
 */
public class MainPresenter extends BasePresenter {
    private MainView mainView;

    public MainPresenter(MainView view) {
        mainView = view;
    }

    public void onSearch(String query) {
        Log.i("Search query", query);
    }
}
