package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Setlists;

/**
 * Created by corey on 03-Jul-16.
 */
public interface ArtistView extends PaginatableView<Setlists.Setlist> {
    void openSetlist(Setlists.Setlist setlist);
}
