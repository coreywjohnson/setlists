package com.coreywjohnson.setlists.modules;

import android.content.Context;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interactors.ArtistInteractorImpl;
import com.coreywjohnson.setlists.interfaces.interactors.ArtistInteractor;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("User-Agent", "Setlists for Android")
                                .header("Authorization", "569099de-9e96-4429-9609-a063c5198242")
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(SetlistService.BASE_API_URL)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    public SetlistService provideSetlistService(Retrofit retrofit) {
        return retrofit.create(SetlistService.class);
    }

    @Singleton
    @Provides
    public ArtistInteractor provideArtistRepoInteractor(Context context) {
        return new ArtistInteractorImpl(context);
    }
}
