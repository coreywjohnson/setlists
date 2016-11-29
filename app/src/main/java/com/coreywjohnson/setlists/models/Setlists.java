package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
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


}