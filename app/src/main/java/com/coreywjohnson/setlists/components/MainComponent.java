package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.activities.MainActivity;
import com.coreywjohnson.setlists.modules.MainModule;

import dagger.Component;

/**
 * Created by coreyjohnson on 4/05/16.
 */

@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    MainActivity inject(MainActivity mainActivity);
}
