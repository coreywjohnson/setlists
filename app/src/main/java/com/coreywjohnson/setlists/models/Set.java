
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Set {

    @SerializedName("song")
    @Expose
    private List<Song> song = new ArrayList<Song>();
    @SerializedName("@encore")
    @Expose
    private String Encore;

    /**
     * @return The song
     */
    public List<Song> getSong() {
        return song;
    }

    /**
     * @param song The song
     */
    public void setSong(List<Song> song) {
        this.song = song;
    }

    /**
     * @return The Encore
     */
    public String getEncore() {
        return Encore;
    }

    /**
     * @param Encore The @encore
     */
    public void setEncore(String Encore) {
        this.Encore = Encore;
    }

}
