package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchSetlistByArtistInteractor;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.SearchSetlistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 02-May-16.
 */
public class SearchSetlistPresenter extends PaginatablePresenter<Setlists.Setlist> {
    private SearchSetlistByArtistInteractor mInteractor;
    private SearchSetlistView mSearchSetlistView;
    private AnalyticsInteractor mAnalyticsInteractor;

    @Inject
    public SearchSetlistPresenter(SearchSetlistByArtistInteractor interactor, SearchSetlistView searchSetlistView, AnalyticsInteractor analyticsInteractor) {
        super(searchSetlistView, interactor);
        mInteractor = interactor;
        mSearchSetlistView = searchSetlistView;
        mAnalyticsInteractor = analyticsInteractor;
    }

    public void onSearch(String query) {
        mInteractor.setQuery(query);
        onRefresh();
        mSearchSetlistView.hideKeyboard();
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.SEARCH_TERM, query);
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SEARCH, properties);
    }

    @Override
    public void onError(String error) {
        super.onError(error);
        if (!error.equals("Not Found")) {
            mSearchSetlistView.makeTextSnackbar(error);
            mSearchSetlistView.onError();
        }
    }

    public void onSetlistClick(Setlists.Setlist setlist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mSearchSetlistView.openSetlist(setlist);
    }
}
