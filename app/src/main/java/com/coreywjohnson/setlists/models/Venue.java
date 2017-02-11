package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Venue implements Serializable {

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
