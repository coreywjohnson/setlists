package com.coreywjohnson.setlists.views;

import android.support.v7.widget.Toolbar;

import com.coreywjohnson.setlists.views.common.BaseView;

/**
 * Created by corey on 24-Apr-16.
 */
public interface MainView extends BaseView {
    void setupView();

    void showSetlistsSearch();

    void showArtistsSearch();

    void setToolbar(Toolbar toolbar, boolean showDrawerIndicator, String title);

    void setToolbar(Toolbar toolbar, boolean showDrawerIndicator, int resId);

    void setToolbarText(String title);

    void setToolbarText(int resId);
}
