package com.coreywjohnson.setlists;

import com.coreywjohnson.setlists.activities.MainActivity;
import com.coreywjohnson.setlists.fragments.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Component(
        modules = AppModule.class
)
@Singleton
public interface AppComponent {
    MainActivity inject(MainActivity mainActivity);

    SearchFragment inject(SearchFragment searchFragment);
}
