package com.coreywjohnson.setlists.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.coreywjohnson.setlists.views.MainView;
import com.coreywjohnson.setlists.views.UserView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 30/11/2016.
 */

public class UserFragment extends BaseFragment implements UserView, SetlistAdapter.AdapterListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String ARGUMENT_USERNAME = "argument_username";

    @Inject
    UserPresenter mPresenter;
    @Inject
    SetlistAdapter mAdapter;
    private FragmentUserBinding mBinding;

    public static UserFragment newInstance(String username) {

        Bundle args = new Bundle();

        args.putString(ARGUMENT_USERNAME, username);

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

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

        mPresenter.setUsername(getArguments().getString(ARGUMENT_USERNAME));
        mPresenter.onCreate(savedInstanceState != null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.dataWidget.showData();
        getParentView().setToolbar(mBinding.toolbar, true, getArguments().getString(ARGUMENT_USERNAME));

        mBinding.refreshView.setOnRefreshListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void addItems(List<Setlist> items, boolean hasMore) {
        mAdapter.addItems(items, hasMore);
    }

    @Override
    public void clearItems() {
        mAdapter.removeAllItems();
    }

    @Override
    public void showLoading() {
        if (mBinding != null) {
            mBinding.refreshView.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (mBinding != null) {
            mBinding.refreshView.setRefreshing(false);
        }
    }

    @Override
    public void showDataState() {
        if (mBinding.dataWidget != null) {
            mBinding.dataWidget.showData();
        }
    }

    @Override
    public void showEmptyState() {
        if (mBinding != null) {
            mBinding.dataWidget.showData();
        }
    }

    @Override
    public void onLoadMore() {
        mPresenter.onLoadMore();
    }

    @Override
    public void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget) {
        // TODO do something
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    public MainView getParentView() {
        return ((MainView) getActivity());
    }
}
