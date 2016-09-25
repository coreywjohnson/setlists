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
            int iteratedSongs = 0;
            for(int i = 0; i < mSetlist.getSets().size(); i++) {
                if(i != 0) {
                    if(mSetlist.getSets().size() == 2) {
                        mSetlistView.addEncoreHeader(iteratedSongs);
                    } else {
                        if(mSetlist.getSets().get(i).getName() != null) {
                            mSetlistView.addEncoreNameHeader(mSetlist.getSets().get(i).getName(), iteratedSongs);
                        } else {
                            mSetlistView.addEncoreNumHeader(Integer.parseInt(mSetlist.getSets().get(i).getEncore()), iteratedSongs);
                        }
                    }
                }
                iteratedSongs += mSetlist.getSets().get(i).getSong().size();
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

    public void setSetlist(Setlists.Setlist setlist) {
        mSetlist = setlist;
    }

    public void onViewWebPressed() {
        mSetlistView.launchWebView(mSetlist);
    }
}
