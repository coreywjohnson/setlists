package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.SearchResponse;
import com.coreywjohnson.setlists.views.SearchView;

import javax.inject.Inject;

/**
 * Created by corey on 02-May-16.
 */
public class SearchPresenter extends Presenter<SearchView> implements SearchArtistInteractor.SearchArtistCallback {
    private SearchArtistInteractor mInteractor;

    @Inject
    public SearchPresenter() {
    }

    public void onSearch(String query) {
        mInteractor = new SearchArtistInteractor(this);
        mInteractor.execute(query);
    }

    @Override
    public void onSuccess(SearchResponse searchResponse) {
        mView.addItems(searchResponse.getSetlists().getSetlist());
    }

    @Override
    public void onError(String error) {
        mView.makeTextSnackbar(error);
    }
}
