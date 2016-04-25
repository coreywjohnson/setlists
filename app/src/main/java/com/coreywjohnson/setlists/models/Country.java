
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Country {

    @SerializedName("@code")
    @Expose
    private String Code;
    @SerializedName("@name")
    @Expose
    private String Name;

    /**
     * @return The Code
     */
    public String getCode() {
        return Code;
    }

    /**
     * @param Code The @code
     */
    public void setCode(String Code) {
        this.Code = Code;
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

}
