
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class City {

    @SerializedName("@id")
    @Expose
    private String Id;
    @SerializedName("@name")
    @Expose
    private String Name;
    @SerializedName("@state")
    @Expose
    private String State;
    @SerializedName("@stateCode")
    @Expose
    private String StateCode;
    @SerializedName("coords")
    @Expose
    private Coords coords;
    @SerializedName("country")
    @Expose
    private Country country;

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
     * @return The State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State The @state
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return The StateCode
     */
    public String getStateCode() {
        return StateCode;
    }

    /**
     * @param StateCode The @stateCode
     */
    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    /**
     * @return The coords
     */
    public Coords getCoords() {
        return coords;
    }

    /**
     * @param coords The coords
     */
    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    /**
     * @return The country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return getName() + ", " + getCountry().getName();
    }
}
