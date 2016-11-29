package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.adapter.common.BaseAdapter;
import com.coreywjohnson.setlists.models.Set;
import com.coreywjohnson.setlists.models.Song;
import com.coreywjohnson.setlists.widgets.SongWidget;

import javax.inject.Inject;

/**
 * Created by corey on 14-May-16.
 */
public class SongAdapter extends BaseAdapter<Song> {

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
            ((SongWidget) holder).setSongNumber(getSongPosition(getItemAtPosition(position)) + 1);
        }
    }

    private int getSongPosition(Song item) {
        int position = 0;
        for (Song song : mAdapterData) {
            if (item == song) {
                return position;
            }
            if (!song.isTape()) {
                position++;
            }
        }
        return -1;
    }

    public void addSet(Set set) {
        addItems(set.getSong());

        // add header
        if (set.getName() != null && !set.getName().isEmpty()) {
            addHeader(getItemCount() - set.getSong().size(), set.getName());
        } else if (set.getEncore() != null && set.getEncore().isEmpty()) {
            addHeader(getItemCount() - set.getSong().size(), set.getEncore());
        } else {
            if (mHeaders.size() > 0) {
                addHeader(getItemCount() - set.getSong().size(), String.format("Encore %d", mHeaders.size() + 1));
            } else if (getItemCount() - set.getSong().size() != 0) {
                addHeader(getItemCount() - set.getSong().size(), "Encore");
            }
        }
    }
}
