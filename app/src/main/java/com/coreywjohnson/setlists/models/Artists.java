package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "artists")
public class Artists implements Serializable {

    @ElementList(name = "artist", inline = true, required = false)
    List<Artist> artist;


    @Attribute(name = "itemsPerPage", required = false)
    String itemsPerPage;


    @Attribute(name = "page", required = false)
    String page;


    @Attribute(name = "total", required = false)
    String total;


    public List<Artist> getArtist() {
        return this.artist;
    }

    public void setArtist(List<Artist> _value) {
        this.artist = _value;
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


    public static class Artist implements Serializable {

        @Element(name = "url", required = false)
        String url;


        @Attribute(name = "mbid", required = false)
        String mbid;


        @Attribute(name = "name", required = false)
        String name;


        @Attribute(name = "sortName", required = false)
        String sortName;


        @Attribute(name = "disambiguation", required = false)
        String disambiguation;


        @Attribute(name = "tmid", required = false)
        String tmid;


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


        public String getDisambiguation() {
            return this.disambiguation;
        }

        public void setDisambiguation(String _value) {
            this.disambiguation = _value;
        }


        public String getTmid() {
            return this.tmid;
        }

        public void setTmid(String _value) {
            this.tmid = _value;
        }


    }
}