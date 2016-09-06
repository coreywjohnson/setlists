package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchSetlistInteractor;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.SearchSetlistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 02-May-16.
 */
public class SearchSetlistPresenter extends PaginatablePresenter<Setlists.Setlist> {
    private SearchSetlistInteractor mInteractor;
    private SearchSetlistView mSearchSetlistView;
    private AnalyticsInteractor mAnalyticsInteractor;
    private String mQuery;
    private String mDate;

    @Inject
    public SearchSetlistPresenter(SearchSetlistInteractor interactor, SearchSetlistView searchSetlistView, AnalyticsInteractor analyticsInteractor) {
        super(searchSetlistView, interactor);
        mInteractor = interactor;
        mSearchSetlistView = searchSetlistView;
        mAnalyticsInteractor = analyticsInteractor;
    }

    public void onSearch(String query) {
        mDate = null;
        mQuery = query;
        mInteractor.clearParameters();
        mInteractor.setName(query);
        onRefresh();
        mSearchSetlistView.setAdapterHeaderSearchResults(query);
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

    @Override
    public PresenterState getPresenterState() {
        SearchSetlistPresenterState state = new SearchSetlistPresenterState();
        state.writeState(this);
        return state;
    }

    @Override
    public void restorePresenterState(PresenterState state) {
        super.restorePresenterState(state);
        mQuery = ((SearchSetlistPresenterState) state).query;
        mDate = ((SearchSetlistPresenterState) state).date;
        if (mQuery != null && !mQuery.isEmpty()) {
            mInteractor.setName(mQuery);
        }
        if (mDate != null && !mDate.isEmpty()) {
            mInteractor.setDate(mDate);
        }
    }

    public void onSetlistClick(Setlists.Setlist setlist, SharedViewWidget sharedViewWidget) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mSearchSetlistView.openSetlist(setlist, sharedViewWidget);
    }

    public void onCreate(boolean isRestoring) {
        if (!isRestoring) {
            // Show initial Search
            Date date = new Date();
            date.setDate(date.getDate() - 1);
            mDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
            mInteractor.setDate(mDate);
            mSearchSetlistView.setAdapterHeaderYesterdaysSetlists();
            onRefresh();
        }
    }

    public static class SearchSetlistPresenterState extends PaginatablePresenterState {
        public String query;
        public String date;

        public void writeState(SearchSetlistPresenter presenter) {
            super.writeState(presenter);
            query = presenter.mQuery;
            date = presenter.mDate;
        }
    }
}
