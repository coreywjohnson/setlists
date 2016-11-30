package com.coreywjohnson.setlists.modules;

import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.views.UserView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coreyjohnson on 30/11/2016.
 */

@Module
public class UserModule {
    UserView mView;
    SetlistAdapter.AdapterListener mAdapterListener;

    public UserModule(UserView view, SetlistAdapter.AdapterListener adapterListener) {
        mView = view;
        mAdapterListener = adapterListener;
    }

    @Provides
    UserView userView() {
        return mView;
    }

    @Provides
    SetlistAdapter.AdapterListener adapterListener() {
        return mAdapterListener;
    }
}
