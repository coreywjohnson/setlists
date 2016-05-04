package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.fragments.SearchFragment;
import com.coreywjohnson.setlists.modules.SearchModule;

import dagger.Component;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Component(
        dependencies = AppComponent.class,
        modules = SearchModule.class
)
public interface SearchComponent {
    SearchFragment inject(SearchFragment searchFragment);
}
