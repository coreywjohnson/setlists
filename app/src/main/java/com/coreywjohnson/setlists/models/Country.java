package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 29/11/2016.
 */
public class Country implements Serializable {

    @Attribute(name = "code", required = false)
    String code;


    @Attribute(name = "name", required = false)
    String name;


    public String getCode() {
        return this.code;
    }

    public void setCode(String _value) {
        this.code = _value;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String _value) {
        this.name = _value;
    }


}
