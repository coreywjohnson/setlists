package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.presenters.common.Presenter;
import com.coreywjohnson.setlists.views.SetlistView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public class SetlistPresenter extends Presenter {
    SetlistView mSetlistView;
    private Setlists.Setlist mSetlist;

    @Inject
    SetlistPresenter(SetlistView setlistView) {
        mSetlistView = setlistView;
    }

    public void displaySetlist() {
        ArrayList<Setlists.Song> songs = mSetlist.getSetlistSongs();
        if (songs != null && songs.isEmpty()) {
            mSetlistView.displayEmptyState();
        } else {
            mSetlistView.displayDataState();
            mSetlistView.addItems(mSetlist.getSetlistSongs());
        }
    }

    @Override
    public void onCreate(boolean isRestoring) {
        if (isRestoring) {
            mSetlistView.showHeader();
        }
    }

    @Override
    public PresenterState getPresenterState() {
        return null;
    }

    @Override
    public void restorePresenterState(PresenterState state) {
        // do nothing
    }

    public void onCreateView(boolean hasSavedState, boolean isTransitioning) {
        if (hasSavedState || !isTransitioning) {
            mSetlistView.showHeader();
        }
        mSetlistView.setupRecyclerView(mSetlist);
        displaySetlist();
    }

    public void setSetlist(Setlists.Setlist setlist) {
        mSetlist = setlist;
    }

    public void onViewWebPressed() {
        mSetlistView.launchWebView(mSetlist);
    }
}
