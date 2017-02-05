package com.coreywjohnson.setlists.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by corey on 01-Nov-16.
 */
public class RealmArtist extends RealmObject {
    String url;
    @PrimaryKey
    String mbid;
    String name;
    String sortName;
    String disambiguation;
    int tmid;

    public RealmArtist() {

    }

    public RealmArtist(Artist artist) {
        this.url = artist.url;
        this.mbid = artist.mbid;
        this.name = artist.name;
        this.sortName = artist.sortName;
        this.disambiguation = artist.disambiguation;
        this.tmid = artist.tmid;
    }

    public Artist getPureArtist() {
        Artist artist = new Artist();
        artist.url = this.url;
        artist.mbid = this.mbid;
        artist.name = this.name;
        artist.sortName = this.sortName;
        artist.disambiguation = this.disambiguation;
        artist.tmid = this.tmid;
        return artist;
    }
}
