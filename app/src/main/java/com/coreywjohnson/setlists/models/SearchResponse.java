
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class SearchResponse {

    @SerializedName("setlists")
    @Expose
    private Setlists setlists;

    /**
     * @return The setlists
     */
    public Setlists getSetlists() {
        return setlists;
    }

    /**
     * @param setlists The setlists
     */
    public void setSetlists(Setlists setlists) {
        this.setlists = setlists;
    }

}
