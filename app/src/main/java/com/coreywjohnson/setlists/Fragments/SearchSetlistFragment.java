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

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.components.DaggerSearchSetlistComponent;
import com.coreywjohnson.setlists.components.SearchSetlistComponent;
import com.coreywjohnson.setlists.databinding.FragmentSearchBinding;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.modules.SearchSetlistModule;
import com.coreywjohnson.setlists.presenters.SearchSetlistPresenter;
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
                        .appComponent(App.getAppComponent(getContext()))
                        .searchSetlistModule(new SearchSetlistModule(this, this))
                        .build();
        searchSetlistComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        getParentView().setToolbar(mBinding.toolbar, true);

        mBinding.refreshView.setOnRefreshListener(this);

        return mBinding.getRoot();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (SearchFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void addItems(List<Setlists.Setlist> setlistList) {
        mAdapter.addItems(setlistList);
    }

    @Override
    public void onError() {
        mBinding.refreshView.setRefreshing(false);
    }

    @Override
    public void removeAllItems() {
        mAdapter.removeAllItems();
    }

    @Override
    public void hideRefresh() {
        mBinding.refreshView.setRefreshing(false);
    }

    @Override
    public MainView getParentView() {
        return (MainView) getActivity();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onSetlistClick(Setlists.Setlist setlist) {
        if (mListener != null) {
            mListener.onSetlistClick(setlist);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    public interface SearchFragmentListener {
        void onSetlistClick(Setlists.Setlist setlist);
    }
}
