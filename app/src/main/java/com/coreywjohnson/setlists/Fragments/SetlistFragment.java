package com.coreywjohnson.setlists.fragments;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.adapter.SongAdapter;
import com.coreywjohnson.setlists.components.DaggerSetlistComponent;
import com.coreywjohnson.setlists.components.SetlistComponent;
import com.coreywjohnson.setlists.databinding.FragmentSetlistBinding;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.modules.SetlistModule;
import com.coreywjohnson.setlists.presenters.SetlistPresenter;
import com.coreywjohnson.setlists.views.SetlistView;

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

        mPresenter.displaySetlist((Setlists.Setlist)getArguments().getSerializable(SETLIST));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.setSetlist((Setlists.Setlist) getArguments().getSerializable(SETLIST));
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mBinding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (verticalOffset == 0) {
                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    } else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    }
                }
            });
        }

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setlist;
    }

    @Override
    public void addItems(List<Setlists.Song> setlistList) {
        mAdapter.addItems(setlistList);
    }
}
