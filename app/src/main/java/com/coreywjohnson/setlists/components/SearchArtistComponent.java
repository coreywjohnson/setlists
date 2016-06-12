package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.fragments.SearchArtistFragment;
import com.coreywjohnson.setlists.modules.SearchArtistModule;

import dagger.Component;

/**
 * Created by corey on 12-Jun-16.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = SearchArtistModule.class
)
public interface SearchArtistComponent {
    SearchArtistFragment inject(SearchArtistFragment searchArtistFragment);
}
