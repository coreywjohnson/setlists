package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class City implements Serializable {

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
