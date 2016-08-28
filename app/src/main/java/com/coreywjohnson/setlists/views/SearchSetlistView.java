package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.common.PaginatableView;

/**
 * Created by corey on 02-May-16.
 */
public interface SearchSetlistView extends PaginatableView<Setlists.Setlist> {
    void onError();

    MainView getParentView();

    void openSetlist(Setlists.Setlist setlist, SharedViewWidget sharedViewWidget);

    void hideKeyboard();

    void setAdapterHeaderYesterdaysSetlists();

    void setAdapterHeaderSearchResults(String query);
}
