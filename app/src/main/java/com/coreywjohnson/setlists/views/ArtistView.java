package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.views.common.PaginatableView;

/**
 * Created by corey on 03-Jul-16.
 */
public interface ArtistView extends PaginatableView<Setlist> {
    void openSetlist(Setlist setlist, SharedViewWidget sharedViewWidget);

    void setupFavoriteCheckListener();

    void showFavorited();

    void showUnfavorited();
}
