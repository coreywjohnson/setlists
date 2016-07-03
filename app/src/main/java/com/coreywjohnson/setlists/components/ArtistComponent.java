package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.fragments.ArtistFragment;
import com.coreywjohnson.setlists.modules.ArtistModule;

import dagger.Component;

/**
 * Created by corey on 03-Jul-16.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = ArtistModule.class
)
public interface ArtistComponent {
    ArtistFragment inject(ArtistFragment artistFragment);
}
