package com.coreywjohnson.setlists.interactors;

import android.content.Context;

import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.models.RealmArtist;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by coreyjohnson on 11/10/2016.
 */

public class ArtistInteractorImpl implements ArtistInteractor {
    public static final String ARTIST_REALM = "realm_artists";
    public static final int ARTIST_REALM_VERSION = 1;

    private final Context mContext;
    private final Realm mRealm;

    @Inject
    public ArtistInteractorImpl(Context context) {
        mContext = context;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(ARTIST_REALM)
                .schemaVersion(ARTIST_REALM_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(realmConfiguration);
    }

    @Override
    public void addArtistToFavorites(Artist artist) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(new RealmArtist(artist));
        mRealm.commitTransaction();
    }

    @Override
    public ArrayList<Artist> getFavoriteArtists() {
        RealmResults<RealmArtist> all = mRealm.where(RealmArtist.class).findAllSorted("sortName");
        ArrayList<Artist> pureArtists = new ArrayList<>();
        for (RealmArtist realmArtist : all) {
            pureArtists.add(realmArtist.getPureArtist());
        }
        return pureArtists;
    }

    @Override
    public void removeArtistFromFavorites(Artist artist) {
        mRealm.beginTransaction();
        RealmResults<RealmArtist> results = mRealm.where(RealmArtist.class).equalTo("mbid", artist.getMbid()).findAll();
        results.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public boolean isFavorited(Artist artist) {
        RealmResults<RealmArtist> results = mRealm.where(RealmArtist.class).equalTo("mbid", artist.getMbid()).findAll();
        return !results.isEmpty();
    }
}
