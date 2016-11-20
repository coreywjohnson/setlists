package com.coreywjohnson.setlists.interactors;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by corey on 04-Jul-16.
 */
public class AnalyticsInteractor {
    public static final String EVENT_ITEM_FAVORITED = "item_favorited";
    public static final String EVENT_ITEM_UNFAVORITED = "item_unfavorited";
    public static String CONTENT_TYPE_SETLIST = "setlist";
    public static String CONTENT_TYPE_ARTIST = "artist";

    FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    public AnalyticsInteractor(FirebaseAnalytics firebaseAnalytics) {
        mFirebaseAnalytics = firebaseAnalytics;
    }

    public void sendEvent(String name, Map<String, String> properties) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        mFirebaseAnalytics.logEvent(name, bundle);
    }
}
