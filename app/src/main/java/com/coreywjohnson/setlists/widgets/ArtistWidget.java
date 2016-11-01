package com.coreywjohnson.setlists.widgets;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.adapter.ArtistAdapter;
import com.coreywjohnson.setlists.databinding.WidgetArtistBinding;
import com.coreywjohnson.setlists.models.Artist;

/**
 * Created by corey on 20-Jun-16.
 */
public class ArtistWidget extends RecyclerView.ViewHolder {
    ArtistAdapter.AdapterListener mListener;
    private WidgetArtistBinding mBinding;

    public ArtistWidget(WidgetArtistBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public static ArtistWidget create(LayoutInflater inflater, ViewGroup parent) {
        return new ArtistWidget((WidgetArtistBinding) DataBindingUtil.inflate(inflater, R.layout.widget_artist, parent, false));
    }

    public void setArtist(Artist artist) {
        mBinding.setArtist(artist);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onArtistClick(mBinding.getArtist());
                }
            }
        });
    }

    public void setListener(ArtistAdapter.AdapterListener listener) {
        mListener = listener;
    }
}
