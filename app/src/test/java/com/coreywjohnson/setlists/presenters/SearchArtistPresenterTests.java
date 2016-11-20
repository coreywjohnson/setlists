package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.presenters.common.Presenter;
import com.coreywjohnson.setlists.views.SearchArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by corey on 20-Nov-16.
 * Created in com.coreywjohnson.setlists.presenters
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchArtistPresenterTests {
    @Mock
    AnalyticsInteractor mAnalyticsInteractor;
    @Mock
    SearchArtistInteractor mSearchArtistInteractor;
    @Mock
    ArtistInteractor mArtistInteractor;
    @Mock
    SearchArtistView mView;
    @InjectMocks
    SearchArtistPresenter mPresenter;

    @Test
    public void testOnCreate_notRestoring_showFavorites() {
        mPresenter.onCreate(false);
        verify(mView).showAdapterFavoritesHeader();
        verify(mView).addItems(any(List.class), eq(false));
    }

    @Test
    public void testOnCreate_isRestoring_doNothing() {
        mPresenter.onCreate(true);
        verify(mView, never()).showAdapterFavoritesHeader();
        verify(mView, never()).addItems(any(List.class), anyBoolean());
    }

    @Test
    public void testOnSearch_setupInteractorPerformSearch() {
        String someSearch = "somesearch";
        mPresenter.onSearch(someSearch);
        verify(mView).setAdapterHeaderSearchResult(someSearch);
        verify(mSearchArtistInteractor).setQuery(someSearch);
        verify(mView).clearItems();
        verify(mSearchArtistInteractor).loadPage(1);
    }

    @Test
    public void testOnSearch_hideKeyboard() {
        mPresenter.onSearch("somesearch");
        verify(mView).hideKeyboard();
    }

    @Test
    public void testOnSearch_sendAnalytics() {
        String query = "somesearch";
        mPresenter.onSearch(query);
        HashMap<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.SEARCH_TERM, query);
        verify(mAnalyticsInteractor).sendEvent(eq(FirebaseAnalytics.Event.SEARCH), eq(properties));
    }

    @Test
    public void testOnError_isNotFound_doNothing() {
        mPresenter.onError(SetlistService.NOT_FOUND_MESSAGE);
        verify(mView, never()).onError(anyString());
    }

    @Test
    public void testOnError_isntNotFound_showError() {
        String someError = "someError";
        mPresenter.onError(someError);
        verify(mView).onError(someError);
    }

    @Test
    public void testGetPresenterState_writesStateCorrectly() {
        String someSearch = "someSearch";
        mPresenter.onSearch(someSearch);
        Presenter.PresenterState presenterState = mPresenter.getPresenterState();
        Assert.assertEquals(someSearch, ((SearchArtistPresenter.SearchArtistPresenterState) presenterState).query);
    }

    @Test
    public void testRestorePresenterState_withQuery_restoresState() {
        SearchArtistPresenter.SearchArtistPresenterState searchArtistPresenterState = new SearchArtistPresenter.SearchArtistPresenterState();
        String someQuery = "someQuery";
        searchArtistPresenterState.query = someQuery;
        mPresenter.restorePresenterState(searchArtistPresenterState);
        verify(mSearchArtistInteractor).setQuery(someQuery);
    }

    @Test
    public void testRestorePresenterState_withEmptyQuery_doNothing() {
        SearchArtistPresenter.SearchArtistPresenterState searchArtistPresenterState = new SearchArtistPresenter.SearchArtistPresenterState();
        String emptyQuery = "";
        searchArtistPresenterState.query = emptyQuery;
        mPresenter.restorePresenterState(searchArtistPresenterState);
        verify(mSearchArtistInteractor, never()).setQuery(anyString());
    }

    @Test
    public void testOnArtistClick_showsArtist() {
        Artist artist = new Artist();
        mPresenter.onArtistClick(artist);
        verify(mView).showArtist(artist);
    }

    @Test
    public void testOnArtistClick_sendsAnalytics() {
        Artist artist = new Artist();
        artist.setMbid("somevalue");
        mPresenter.onArtistClick(artist);
        HashMap<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, artist.getMbid());
        verify(mAnalyticsInteractor).sendEvent(eq(FirebaseAnalytics.Event.SELECT_CONTENT), eq(properties));
    }
}