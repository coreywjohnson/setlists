package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.SearchView;

import javax.inject.Inject;

/**
 * Created by corey on 02-May-16.
 */
public class SearchPresenter extends Presenter implements SearchArtistInteractor.SearchArtistCallback {
    private SearchArtistInteractor mInteractor;
    private SearchView mSearchView;
    private String mQuery;
    private int mPageNo = -1;

    @Inject
    public SearchPresenter(SearchArtistInteractor interactor, SearchView searchView) {
        mInteractor = interactor;
        mSearchView = searchView;
    }

    public void onSearch(String query) {
        mSearchView.removeAllItems();
        mQuery = query;
        mPageNo = 1;
        mInteractor.execute(mQuery, mPageNo, this);
    }

    public void loadMore() {
        mPageNo++;
        mInteractor.execute(mQuery, mPageNo, this);
    }

    public void refresh() {
        onSearch(mQuery);
    }

    @Override
    public void onSuccess(Setlists searchResponse) {
        mSearchView.hideRefresh();
        mSearchView.addItems(searchResponse.getSetlist());
    }

    @Override
    public void onError(String error) {
        mSearchView.makeTextSnackbar(error);
    }
}
