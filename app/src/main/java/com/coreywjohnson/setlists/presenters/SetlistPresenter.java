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

    @Inject
    SetlistPresenter(SetlistView setlistView) {
        mSetlistView = setlistView;
    }

    public void displaySetlist(Setlists.Setlist setlist) {
        ArrayList<Setlists.Song> songs = setlist.getSetlistSongs();
        if (songs != null && songs.isEmpty()) {
            mSetlistView.displayEmptyState();
        } else {
            mSetlistView.displayDataState();
            mSetlistView.addItems(setlist.getSetlistSongs());
        }
    }
}
