package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.views.MainView;

import javax.inject.Inject;

/**
 * Created by corey on 24-Apr-16.
 */
public class MainPresenter extends Presenter {
    MainView mMainView;

    @Inject
    public MainPresenter(MainView mainView) {
        mMainView = mainView;
    }

    public void onCreate() {
        mMainView.showSetlistsSearch();
        mMainView.setupView();
    }
}
