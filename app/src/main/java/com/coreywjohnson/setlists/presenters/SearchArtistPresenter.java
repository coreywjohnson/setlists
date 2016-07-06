package com.coreywjohnson.setlists.presenters;

import android.util.Log;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.views.SearchArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistPresenter extends Presenter implements SearchArtistInteractor.SearchArtistListener {
    private AnalyticsInteractor mAnalyticsInteractor;
    private SearchArtistInteractor mSearchArtistInteractor;
    private SearchArtistView mView;
    private String mQuery;
    private int mPageNo = -1;
    private int mLoadCount = -1;

    @Inject
    public SearchArtistPresenter(SearchArtistInteractor interactor, SearchArtistView view, AnalyticsInteractor analyticsInteractor) {
        mSearchArtistInteractor = interactor;
        mView = view;
        mAnalyticsInteractor = analyticsInteractor;
    }

    public void onSearch(String query) {
        mView.removeAllItems();
        mQuery = query;
        mPageNo = 1;
        mSearchArtistInteractor.execute(mQuery, mPageNo, this);
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.SEARCH_TERM, query);
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SEARCH, properties);
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

    public void onArtistClick(Artists.Artist artist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, artist.getMbid());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.showArtist(artist);
    }
}
