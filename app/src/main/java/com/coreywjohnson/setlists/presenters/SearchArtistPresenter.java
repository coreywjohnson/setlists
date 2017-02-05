package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.SearchArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistPresenter extends PaginatablePresenter<Artist> {
    private AnalyticsInteractor mAnalyticsInteractor;
    private SearchArtistInteractor mSearchArtistInteractor;
    private ArtistInteractor mArtistInteractor;
    private SearchArtistView mView;
    private String mQuery;
    private DisplayState mDisplayState;

    @Inject
    public SearchArtistPresenter(SearchArtistInteractor interactor, SearchArtistView view, AnalyticsInteractor analyticsInteractor, ArtistInteractor artistRepoInteractor) {
        super(view, interactor);
        mSearchArtistInteractor = interactor;
        mView = view;
        mAnalyticsInteractor = analyticsInteractor;
        mArtistInteractor = artistRepoInteractor;
    }

    @Override
    public void onCreate(boolean isRestoring) {
        if (!isRestoring) {
            showFavorites();
        }
    }

    public void onCreateView() {
        if (mDisplayState != null) {
            switch (mDisplayState) {
                case DISPLAYING_DATA:
                    mView.showDataState();
                    break;
                case DISPLAYING_EMPTY_FAVORITES:
                    mView.showNoFavoritesState();
                    break;
                case DISPLAYING_EMPTY_SEARCH:
                    mView.showEmptyState();
            }
        }
    }

    public void showFavorites() {
        mView.clearItems();
        mView.showAdapterFavoritesHeader();
        ArrayList<Artist> favoriteArtists = mArtistInteractor.getFavoriteArtists();
        if (favoriteArtists.isEmpty()) {
            mView.showNoFavoritesState();
            mDisplayState = DisplayState.DISPLAYING_EMPTY_FAVORITES;
        } else {
            mView.addItems(favoriteArtists, false);
            mDisplayState = DisplayState.DISPLAYING_DATA;
        }
    }

    @Override
    public void onRefresh() {
        if (mQuery != null && !mQuery.isEmpty()) {
            super.onRefresh();
        } else {
            showFavorites();
            mView.hideLoading();
        }
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
    public void onSuccess(List<Artist> items, int totalCount) {
        if (totalCount != 0) {
            mDisplayState = DisplayState.DISPLAYING_DATA;
        } else {
            mDisplayState = DisplayState.DISPLAYING_EMPTY_SEARCH;
        }
        super.onSuccess(items, totalCount);
    }

    @Override
    public void onError(String error) {
        super.onError(error);
        if (!error.equals(SetlistService.NOT_FOUND_MESSAGE)) {
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
        mDisplayState = ((SearchArtistPresenterState) state).displayState;
    }

    public void onArtistClick(Artist artist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, artist.getMbid());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.showArtist(artist);
    }

    public enum DisplayState {
        DISPLAYING_DATA, DISPLAYING_EMPTY_SEARCH, DISPLAYING_EMPTY_FAVORITES
    }

    public static class SearchArtistPresenterState extends PaginatablePresenterState {
        String query;
        DisplayState displayState;

        public void writeState(SearchArtistPresenter presenter) {
            super.writeState(presenter);
            query = presenter.mQuery;
            displayState = presenter.mDisplayState;
        }
    }
}
