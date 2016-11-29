package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.views.common.PaginatableView;

/**
 * Created by corey on 02-May-16.
 */
public interface SearchSetlistView extends PaginatableView<Setlist> {
    void onError();

    MainView getParentView();

    void openSetlist(Setlist setlist, SharedViewWidget sharedViewWidget);

    void hideKeyboard();

    void setAdapterHeaderYesterdaysSetlists();

    void setAdapterHeaderSearchResults(String query);
}
