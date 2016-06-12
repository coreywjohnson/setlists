package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.views.SearchArtistView;

import javax.inject.Inject;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistPresenter extends Presenter implements SearchArtistInteractor.SearchArtistListener {
    private SearchArtistInteractor mSearchArtistInteractor;
    private SearchArtistView mView;

    @Inject
    public SearchArtistPresenter(SearchArtistInteractor interactor, SearchArtistView view) {
        mSearchArtistInteractor = interactor;
        mView = view;
    }

    @Override
    public void onSuccess(Artists artists) {

    }

    @Override
    public void onError(String error) {

    }
}
