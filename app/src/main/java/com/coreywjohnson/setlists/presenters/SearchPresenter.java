package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.SearchResponse;
import com.coreywjohnson.setlists.views.SearchView;

/**
 * Created by corey on 02-May-16.
 */
public class SearchPresenter implements SearchArtistInteractor.SearchArtistCallback {
    private SearchView searchView;
    private SearchArtistInteractor mInteractor;

    public SearchPresenter(SearchView view) {
        searchView = view;
    }

    public void onSearch(String query) {
        mInteractor = new SearchArtistInteractor(this);
        mInteractor.execute(query);
    }

    @Override
    public void onSuccess(SearchResponse searchResponse) {
        searchView.addItems(searchResponse.getSetlists().getSetlist());
    }

    @Override
    public void onError(String error) {
        searchView.makeTextSnackbar(error);
    }
}
