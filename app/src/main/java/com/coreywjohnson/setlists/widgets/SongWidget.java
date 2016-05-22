package com.coreywjohnson.setlists.widgets;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.databinding.WidgetSongBinding;
import com.coreywjohnson.setlists.models.Setlists;

/**
 * Created by corey on 14-May-16.
 */
public class SongWidget extends RecyclerView.ViewHolder {
    private WidgetSongBinding mBinding;

    public SongWidget(WidgetSongBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public static SongWidget create(LayoutInflater inflater, ViewGroup parent) {
       WidgetSongBinding binding = DataBindingUtil.inflate(inflater, R.layout.widget_song, parent, false);
        return new SongWidget(binding);
    }

    public void setSong(Setlists.Song song) {
        mBinding.setSong(song);
        String infoText = "";
        if (song.getCover() != null) {
            infoText += "(" + song.getCover().getName() + " cover) ";
        }
        if (song.getInfo() != null) {
            infoText += "(" + song.getInfo() + ")";
        }
        if (infoText.equals("")) {
            mBinding.textInfo.setVisibility(View.GONE);
        } else {
            mBinding.textInfo.setVisibility(View.VISIBLE);
            mBinding.textInfo.setText(infoText);
        }
    }

    public void setSongNumber(int position) {
        mBinding.setSongNo(position);
    }
}
