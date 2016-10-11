package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.interactors.AnalyticsInteractor;
import com.coreywjohnson.setlists.interactors.GetArtistsSetlistsInteractor;
import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.interactors.SearchSetlistInteractor;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistRepoInteractor;
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
    SearchArtistInteractor provideSearchArtistInteractor();
    AnalyticsInteractor provideAnalyticsInteractor();

    ArtistRepoInteractor provideArtistRepoInteractor();

    SearchSetlistInteractor provideSearchSetlistByArtistInteractor();

    GetArtistsSetlistsInteractor provide();
}
