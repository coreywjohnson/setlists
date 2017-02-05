package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "artists")
public class Artists implements Serializable {

    @ElementList(name = "artist", inline = true, required = false)
    List<Artist> artist;


    @Attribute(name = "itemsPerPage", required = false)
    int itemsPerPage;


    @Attribute(name = "page", required = false)
    int page;


    @Attribute(name = "total", required = false)
    int total;


    public List<Artist> getArtist() {
        return this.artist;
    }

    public void setArtist(List<Artist> _value) {
        this.artist = _value;
    }


    public int getItemsPerPage() {
        return this.itemsPerPage;
    }

    public void setItemsPerPage(int _value) {
        this.itemsPerPage = _value;
    }


    public int getPage() {
        return this.page;
    }

    public void setPage(int _value) {
        this.page = _value;
    }


    public int getTotal() {
        return this.total;
    }

    public void setTotal(int _value) {
        this.total = _value;
    }
}