package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.fragments.SetlistFragment;
import com.coreywjohnson.setlists.modules.SetlistModule;

import dagger.Component;

/**
 * Created by coreyjohnson on 12/05/16.
 */

@Component(
        dependencies = AppComponent.class,
        modules = SetlistModule.class
)
public interface SetlistComponent {
    SetlistFragment inject(SetlistFragment setlistFragment);
}
