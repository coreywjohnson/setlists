package com.coreywjohnson.setlists.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.SetlistsApp;
import com.coreywjohnson.setlists.adapter.SongAdapter;
import com.coreywjohnson.setlists.components.DaggerSetlistComponent;
import com.coreywjohnson.setlists.components.SetlistComponent;
import com.coreywjohnson.setlists.databinding.FragmentSetlistBinding;
import com.coreywjohnson.setlists.models.Set;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.models.Song;
import com.coreywjohnson.setlists.modules.SetlistModule;
import com.coreywjohnson.setlists.presenters.SetlistPresenter;
import com.coreywjohnson.setlists.utils.ViewUtils;
import com.coreywjohnson.setlists.views.MainView;
import com.coreywjohnson.setlists.views.SetlistView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 12/05/16.
 */
public class SetlistFragment extends BaseFragment implements SetlistView {
    public static final String SETLIST = "Setlist";

    @Inject
    SetlistPresenter mPresenter;

    @Inject
    SongAdapter mAdapter;
    FragmentSetlistBinding mBinding;

    public static SetlistFragment newInstance(Setlist setlist) {

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
                .appComponent(SetlistsApp.getAppComponent(getContext()))
                .setlistModule(new SetlistModule(this))
                .build();
        setlistComponent.inject(this);
        mPresenter.setSetlist((Setlist) getArguments().getSerializable(SETLIST));
    }

    @Override
    protected boolean hasMenu() {
        return true;
    }

    @Override
    protected int getMenu() {
        return R.menu.menu_setlist;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setlist;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        mPresenter.onCreateView(savedInstanceState != null, android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP);
        ((MainView) getActivity()).setToolbar(mBinding.toolbar, false, null);

        Picasso.with(getContext())
                .load(R.mipmap.crowd)
                .fit()
                .centerCrop()
                .into(mBinding.backgroundImage);

        return mBinding.getRoot();
    }

    @Override
    public void setupRecyclerView(Setlist setlist) {
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.setSetlist((Setlist) getArguments().getSerializable(SETLIST));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // if we are not transitioning
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mBinding.backgroundImage.setImageDrawable(null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.action_web:
                mPresenter.onViewWebPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void revealToolbar() {
        ViewUtils.circularReveal(mBinding.toolbarContent, getContext());
    }

    @Override
    public void addItems(List<Song> setlistList) {
        if (mAdapter != null) {
            mAdapter.addItems(setlistList);
        }
    }

    @Override
    public void displayEmptyState() {
        mBinding.dataWidget.showEmpty();
    }

    @Override
    public void displayDataState() {
        mBinding.dataWidget.showData();
    }

    @Override
    public void showHeader() {
        mBinding.toolbarContent.setVisibility(View.VISIBLE);
        mBinding.dateCircle.setVisibility(View.GONE);
    }

    @Override
    public void launchWebView(Setlist setlist) {
        String webUrl = setlist.getUrl();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
        startActivity(browserIntent);
    }

    @Override
    public void addEncoreHeader(int position) {
        mAdapter.addHeader(position, getContext().getString(R.string.txt_encore));
    }

    @Override
    public void addEncoreNumHeader(int encoreNum, int position) {
        mAdapter.addHeader(position, getContext().getString(R.string.txt_encore_num, encoreNum));
    }

    @Override
    public void addEncoreNameHeader(String name, int position) {
        mAdapter.addHeader(position, name);
    }

    @Override
    public void addSet(Set set) {
        mAdapter.addSet(set);
    }
}
