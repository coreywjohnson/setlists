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
        if (viewType == TYPE_CONTENT) {
            return SongWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof SongWidget) {
            ((SongWidget) holder).setSong(getItemAtPosition(position));
            ((SongWidget) holder).setSongNumber(position + 1);
        }
    }
}
