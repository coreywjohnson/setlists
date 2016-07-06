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

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.adapter.ArtistAdapter;
import com.coreywjohnson.setlists.components.DaggerSearchArtistComponent;
import com.coreywjohnson.setlists.components.SearchArtistComponent;
import com.coreywjohnson.setlists.databinding.FragmentSearchBinding;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.modules.SearchArtistModule;
import com.coreywjohnson.setlists.presenters.SearchArtistPresenter;
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
                        .appComponent(App.getAppComponent(getContext()))
                        .searchArtistModule(new SearchArtistModule(this, this))
                        .build();
        searchArtistComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        getParentView().setToolbar(mBinding.toolbar, true, null);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.refreshView.setOnRefreshListener(this);

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
    public void addItems(List<Artists.Artist> artists) {
        mAdapter.addItems(artists);
    }

    @Override
    public void removeAllItems() {
        mAdapter.removeAllItems();
    }

    @Override
    public void notifyNoMoreItems() {
        mAdapter.notifyNoMoreItems();
    }

    @Override
    public void hideRefresh() {
        mBinding.refreshView.setRefreshing(false);
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
    public void showArtist(Artists.Artist artist) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ArtistFragment.newInstance(artist))
                .addToBackStack("artist")
                .commit();
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onArtistClick(Artists.Artist artist) {
        mPresenter.onArtistClick(artist);
    }
}