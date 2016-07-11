package com.coreywjohnson.setlists.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.components.ArtistComponent;
import com.coreywjohnson.setlists.components.DaggerArtistComponent;
import com.coreywjohnson.setlists.databinding.FragmentArtistBinding;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.modules.ArtistModule;
import com.coreywjohnson.setlists.presenters.ArtistPresenter;
import com.coreywjohnson.setlists.views.ArtistView;
import com.coreywjohnson.setlists.views.MainView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by corey on 03-Jul-16.
 */
public class ArtistFragment extends BaseFragment implements ArtistView, SetlistAdapter.AdapterListener {
    public static final String ARTIST = "artist";
    FragmentArtistBinding mBinding;
    @Inject
    ArtistPresenter mPresenter;
    @Inject
    SetlistAdapter mAdapter;

    public static ArtistFragment newInstance(Artists.Artist artist) {

        Bundle args = new Bundle();
        args.putSerializable(ARTIST, artist);

        ArtistFragment fragment = new ArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArtistComponent artistComponent = DaggerArtistComponent.builder()
                .appComponent(App.getAppComponent(getContext()))
                .artistModule(new ArtistModule(this, this))
                .build();
        artistComponent.inject(this);
        setHasOptionsMenu(true);

        mPresenter.onCreate((Artists.Artist) getArguments().getSerializable(ARTIST));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist, container, false);
        ((MainView) getActivity()).setToolbar(mBinding.toolbar, false, ((Artists.Artist) getArguments().getSerializable(ARTIST)).getName());

        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mBinding.refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected boolean hasMenu() {
        return true;
    }

    @Override
    protected int getMenu() {
        return R.menu.menu_artist;
    }

    @Override
    public void onSetlistClick(Setlists.Setlist setlist) {
        mPresenter.onSetlistClick(setlist);
    }

    @Override
    public void onLoadMore() {
        mPresenter.onLoadMore();
    }

    @Override
    public void addItems(List<Setlists.Setlist> items, boolean hasMore) {
        mAdapter.addItems(items);
        if (!hasMore) {
            mAdapter.notifyNoMoreItems();
        }
    }

    @Override
    public void clearItems() {
        mAdapter.removeAllItems();
        mAdapter.setHasMoreItems(true);
    }

    @Override
    public void showLoading() {
        mBinding.refreshView.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mBinding.refreshView.setRefreshing(false);
    }

    @Override
    public void openSetlist(Setlists.Setlist setlist) {
        SetlistFragment setlistFragment = SetlistFragment.newInstance(setlist);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, setlistFragment)
                .addToBackStack("Setlist")
                .commit();
    }
}
