package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.GetArtistsSetlistsInteractor;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.ArtistView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 03-Jul-16.
 */
public class ArtistPresenter extends PaginatablePresenter<Setlists.Setlist> {
    ArtistView mView;
    Artists.Artist mArtist;
    GetArtistsSetlistsInteractor mInteractor;
    AnalyticsInteractor mAnalyticsInteractor;

    @Inject
    public ArtistPresenter(ArtistView view, AnalyticsInteractor analyticsInteractor, GetArtistsSetlistsInteractor interactor) {
        super(view, interactor);
        mView = view;
        mInteractor = interactor;
        mAnalyticsInteractor = analyticsInteractor;
    }

    public void onCreate(Artists.Artist artist) {
        mArtist = artist;
        mInteractor.setArtistMbid(artist.getMbid());
        onRefresh();
    }

    @Override
    public void onError(String error) {
        mView.makeTextSnackbar(error);
    }

    public void onSetlistClick(Setlists.Setlist setlist) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.openSetlist(setlist);
    }
}
