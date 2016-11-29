package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Setlist implements Serializable {

    @Element(name = "artist", required = false)
    Artist artist;


    @Element(name = "venue", required = false)
    Venue venue;


    @ElementList(name = "sets", required = false)
    List<Set> sets;


    @Element(name = "url", required = false)
    String url;


    @Attribute(name = "eventDate", required = false)
    String eventDate;


    @Attribute(name = "id", required = false)
    String id;


    @Attribute(name = "lastUpdated", required = false)
    String lastUpdated;


    @Attribute(name = "tour", required = false)
    String tour;


    @Attribute(name = "versionId", required = false)
    String versionId;

    public ArrayList<Song> getSetlistSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0; i < sets.size(); i++) {
            Set set = sets.get(i);
            songs.addAll(set.getSong());
        }
        return songs;
    }


    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist _value) {
        this.artist = _value;
    }


    public Venue getVenue() {
        return this.venue;
    }

    public void setVenue(Venue _value) {
        this.venue = _value;
    }


    public List<Set> getSets() {
        return this.sets;
    }

    public void setSets(List<Set> _value) {
        this.sets = _value;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String _value) {
        this.url = _value;
    }


    public String getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(String _value) {
        this.eventDate = _value;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String _value) {
        this.id = _value;
    }


    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String _value) {
        this.lastUpdated = _value;
    }


    public String getTour() {
        return this.tour;
    }

    public void setTour(String _value) {
        this.tour = _value;
    }


    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String _value) {
        this.versionId = _value;
    }


}
