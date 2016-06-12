package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.interactors.SearchSetlistByArtistInteractor;
import com.coreywjohnson.setlists.modules.AppModule;
import com.coreywjohnson.setlists.modules.InteractorsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Singleton
@Component(
        modules = {AppModule.class, InteractorsModule.class}
)
public interface AppComponent {
    SearchSetlistByArtistInteractor provideSearchSetlistByArtistInteractor();

    SearchArtistInteractor provideSearchArtistInteractor();
}
