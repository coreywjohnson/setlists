package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Setlists;

import java.util.List;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public interface SetlistView extends BaseView {
    void addItems(List<Setlists.Song> setlistList);
}
