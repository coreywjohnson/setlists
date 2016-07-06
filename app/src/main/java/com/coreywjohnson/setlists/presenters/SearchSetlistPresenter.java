package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchSetlistByArtistInteractor;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.SearchSetlistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 02-May-16.
 */
public class SearchSetlistPresenter extends Presenter implements SearchSetlistByArtistInteractor.SearchSetlistByArtistListener {
    private SearchSetlistByArtistInteractor mInteractor;
    private SearchSetlistView mSearchSetlistView;
    private AnalyticsInteractor mAnalyticsInteractor;
    private String mQuery;
    private int mPageNo = -1;

    @Inject
    public SearchSetlistPresenter(SearchSetlistByArtistInteractor interactor, SearchSetlistView searchSetlistView, AnalyticsInteractor analyticsInteractor) {
        mInteractor = interactor;
        mSearchSetlistView = searchSetlistView;
        mAnalyticsInteractor = analyticsInteractor;
    }

    public void onSearch(String query) {
        mSearchSetlistView.removeAllItems();
        mQuery = query;
        mPageNo = 1;
        mInteractor.execute(mQuery, mPageNo, this);
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.SEARCH_TERM, query);
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SEARCH, properties);
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

    public void onSetlistClick(Setlists.Setlist setlist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mSearchSetlistView.openSetlist(setlist);
    }
}
