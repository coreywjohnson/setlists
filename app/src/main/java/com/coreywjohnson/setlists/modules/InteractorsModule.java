package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.data.SetlistService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Module
public class InteractorsModule {
    @Singleton
    @Provides
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(SetlistService.BASE_API_URL)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                .build();
    }

    @Singleton
    @Provides
    public SetlistService provideSetlistService(Retrofit retrofit) {
        return retrofit.create(SetlistService.class);
    }
}
