package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.common.PaginatableView;

/**
 * Created by corey on 03-Jul-16.
 */
public interface ArtistView extends PaginatableView<Setlists.Setlist> {
    void openSetlist(Setlists.Setlist setlist, SharedViewWidget sharedViewWidget);

    void setupFavoriteCheckListener();

    void showFavorited();

    void showUnfavorited();
}
