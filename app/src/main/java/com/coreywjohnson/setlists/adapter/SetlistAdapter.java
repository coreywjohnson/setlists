package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.widgets.SetlistWidget;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 5/05/16.
 */
public class SetlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Setlists.Setlist> mAdapterData;

    @Inject
    public SetlistAdapter() {
        mAdapterData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SetlistWidget.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SetlistWidget) holder).setSetlist(mAdapterData.get(position));
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    public void addItems(List<Setlists.Setlist> setlists) {
        int rangeStart = mAdapterData.size();
        mAdapterData.addAll(setlists);
        notifyItemRangeInserted(rangeStart, setlists.size());
    }
}
