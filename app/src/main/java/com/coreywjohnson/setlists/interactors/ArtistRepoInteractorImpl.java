package com.coreywjohnson.setlists.interactors;

import android.content.Context;

import com.coreywjohnson.setlists.interfaces.interactors.ArtistRepoInteractor;
import com.coreywjohnson.setlists.models.Artists;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by coreyjohnson on 11/10/2016.
 */

public class ArtistRepoInteractorImpl implements ArtistRepoInteractor {
    private final Context mContext;
    private final Realm mRealm;

    @Inject
    public ArtistRepoInteractorImpl(Context context) {
        mContext = context;
        Realm.init(mContext);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void addArtist(Artists.Artist artist) {
//        mRealm.insert(artist);
    }

    @Override
    public ArrayList<Artists.Artist> getArtists() {
//        RealmResults<Artists.Artist> all = mRealm.where(Artists.Artist.class).findAll();
        return new ArrayList<>();
    }
}
