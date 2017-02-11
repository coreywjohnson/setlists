package com.coreywjohnson.setlists.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by coreyjohnson on 29/11/2016.
 */

public class User {
    @Attribute(name = "flickr", required = false)
    String flickr;

    @Attribute(name = "twitter", required = false)
    String twitter;

    @Attribute(name = "website", required = false)
    String website;

    @Attribute(name = "userId", required = false)
    String userId;

    @Attribute(name = "lastFm", required = false)
    String lastFm;

    @Attribute(name = "mySpace", required = false)
    String mySpace;

    @Attribute(name = "fullname", required = false)
    String fullname;

    @Element(name = "about", required = false)
    String about;

    @Element(name = "url", required = false)
    String url;

    public String getFlickr() {
        return flickr;
    }

    public void setFlickr(String flickr) {
        this.flickr = flickr;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastFm() {
        return lastFm;
    }

    public void setLastFm(String lastFm) {
        this.lastFm = lastFm;
    }

    public String getMySpace() {
        return mySpace;
    }

    public void setMySpace(String mySpace) {
        this.mySpace = mySpace;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
