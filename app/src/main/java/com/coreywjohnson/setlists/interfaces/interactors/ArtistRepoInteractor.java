package com.coreywjohnson.setlists.interfaces.interactors;

import com.coreywjohnson.setlists.models.Artists;

import java.util.ArrayList;

/**
 * Created by coreyjohnson on 11/10/2016.
 */

public interface ArtistRepoInteractor {
    void addArtist(Artists.Artist artist);

    ArrayList<Artists.Artist> getArtists();
}
