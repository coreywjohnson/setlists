package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.SetlistView;

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
        mSetlistView.addItems(setlist.getSetlistSongs());
    }
}
