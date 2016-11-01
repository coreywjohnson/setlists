package com.coreywjohnson.setlists.fragments;

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
import com.coreywjohnson.setlists.adapter.ArtistAdapter;
import com.coreywjohnson.setlists.adapter.common.BaseAdapter;
import com.coreywjohnson.setlists.components.DaggerSearchArtistComponent;
import com.coreywjohnson.setlists.components.SearchArtistComponent;
import com.coreywjohnson.setlists.databinding.FragmentSearchBinding;
import com.coreywjohnson.setlists.helpers.ViewHelper;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.modules.SearchArtistModule;
import com.coreywjohnson.setlists.presenters.SearchArtistPresenter;
import com.coreywjohnson.setlists.presenters.common.Presenter;
import com.coreywjohnson.setlists.views.MainView;
import com.coreywjohnson.setlists.views.SearchArtistView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistFragment extends BaseFragment implements SearchArtistView, SwipeRefreshLayout.OnRefreshListener, ArtistAdapter.AdapterListener {
    @Inject
    ArtistAdapter mAdapter;
    @Inject
    SearchArtistPresenter mPresenter;
    FragmentSearchBinding mBinding;

    public static SearchArtistFragment newInstance() {

        Bundle args = new Bundle();

        SearchArtistFragment fragment = new SearchArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchArtistComponent searchArtistComponent =
                DaggerSearchArtistComponent.builder()
                        .appComponent(SetlistsApp.getAppComponent(getContext()))
                        .searchArtistModule(new SearchArtistModule(this, this))
                        .build();
        searchArtistComponent.inject(this);
        if (savedInstanceState != null) {
            mPresenter.restorePresenterState((Presenter.PresenterState) savedInstanceState.getSerializable(PRESENTER_STATE));
            mAdapter.restoreAdapterState((BaseAdapter.AdapterState) savedInstanceState.getSerializable(ADAPTER_STATE));
        }
        mPresenter.onCreate(savedInstanceState != null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        getParentView().setToolbar(mBinding.toolbar, true, R.string.title_artists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.refreshView.setOnRefreshListener(this);
        mBinding.dataWidget.showData();

        return mBinding.getRoot();
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
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PRESENTER_STATE, mPresenter.getPresenterState());
        outState.putSerializable(ADAPTER_STATE, mAdapter.getAdapterState());
        super.onSaveInstanceState(outState);
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
    public void onError(String error) {
        makeTextSnackbar(error);
    }

    @Override
    public MainView getParentView() {
        return (MainView) getActivity();
    }

    @Override
    public void showArtist(Artist artist) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ArtistFragment.newInstance(artist))
                .addToBackStack("artist")
                .commit();
    }

    @Override
    public void hideKeyboard() {
        ViewHelper.hideKeyboard(mBinding.getRoot(), getContext());
    }

    @Override
    public void setAdapterHeaderSearchResult(String query) {
        mAdapter.addHeader(0, getString(R.string.txt_results_for, query));
    }

    @Override
    public void showAdapterFavoritesHeader() {
        mAdapter.addHeader(0, getString(R.string.txt_favorite_artists));
    }

    @Override
    public void onArtistClick(Artist artist) {
        mPresenter.onArtistClick(artist);
    }

    @Override
    public void addItems(List<Artist> items, boolean hasMore) {
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

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void onLoadMore() {
        mPresenter.onLoadMore();
    }
}
