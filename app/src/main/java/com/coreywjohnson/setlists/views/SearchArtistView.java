package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.views.common.PaginatableView;

/**
 * Created by corey on 12-Jun-16.
 */
public interface SearchArtistView extends PaginatableView<Artist> {
    void onError(String error);

    MainView getParentView();

    void showArtist(Artist artist);

    void hideKeyboard();

    void setAdapterHeaderSearchResult(String query);

    void showAdapterFavoritesHeader();

    void showNoFavoritesState();
}
