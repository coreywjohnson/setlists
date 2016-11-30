package com.coreywjohnson.setlists.components;

import com.coreywjohnson.setlists.fragments.UserFragment;
import com.coreywjohnson.setlists.modules.UserModule;

import dagger.Component;

/**
 * Created by coreyjohnson on 30/11/2016.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = UserModule.class
)
public interface UserComponent {
    UserFragment inject(UserFragment userFragment);
}
