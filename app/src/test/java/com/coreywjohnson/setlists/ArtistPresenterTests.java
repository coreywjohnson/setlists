package com.coreywjohnson.setlists;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.GetArtistsSetlistsInteractor;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.presenters.ArtistPresenter;
import com.coreywjohnson.setlists.views.ArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by corey on 20-Nov-16.
 * Created in com.coreywjohnson.setlists
 */

@RunWith(MockitoJUnitRunner.class)
public class ArtistPresenterTests {
    @Mock
    ArtistView mArtistView;
    @Mock
    ArtistInteractor mArtistInteractor;
    @Mock
    Artist mArtist;
    @Mock
    GetArtistsSetlistsInteractor mInteractor;
    @Mock
    AnalyticsInteractor mAnalyticsInteractor;
    @InjectMocks
    ArtistPresenter mPresenter;

    @Test
    public void testOnCreate_notRestoring_refresh() {
        mPresenter.onCreate(false);
        verify(mArtistView).clearItems();
        verify(mInteractor).loadPage(1);
    }

    @Test
    public void testOnCreate_isRestoring_doNothing() {
        mPresenter.onCreate(true);
        verify(mArtistView, never()).clearItems();
        verify(mInteractor, never()).loadPage(1);
    }

    @Test
    public void testOnError_isNotFound_doNothing() {
        mPresenter.onError(SetlistService.NOT_FOUND_MESSAGE);
        verify(mArtistView, never()).makeTextSnackbar(anyString());
    }

    @Test
    public void testOnError_notNotFound_showError() {
        String someError = "someerror";
        mPresenter.onError(someError);
        verify(mArtistView).makeTextSnackbar(someError);
    }

    @Test
    public void testOnSetlistClick_sendAnalytics() {
        Setlist setlist = new Setlist();
        setlist.setId("1234");
        SharedViewWidget mock = mock(SharedViewWidget.class);
        mPresenter.onSetlistClick(setlist, mock);
        HashMap<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(eq(FirebaseAnalytics.Event.SELECT_CONTENT), eq(properties));
    }

    @Test
    public void testOnSetlistClick_openSetlist() {
        Setlist setlist = new Setlist();
        SharedViewWidget mock = mock(SharedViewWidget.class);
        mPresenter.onSetlistClick(setlist, mock);
        verify(mArtistView).openSetlist(setlist, mock);
    }

    @Test
    public void testSetArtist_setupInteractor() {
        mPresenter.setArtist(mArtist);
        verify(mInteractor).setArtistMbid(mArtist.getMbid());
    }

    @Test
    public void testSetArtist_storesArtist() {
        mPresenter.setArtist(mArtist);
        Assert.assertEquals(mArtist, mPresenter.getArtist());
    }

    @Test
    public void testOnFavoriteItemInit_setupListener() {
        mPresenter.onFavoriteItemInit();
        verify(mArtistView).setupFavoriteCheckListener();
    }

    @Test
    public void testOnFavoriteItemInit_isFavorite_showFavorited() {
        mPresenter.setArtist(mArtist);
        doReturn(true).when(mArtistInteractor).isFavorited(mArtist);
        mPresenter.onFavoriteItemInit();
        verify(mArtistView).showFavorited();
    }

    @Test
    public void testOnFavoriteItemInit_notFavorite_showUnfavorited() {
        mPresenter.setArtist(mArtist);
        doReturn(false).when(mArtistInteractor).isFavorited(mArtist);
        mPresenter.onFavoriteItemInit();
        verify(mArtistView).showUnfavorited();
    }

    @Test
    public void testOnFavoriteItemChecked_isChecked_updateViewAndStorageToFavorited() {
        mPresenter.setArtist(mArtist);
        mPresenter.onFavoriteItemChecked(true);
        verify(mArtistView).showUnfavorited();
        verify(mArtistInteractor).removeArtistFromFavorites(mArtist);
    }

    @Test
    public void testOnFavoriteItemChecked_notChecked_updateViewAndStorageToUnfavorited() {
        mPresenter.setArtist(mArtist);
        mPresenter.onFavoriteItemChecked(false);
        verify(mArtistView).showFavorited();
        verify(mArtistInteractor).addArtistToFavorites(mArtist);
    }

    @Test
    public void testOnFavoriteItemChecked_isUnchecked_sendsAnalytics() {
        String mbid = "mbid";
        doReturn(mbid).when(mArtist).getMbid();
        mPresenter.setArtist(mArtist);
        mPresenter.onFavoriteItemChecked(false);
        HashMap<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.ITEM_ID, mArtist.getMbid());
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        verify(mAnalyticsInteractor).sendEvent(eq(AnalyticsInteractor.EVENT_ITEM_FAVORITED), eq(properties));
    }

    @Test
    public void testOnFavoriteItemChecked_isChecked_sendsAnalytics() {
        String mbid = "mbid";
        doReturn(mbid).when(mArtist).getMbid();
        mPresenter.setArtist(mArtist);
        mPresenter.onFavoriteItemChecked(true);
        HashMap<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.ITEM_ID, mArtist.getMbid());
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
        verify(mAnalyticsInteractor).sendEvent(eq(AnalyticsInteractor.EVENT_ITEM_UNFAVORITED), eq(properties));
    }
}
