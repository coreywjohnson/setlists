package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.views.common.BaseView;

import java.util.List;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public interface SetlistView extends BaseView {
    void setupRecyclerView(Setlists.Setlist setlist);

    void revealToolbar();

    void addItems(List<Setlists.Song> setlistList);

    void displayEmptyState();

    void displayDataState();

    void showHeader();

    void launchWebView(Setlists.Setlist setlist);

    void addEncoreHeader(int position);

    void addEncoreNumHeader(int encoreNum, int position);

    void addEncoreNameHeader(String name, int position);

    void addSet(Setlists.Set set);
}
