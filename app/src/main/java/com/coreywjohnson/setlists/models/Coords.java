
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Coords {

    @SerializedName("@lat")
    @Expose
    private String Lat;
    @SerializedName("@long")
    @Expose
    private String Long;

    /**
     * @return The Lat
     */
    public String getLat() {
        return Lat;
    }

    /**
     * @param Lat The @lat
     */
    public void setLat(String Lat) {
        this.Lat = Lat;
    }

    /**
     * @return The Long
     */
    public String getLong() {
        return Long;
    }

    /**
     * @param Long The @long
     */
    public void setLong(String Long) {
        this.Long = Long;
    }

}
