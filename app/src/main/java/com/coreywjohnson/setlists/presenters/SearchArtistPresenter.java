package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.SearchArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistPresenter extends PaginatablePresenter<Artists.Artist> {
    private AnalyticsInteractor mAnalyticsInteractor;
    private SearchArtistInteractor mSearchArtistInteractor;
    private SearchArtistView mView;

    @Inject
    public SearchArtistPresenter(SearchArtistInteractor interactor, SearchArtistView view, AnalyticsInteractor analyticsInteractor) {
        super(view, interactor);
        mSearchArtistInteractor = interactor;
        mView = view;
        mAnalyticsInteractor = analyticsInteractor;
    }

    public void onSearch(String query) {
        mSearchArtistInteractor.setQuery(query);
        mView.setAdapterHeaderSearchResult(query);
        onRefresh();
        mView.hideKeyboard();
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.SEARCH_TERM, query);
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SEARCH, properties);
    }

    @Override
    public void onError(String error) {
        super.onError(error);
        if(!error.equals("Not Found")) {
            mView.onError(error);
        }
    }

    public void onArtistClick(Artists.Artist artist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, artist.getMbid());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.showArtist(artist);
    }
}
