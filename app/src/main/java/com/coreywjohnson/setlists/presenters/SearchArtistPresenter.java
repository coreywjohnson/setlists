package com.coreywjohnson.setlists.presenters;

import android.util.Log;

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
    private String mQuery;
    private int mPageNo = -1;
    private int mLoadCount = -1;

    @Inject
    public SearchArtistPresenter(SearchArtistInteractor interactor, SearchArtistView view) {
        mSearchArtistInteractor = interactor;
        mView = view;
    }

    public void onSearch(String query) {
        mView.removeAllItems();
        mQuery = query;
        mPageNo = 1;
        mSearchArtistInteractor.execute(mQuery, mPageNo, this);
    }

    public void loadMore() {
        mPageNo++;
        mSearchArtistInteractor.execute(mQuery, mPageNo, this);
    }

    public void refresh() {
        onSearch(mQuery);
    }

    @Override
    public void onSuccess(Artists artists) {
        mView.hideRefresh();
        mView.addItems(artists.getArtist());
        mLoadCount += artists.getArtist().size();
        Log.i("Loading", "Load count is " + mLoadCount);
        // Minus 1 because the api returns an incorrent total count
        if (mLoadCount == (Integer.parseInt(artists.getTotal()) - 1)) {
            mView.notifyNoMoreItems();
        }
    }

    @Override
    public void onError(String error) {
        mView.onError(error);
    }
}
