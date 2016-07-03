package com.coreywjohnson.setlists.views;

import android.support.v7.widget.Toolbar;

/**
 * Created by corey on 24-Apr-16.
 */
public interface MainView extends BaseView {
    void setupView();

    void showSetlistsSearch();

    void showArtistsSearch();

    void setToolbar(Toolbar toolbar, boolean showDrawerIndicator, String title);
}
