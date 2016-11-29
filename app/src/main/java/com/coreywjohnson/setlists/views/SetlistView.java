package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Set;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.models.Song;
import com.coreywjohnson.setlists.views.common.BaseView;

import java.util.List;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public interface SetlistView extends BaseView {
    void setupRecyclerView(Setlist setlist);

    void revealToolbar();

    void addItems(List<Song> setlistList);

    void displayEmptyState();

    void displayDataState();

    void showHeader();

    void launchWebView(Setlist setlist);

    void addEncoreHeader(int position);

    void addEncoreNumHeader(int encoreNum, int position);

    void addEncoreNameHeader(String name, int position);

    void addSet(Set set);
}
