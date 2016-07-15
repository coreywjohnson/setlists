package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Artists;

/**
 * Created by corey on 12-Jun-16.
 */
public interface SearchArtistView extends PaginatableView<Artists.Artist> {
    void onError(String error);

    MainView getParentView();

    void showArtist(Artists.Artist artist);
}
