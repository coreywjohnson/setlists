package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.GetArtistsSetlistsInteractor;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.ArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 03-Jul-16.
 */
public class ArtistPresenter extends PaginatablePresenter<Setlist> {
    private final ArtistInteractor mArtistInteractor;
    ArtistView mView;
    Artist mArtist;
    GetArtistsSetlistsInteractor mInteractor;
    AnalyticsInteractor mAnalyticsInteractor;

    @Inject
    public ArtistPresenter(ArtistView view, AnalyticsInteractor analyticsInteractor, GetArtistsSetlistsInteractor interactor, ArtistInteractor artistInteractor) {
        super(view, interactor);
        mView = view;
        mInteractor = interactor;
        mAnalyticsInteractor = analyticsInteractor;
        mArtistInteractor = artistInteractor;
    }

    @Override
    public void onCreate(boolean isRestoring) {
        if (!isRestoring) {
            onRefresh();
        }
    }

    @Override
    public void onError(String error) {
        super.onError(error);
        if (!error.equals(SetlistService.NOT_FOUND_MESSAGE)) {
            mView.makeTextSnackbar(error);
        }
    }

    public void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.openSetlist(setlist, sharedViewWidget);
    }

    public Artist getArtist() {
        return mArtist;
    }

    public void setArtist(Artist artist) {
        mArtist = artist;
        mInteractor.setArtistMbid(artist.getMbid());
    }

    public void onFavoriteItemInit() {
        mView.setupFavoriteCheckListener();
        if (mArtistInteractor.isFavorited(mArtist)) {
            mView.showFavorited();
        } else {
            mView.showUnfavorited();
        }
    }

    public void onFavoriteItemChecked(boolean checked) {
        if (checked) {
            mView.showUnfavorited();
            mArtistInteractor.removeArtistFromFavorites(mArtist);
            Map<String, String> properties = new HashMap<>();
            properties.put(FirebaseAnalytics.Param.ITEM_ID, mArtist.getMbid());
            properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
            mAnalyticsInteractor.sendEvent(AnalyticsInteractor.EVENT_ITEM_UNFAVORITED, properties);
        } else {
            mView.showFavorited();
            mArtistInteractor.addArtistToFavorites(mArtist);
            Map<String, String> properties = new HashMap<>();
            properties.put(FirebaseAnalytics.Param.ITEM_ID, mArtist.getMbid());
            properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_ARTIST);
            mAnalyticsInteractor.sendEvent(AnalyticsInteractor.EVENT_ITEM_FAVORITED, properties);
        }
    }
}
