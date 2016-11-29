package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Coords implements Serializable {

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
