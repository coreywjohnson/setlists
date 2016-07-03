package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.views.ArtistView;

import javax.inject.Inject;

/**
 * Created by corey on 03-Jul-16.
 */
public class ArtistPresenter extends Presenter {
    ArtistView mView;

    @Inject
    public ArtistPresenter(ArtistView view) {
        mView = view;
    }
}
