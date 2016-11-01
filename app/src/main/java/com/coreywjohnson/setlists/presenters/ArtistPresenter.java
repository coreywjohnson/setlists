package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.GetArtistsSetlistsInteractor;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;
import com.coreywjohnson.setlists.models.Artist;
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
        if (!error.equals("Not Found")) {
            mView.makeTextSnackbar(error);
        }
    }

    public void onSetlistClick(Setlists.Setlist setlist, SharedViewWidget sharedViewWidget) {
        Map<String, String> properties = new HashMap<>();
        properties.put(FirebaseAnalytics.Param.CONTENT_TYPE, AnalyticsInteractor.CONTENT_TYPE_SETLIST);
        properties.put(FirebaseAnalytics.Param.ITEM_ID, setlist.getId());
        mAnalyticsInteractor.sendEvent(FirebaseAnalytics.Event.SELECT_CONTENT, properties);
        mView.openSetlist(setlist, sharedViewWidget);
    }

    public void setArtist(Artist artist) {
        mArtist = artist;
        mInteractor.setArtistMbid(artist.getMbid());
    }
}
