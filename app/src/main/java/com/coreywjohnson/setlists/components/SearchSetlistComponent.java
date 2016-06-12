package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.fragments.SearchSetlistFragment;
import com.coreywjohnson.setlists.modules.SearchSetlistModule;

import dagger.Component;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = SearchSetlistModule.class
)
public interface SearchSetlistComponent {
    SearchSetlistFragment inject(SearchSetlistFragment searchFragment);
}
