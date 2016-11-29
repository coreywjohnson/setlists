package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Song implements Serializable {

    @Attribute(name = "name", required = false)
    String name;

    @Attribute(name = "tape", required = false)
    boolean tape;


    @Element(name = "info", required = false)
    String info;


    @Element(name = "cover", required = false)
    Cover cover;

    @Element(name = "with", required = false)
    Artist with;


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

    public boolean isTape() {
        return tape;
    }

    public void setTape(boolean value) {
        this.tape = value;
    }

    public Artist getWith() {
        return with;
    }

    public void setWith(Artist with) {
        this.with = with;
    }
}
