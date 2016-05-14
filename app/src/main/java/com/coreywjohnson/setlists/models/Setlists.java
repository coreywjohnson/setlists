package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "setlists")
public class Setlists implements Serializable {

    @ElementList(name = "setlist", inline = true, required = false)
    List<Setlist> setlist;


    @Attribute(name = "itemsPerPage", required = false)
    String itemsPerPage;


    @Attribute(name = "page", required = false)
    String page;


    @Attribute(name = "total", required = false)
    String total;


    public List<Setlist> getSetlist() {
        return this.setlist;
    }

    public void setSetlist(List<Setlist> _value) {
        this.setlist = _value;
    }


    public String getItemsPerPage() {
        return this.itemsPerPage;
    }

    public void setItemsPerPage(String _value) {
        this.itemsPerPage = _value;
    }


    public String getPage() {
        return this.page;
    }

    public void setPage(String _value) {
        this.page = _value;
    }


    public String getTotal() {
        return this.total;
    }

    public void setTotal(String _value) {
        this.total = _value;
    }


    public static class Setlist implements Serializable {

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

    public static class Artist implements Serializable {

        @Element(name = "url", required = false)
        String url;


        @Attribute(name = "disambiguation", required = false)
        String disambiguation;


        @Attribute(name = "mbid", required = false)
        String mbid;


        @Attribute(name = "name", required = false)
        String name;


        @Attribute(name = "sortName", required = false)
        String sortName;


        @Attribute(name = "tmid", required = false)
        String tmid;


        public String getUrl() {
            return this.url;
        }

        public void setUrl(String _value) {
            this.url = _value;
        }


        public String getDisambiguation() {
            return this.disambiguation;
        }

        public void setDisambiguation(String _value) {
            this.disambiguation = _value;
        }


        public String getMbid() {
            return this.mbid;
        }

        public void setMbid(String _value) {
            this.mbid = _value;
        }


        public String getName() {
            return this.name;
        }

        public void setName(String _value) {
            this.name = _value;
        }


        public String getSortName() {
            return this.sortName;
        }

        public void setSortName(String _value) {
            this.sortName = _value;
        }


        public String getTmid() {
            return this.tmid;
        }

        public void setTmid(String _value) {
            this.tmid = _value;
        }


    }

    public static class Venue implements Serializable {

        @Element(name = "city", required = false)
        City city;


        @Element(name = "url", required = false)
        String url;


        @Attribute(name = "id", required = false)
        String id;


        @Attribute(name = "name", required = false)
        String name;


        public City getCity() {
            return this.city;
        }

        public void setCity(City _value) {
            this.city = _value;
        }


        public String getUrl() {
            return this.url;
        }

        public void setUrl(String _value) {
            this.url = _value;
        }


        public String getId() {
            return this.id;
        }

        public void setId(String _value) {
            this.id = _value;
        }


        public String getName() {
            return this.name;
        }

        public void setName(String _value) {
            this.name = _value;
        }


    }

    public static class City implements Serializable {

        @Element(name = "coords", required = false)
        Coords coords;


        @Element(name = "country", required = false)
        Country country;


        @Attribute(name = "id", required = false)
        String id;


        @Attribute(name = "name", required = false)
        String name;


        @Attribute(name = "state", required = false)
        String state;


        @Attribute(name = "stateCode", required = false)
        String stateCode;


        public Coords getCoords() {
            return this.coords;
        }

        public void setCoords(Coords _value) {
            this.coords = _value;
        }


        public Country getCountry() {
            return this.country;
        }

        public void setCountry(Country _value) {
            this.country = _value;
        }


        public String getId() {
            return this.id;
        }

        public void setId(String _value) {
            this.id = _value;
        }


        public String getName() {
            return this.name;
        }

        public void setName(String _value) {
            this.name = _value;
        }


        public String getState() {
            return this.state;
        }

        public void setState(String _value) {
            this.state = _value;
        }


        public String getStateCode() {
            return this.stateCode;
        }

        public void setStateCode(String _value) {
            this.stateCode = _value;
        }


    }

    public static class Coords implements Serializable {

        @Attribute(name = "lat", required = false)
        String lat;


        @Attribute(name = "long", required = false)
        String lon;


        public String getLat() {
            return this.lat;
        }

        public void setLat(String _value) {
            this.lat = _value;
        }


        public String getLong() {
            return this.lon;
        }

        public void setLong(String _value) {
            this.lon = _value;
        }


    }

    public static class Country implements Serializable {

        @Attribute(name = "code", required = false)
        String code;


        @Attribute(name = "name", required = false)
        String name;


        public String getCode() {
            return this.code;
        }

        public void setCode(String _value) {
            this.code = _value;
        }


        public String getName() {
            return this.name;
        }

        public void setName(String _value) {
            this.name = _value;
        }


    }

    public static class Song implements Serializable {

        @Attribute(name = "name", required = false)
        String name;


        @Element(name = "info", required = false)
        String info;


        @Element(name = "cover", required = false)
        Cover cover;


        public String getName() {
            return this.name;
        }

        public void setName(String _value) {
            this.name = _value;
        }


        public String getInfo() {
            return this.info;
        }

        public void setInfo(String _value) {
            this.info = _value;
        }


        public Cover getCover() {
            return this.cover;
        }

        public void setCover(Cover _value) {
            this.cover = _value;
        }


    }

    public static class Set implements Serializable {

        @ElementList(name = "song", inline = true, required = false)
        List<Song> song;


        @Attribute(name = "encore", required = false)
        String encore;


        public List<Song> getSong() {
            return this.song;
        }

        public void setSong(List<Song> _value) {
            this.song = _value;
        }


        public String getEncore() {
            return this.encore;
        }

        public void setEncore(String _value) {
            this.encore = _value;
        }


    }

    public static class Cover implements Serializable {

        @Element(name = "url", required = false)
        String url;


        @Attribute(name = "mbid", required = false)
        String mbid;


        @Attribute(name = "name", required = false)
        String name;


        @Attribute(name = "sortName", required = false)
        String sortName;


        public String getUrl() {
            return this.url;
        }

        public void setUrl(String _value) {
            this.url = _value;
        }


        public String getMbid() {
            return this.mbid;
        }

        public void setMbid(String _value) {
            this.mbid = _value;
        }


        public String getName() {
            return this.name;
        }

        public void setName(String _value) {
            this.name = _value;
        }


        public String getSortName() {
            return this.sortName;
        }

        public void setSortName(String _value) {
            this.sortName = _value;
        }


    }
}