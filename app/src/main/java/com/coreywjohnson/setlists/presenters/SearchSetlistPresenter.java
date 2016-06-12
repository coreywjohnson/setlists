package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.SearchSetlistByArtistInteractor;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.SearchSetlistView;

import javax.inject.Inject;

/**
 * Created by corey on 02-May-16.
 */
public class SearchSetlistPresenter extends Presenter implements SearchSetlistByArtistInteractor.SearchArtistCallback {
    private SearchSetlistByArtistInteractor mInteractor;
    private SearchSetlistView mSearchSetlistView;
    private String mQuery;
    private int mPageNo = -1;

    @Inject
    public SearchSetlistPresenter(SearchSetlistByArtistInteractor interactor, SearchSetlistView searchSetlistView) {
        mInteractor = interactor;
        mSearchSetlistView = searchSetlistView;
    }

    public void onSearch(String query) {
        mSearchSetlistView.removeAllItems();
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
        mSearchSetlistView.hideRefresh();
        mSearchSetlistView.addItems(searchResponse.getSetlist());
    }

    @Override
    public void onError(String error) {
        mSearchSetlistView.makeTextSnackbar(error);
        mSearchSetlistView.onError();
    }
}
