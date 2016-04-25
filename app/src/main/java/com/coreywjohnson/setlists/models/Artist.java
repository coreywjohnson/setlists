
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Artist {

    @SerializedName("@disambiguation")
    @Expose
    private String Disambiguation;
    @SerializedName("@mbid")
    @Expose
    private String Mbid;
    @SerializedName("@name")
    @Expose
    private String Name;
    @SerializedName("@sortName")
    @Expose
    private String SortName;
    @SerializedName("@tmid")
    @Expose
    private String Tmid;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * @return The Disambiguation
     */
    public String getDisambiguation() {
        return Disambiguation;
    }

    /**
     * @param Disambiguation The @disambiguation
     */
    public void setDisambiguation(String Disambiguation) {
        this.Disambiguation = Disambiguation;
    }

    /**
     * @return The Mbid
     */
    public String getMbid() {
        return Mbid;
    }

    /**
     * @param Mbid The @mbid
     */
    public void setMbid(String Mbid) {
        this.Mbid = Mbid;
    }

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
     * @return The SortName
     */
    public String getSortName() {
        return SortName;
    }

    /**
     * @param SortName The @sortName
     */
    public void setSortName(String SortName) {
        this.SortName = SortName;
    }

    /**
     * @return The Tmid
     */
    public String getTmid() {
        return Tmid;
    }

    /**
     * @param Tmid The @tmid
     */
    public void setTmid(String Tmid) {
        this.Tmid = Tmid;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
