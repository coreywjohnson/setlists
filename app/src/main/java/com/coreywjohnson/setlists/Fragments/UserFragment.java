package com.coreywjohnson.setlists.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.SetlistsApp;
import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.components.DaggerUserComponent;
import com.coreywjohnson.setlists.components.UserComponent;
import com.coreywjohnson.setlists.databinding.FragmentUserBinding;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.modules.UserModule;
import com.coreywjohnson.setlists.presenters.UserPresenter;
import com.coreywjohnson.setlists.views.UserView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 30/11/2016.
 */

public class UserFragment extends BaseFragment implements UserView, SetlistAdapter.AdapterListener {
    @Inject
    UserPresenter mPresenter;
    @Inject
    SetlistAdapter mAdapter;
    private FragmentUserBinding mBinding;

    @Override
    protected int getLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserComponent userComponent = DaggerUserComponent.builder()
                .appComponent(SetlistsApp.getAppComponent(getContext()))
                .userModule(new UserModule(this, this))
                .build();
        userComponent.inject(this);
    }

    @Override
    public void addItems(List<Setlist> items, boolean hasMore) {

    }

    @Override
    public void clearItems() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDataState() {

    }

    @Override
    public void showEmptyState() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget) {

    }
}
