package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.models.Set;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.models.Song;
import com.coreywjohnson.setlists.presenters.common.Presenter;
import com.coreywjohnson.setlists.views.SetlistView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public class SetlistPresenter extends Presenter {
    SetlistView mSetlistView;
    private Setlist mSetlist;

    @Inject
    SetlistPresenter(SetlistView setlistView) {
        mSetlistView = setlistView;
    }

    public void displaySetlist() {
        ArrayList<Song> songs = mSetlist.getSetlistSongs();
        if (songs != null && songs.isEmpty()) {
            mSetlistView.displayEmptyState();
        } else {
            mSetlistView.displayDataState();
            for (Set set : mSetlist.getSets()) {
                mSetlistView.addSet(set);
            }
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

    public void setSetlist(Setlist setlist) {
        mSetlist = setlist;
    }

    public void onViewWebPressed() {
        mSetlistView.launchWebView(mSetlist);
    }
}
