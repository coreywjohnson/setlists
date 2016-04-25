
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Venue {

    @SerializedName("@id")
    @Expose
    private String Id;
    @SerializedName("@name")
    @Expose
    private String Name;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * @return The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id The @id
     */
    public void setId(String Id) {
        this.Id = Id;
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
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(City city) {
        this.city = city;
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
