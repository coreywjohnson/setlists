
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Sets {

    @SerializedName("set")
    @Expose
    private List<Set> set = new ArrayList<Set>();

    /**
     * @return The set
     */
    public List<Set> getSet() {
        return set;
    }

    /**
     * @param set The set
     */
    public void setSet(List<Set> set) {
        this.set = set;
    }

}
