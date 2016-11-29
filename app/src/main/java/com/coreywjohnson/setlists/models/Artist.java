package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Artist implements Serializable {

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
