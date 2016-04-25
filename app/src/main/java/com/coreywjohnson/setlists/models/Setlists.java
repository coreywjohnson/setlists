
package com.coreywjohnson.setlists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Setlists {

    @SerializedName("@itemsPerPage")
    @Expose
    private String ItemsPerPage;
    @SerializedName("@page")
    @Expose
    private String Page;
    @SerializedName("@total")
    @Expose
    private String Total;
    @SerializedName("setlist")
    @Expose
    private List<Setlist> setlist = new ArrayList<Setlist>();

    /**
     * @return The ItemsPerPage
     */
    public String getItemsPerPage() {
        return ItemsPerPage;
    }

    /**
     * @param ItemsPerPage The @itemsPerPage
     */
    public void setItemsPerPage(String ItemsPerPage) {
        this.ItemsPerPage = ItemsPerPage;
    }

    /**
     * @return The Page
     */
    public String getPage() {
        return Page;
    }

    /**
     * @param Page The @page
     */
    public void setPage(String Page) {
        this.Page = Page;
    }

    /**
     * @return The Total
     */
    public String getTotal() {
        return Total;
    }

    /**
     * @param Total The @total
     */
    public void setTotal(String Total) {
        this.Total = Total;
    }

    /**
     * @return The setlist
     */
    public List<Setlist> getSetlist() {
        return setlist;
    }

    /**
     * @param setlist The setlist
     */
    public void setSetlist(List<Setlist> setlist) {
        this.setlist = setlist;
    }

}
