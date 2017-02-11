package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Coords implements Serializable {

    @Attribute(name = "lat", required = false)
    double lat;


    @Attribute(name = "long", required = false)
    double lon;


    public double getLat() {
        return this.lat;
    }

    public void setLat(double _value) {
        this.lat = _value;
    }


    public double getLong() {
        return this.lon;
    }

    public void setLong(double _value) {
        this.lon = _value;
    }


}
