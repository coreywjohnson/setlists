package com.coreywjohnson.setlists.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.App;
import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.components.ArtistComponent;
import com.coreywjohnson.setlists.components.DaggerArtistComponent;
import com.coreywjohnson.setlists.databinding.FragmentArtistBinding;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.modules.ArtistModule;
import com.coreywjohnson.setlists.presenters.ArtistPresenter;
import com.coreywjohnson.setlists.views.ArtistView;
import com.coreywjohnson.setlists.views.MainView;

import javax.inject.Inject;

/**
 * Created by corey on 03-Jul-16.
 */
public class ArtistFragment extends BaseFragment implements ArtistView {
    public static final String ARTIST = "artist";
    FragmentArtistBinding mBinding;
    @Inject
    ArtistPresenter mPresenter;

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
                .artistModule(new ArtistModule(this))
                .build();
        artistComponent.inject(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist, container, false);
        ((MainView) getActivity()).setToolbar(mBinding.toolbar, false, ((Artists.Artist) getArguments().getSerializable(ARTIST)).getName());
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
}
