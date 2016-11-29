package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.List;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Set implements Serializable {

    @ElementList(name = "song", inline = true, required = false)
    List<Song> song;


    @Attribute(name = "encore", required = false)
    String encore;

    @Attribute(name = "name", required = false)
    String name;


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

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

}
