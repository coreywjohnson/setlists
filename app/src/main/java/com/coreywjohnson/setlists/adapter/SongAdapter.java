package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.adapter.common.BaseAdapter;
import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.widgets.SongWidget;

import javax.inject.Inject;

/**
 * Created by corey on 14-May-16.
 */
public class SongAdapter extends BaseAdapter<Setlists.Song> {

    @Inject
    public SongAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SongWidget.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SongWidget) holder).setSong(mAdapterData.get(position));
        ((SongWidget) holder).setSongNumber(position+1);
    }
}
