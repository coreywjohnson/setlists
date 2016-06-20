package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Artists;

import java.util.List;

/**
 * Created by corey on 12-Jun-16.
 */
public interface SearchArtistView extends BaseView {
    void addItems(List<Artists.Artist> artists);

    void removeAllItems();

    void notifyNoMoreItems();

    void hideRefresh();

    void onError(String error);

    MainView getParentView();
}
