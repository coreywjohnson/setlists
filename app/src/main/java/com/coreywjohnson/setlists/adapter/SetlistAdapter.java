package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.adapter.common.PaginatableAdapter;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.widgets.SetlistWidget;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 5/05/16.
 */
public class SetlistAdapter extends PaginatableAdapter<Setlist> {
    private AdapterListener mListener;

    @Inject
    public SetlistAdapter(AdapterListener adapterListener) {
        super(adapterListener);
        mListener = adapterListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CONTENT) {
            return SetlistWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof SetlistWidget) {
            ((SetlistWidget) holder).setSetlist(getItemAtPosition(position));
            ((SetlistWidget) holder).setListener(mListener);
        }
    }

    public interface AdapterListener extends PaginatableAdapterListener {
        void onSetlistClick(Setlist setlist, SharedViewWidget sharedViewWidget);
    }
}
