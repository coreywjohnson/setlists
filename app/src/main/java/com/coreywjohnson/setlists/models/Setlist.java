
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Setlist {

    @SerializedName("@eventDate")
    @Expose
    private String EventDate;
    @SerializedName("@id")
    @Expose
    private String Id;
    @SerializedName("@lastUpdated")
    @Expose
    private String LastUpdated;
    @SerializedName("@tour")
    @Expose
    private String Tour;
    @SerializedName("@versionId")
    @Expose
    private String VersionId;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("sets")
    @Expose
    private Sets sets;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * @return The EventDate
     */
    public String getEventDate() {
        return EventDate;
    }

    /**
     * @param EventDate The @eventDate
     */
    public void setEventDate(String EventDate) {
        this.EventDate = EventDate;
    }

    /**
     * @return The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id The @id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return The LastUpdated
     */
    public String getLastUpdated() {
        return LastUpdated;
    }

    /**
     * @param LastUpdated The @lastUpdated
     */
    public void setLastUpdated(String LastUpdated) {
        this.LastUpdated = LastUpdated;
    }

    /**
     * @return The Tour
     */
    public String getTour() {
        return Tour;
    }

    /**
     * @param Tour The @tour
     */
    public void setTour(String Tour) {
        this.Tour = Tour;
    }

    /**
     * @return The VersionId
     */
    public String getVersionId() {
        return VersionId;
    }

    /**
     * @param VersionId The @versionId
     */
    public void setVersionId(String VersionId) {
        this.VersionId = VersionId;
    }

    /**
     * @return The artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @param artist The artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * @return The venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * @param venue The venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * @return The sets
     */
    public Sets getSets() {
        return sets;
    }

    /**
     * @param sets The sets
     */
    public void setSets(Sets sets) {
        this.sets = sets;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
