package com.coreywjohnson.setlists.models;

import java.io.Serializable;

/**
 * Created by coreyjohnson on 6/09/2016.
 */

public class HeaderObject implements Serializable {

    public int position;
    public String text;

    public HeaderObject(int position, String text) {
        this.text = text;
        this.position = position;
    }

}