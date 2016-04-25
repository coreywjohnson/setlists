
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Song {

    @SerializedName("@name")
    @Expose
    private String Name;
    @SerializedName("info")
    @Expose
    private String info;

    /**
     * @return The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name The @name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return The info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info The info
     */
    public void setInfo(String info) {
        this.info = info;
    }

}
