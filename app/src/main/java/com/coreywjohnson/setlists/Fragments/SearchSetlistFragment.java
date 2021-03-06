package com.coreywjohnson.setlists.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.SetlistsApp;
import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.adapter.common.BaseAdapter;
import com.coreywjohnson.setlists.components.DaggerSearchSetlistComponent;
import com.coreywjohnson.setlists.components.SearchSetlistComponent;
import com.coreywjohnson.setlists.databinding.FragmentSearchBinding;
import com.coreywjohnson.setlists.helpers.ViewHelper;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.modules.SearchSetlistModule;
import com.coreywjohnson.setlists.presenters.SearchSetlistPresenter;
import com.coreywjohnson.setlists.presenters.common.Presenter;
import com.coreywjohnson.setlists.views.MainView;
import com.coreywjohnson.setlists.views.SearchSetlistView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by corey on 24-Apr-16.
 */
public class SearchSetlistFragment extends BaseFragment implements SearchSetlistView, SetlistAdapter.AdapterListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    SetlistAdapter mAdapter;
    @Inject
    SearchSetlistPresenter mPresenter;
    private FragmentSearchBinding mBinding;
    private SearchFragmentListener mListener;

    public static SearchSetlistFragment newInstance() {
        return new SearchSetlistFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchSetlistComponent searchSetlistComponent =
                DaggerSearchSetlistComponent.builder()
                        .appComponent(SetlistsApp.getAppComponent(getContext()))
                        .searchSetlistModule(new SearchSetlistModule(this, this))
                        .build();
        searchSetlistComponent.inject(this);
        mPresenter.onCreate(savedInstanceState != null);
        if(savedInstanceState != null) {
            mPresenter.restorePresenterState((Presenter.PresenterState) savedInstanceState.getSerializable(PRESENTER_STATE));
            mAdapter.restoreAdapterState((BaseAdapter.AdapterState) savedInstanceState.getSerializable(ADAPTER_STATE));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.onSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected boolean hasMenu() {
        return true;
    }

    @Override
    protected int getMenu() {
        return R.menu.menu_search;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (SearchFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.dataWidget.showData();
        getParentView().setToolbar(mBinding.toolbar, true, R.string.title_setlists);

        mBinding.refreshView.setOnRefreshListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PRESENTER_STATE, mPresenter.getPresenterState());
        outState.putSerializable(ADAPTER_STATE, mAdapter.getAdapterState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onError() {
        mBinding.refreshView.setRefreshing(false);
    }

    @Override
    public MainView getParentView() {
        return (MainView) getActivity();
    }

    @Override
    public void openSetlist(Setlist setlist, SharedViewWidget sharedViewWidget) {
        if (mListener != null) {
            mListener.onSetlistClick(setlist, sharedViewWidget);
        }
    }

    @Override
    public void hideKeyboard() {
        ViewHelper.hideKeyboard(mBinding.getRoot(), getContext());
    }

    @Override
    public void setAdapterHeaderYesterdaysSetlists() {
        mAdapter.addHeader(0, getString(R.string.txt_yesterdays_setlists));
    }

    @Override
    public void setAdapterHeaderSearchResults(String query) {
        mAdapter.addHeader(0, getString(R.string.txt_results_for, query));
    }

    @Override
    public void onLoadMore() {
        mPresenter.onLoadMore();
    }

    @Override
    public void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget) {
        mPresenter.onSetlistClick(setlist, sharedViewWidget);
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void addItems(List<Setlist> items, boolean hasMore) {
        if (mAdapter != null) {
            mAdapter.addItems(items, hasMore);
        }
    }

    @Override
    public void clearItems() {
        if (mAdapter != null) {
            mAdapter.removeAllItems();
        }
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
        if (mBinding != null) {
            mBinding.dataWidget.showData();
        }
    }

    @Override
    public void showEmptyState() {
        if (mBinding != null) {
            mBinding.dataWidget.showEmpty();
        }
    }

    public interface SearchFragmentListener {
        void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget);
    }
}
