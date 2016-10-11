package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistRepoInteractor;
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
    private ArtistRepoInteractor mArtistRepoInteractor;
    private SearchArtistView mView;
    private String mQuery;

    @Inject
    public SearchArtistPresenter(SearchArtistInteractor interactor, SearchArtistView view, AnalyticsInteractor analyticsInteractor, ArtistRepoInteractor artistRepoInteractor) {
        super(view, interactor);
        mSearchArtistInteractor = interactor;
        mView = view;
        mAnalyticsInteractor = analyticsInteractor;
        mArtistRepoInteractor = artistRepoInteractor;
    }

    @Override
    public void onCreate(boolean isRestoring) {

    }

    public void onSearch(String query) {
        mQuery = query;
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

    @Override
    public PresenterState getPresenterState() {
        SearchArtistPresenterState presenterState = new SearchArtistPresenterState();
        presenterState.writeState(this);
        return presenterState;
    }

    @Override
    public void restorePresenterState(PresenterState state) {
        super.restorePresenterState(state);
        mQuery = ((SearchArtistPresenterState) state).query;
        if (mQuery != null && !mQuery.isEmpty()) {
            mSearchArtistInteractor.setQuery(mQuery);
        }
    }

    public void onArtistClick(Artists.Artist artist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, artist.getMbid());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.showArtist(artist);
    }

    public static class SearchArtistPresenterState extends PaginatablePresenterState {
        String query;

        public void writeState(SearchArtistPresenter presenter) {
            super.writeState(presenter);
            query = presenter.mQuery;
        }
    }
}
