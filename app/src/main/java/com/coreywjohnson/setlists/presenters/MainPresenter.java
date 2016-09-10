package com.coreywjohnson.setlists.presenters;

import android.util.Log;

import com.coreywjohnson.setlists.presenters.common.Presenter;
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

    @Override
    public void onCreate(boolean isRestoringState) {
        if (!isRestoringState) {
            mMainView.showSetlistsSearch();
        }
        mMainView.setupView();
    }

    @Override
    public PresenterState getPresenterState() {
        return null;
    }

    @Override
    public void restorePresenterState(PresenterState state) {

    }
}
