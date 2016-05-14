package com.coreywjohnson.setlists.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.components.DaggerSetlistComponent;
import com.coreywjohnson.setlists.components.SetlistComponent;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.modules.SetlistModule;
import com.coreywjohnson.setlists.presenters.SetlistPresenter;
import com.coreywjohnson.setlists.views.SetlistView;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public class SetlistFragment extends BaseFragment implements SetlistView {
    public static final String SETLIST = "Setlist";

    @Inject
    SetlistPresenter mPresenter;

    public static SetlistFragment newInstance(Setlists.Setlist setlist) {

        Bundle args = new Bundle();
        args.putSerializable(SETLIST, setlist);
        SetlistFragment fragment = new SetlistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetlistComponent setlistComponent = DaggerSetlistComponent.builder()
                .appComponent(App.getAppComponent(getContext()))
                .setlistModule(new SetlistModule(this))
                .build();
        setlistComponent.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setlist;
    }
}
