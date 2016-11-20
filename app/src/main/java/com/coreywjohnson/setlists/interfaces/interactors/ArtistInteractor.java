package com.coreywjohnson.setlists.interfaces.interactors;

import com.coreywjohnson.setlists.models.Artist;

import java.util.ArrayList;

/**
 * Created by coreyjohnson on 11/10/2016.
 */

public interface ArtistInteractor {
    void addArtistToFavorites(Artist artist);

    ArrayList<Artist> getFavoriteArtists();

    void removeArtistFromFavorites(Artist artist);

    boolean isFavorited(Artist artist);
}
