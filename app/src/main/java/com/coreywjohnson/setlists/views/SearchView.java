package com.coreywjohnson.setlists.views;

import com.coreywjohnson.setlists.models.Setlists;

import java.util.List;

/**
 * Created by corey on 02-May-16.
 */
public interface SearchView extends BaseView {
    void addItems(List<Setlists.Setlist> setlistList);
}
