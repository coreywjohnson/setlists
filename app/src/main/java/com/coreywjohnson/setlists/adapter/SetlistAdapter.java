package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.widgets.LoadingWidget;
import com.coreywjohnson.setlists.widgets.SetlistWidget;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 5/05/16.
 */
public class SetlistAdapter extends PaginatableAdapter<Setlists.Setlist> {
    public static final int ITEM_SETLIST = 1;
    public static final int ITEM_LOADING = 2;
    private AdapterListener mListener;

    @Inject
    public SetlistAdapter(AdapterListener adapterListener) {
        super(adapterListener);
        mListener = adapterListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mAdapterData.size()) {
            return ITEM_LOADING;
        } else {
            return ITEM_SETLIST;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_SETLIST) {
            return SetlistWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return LoadingWidget.create(LayoutInflater.from(parent.getContext()), parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof SetlistWidget) {
            ((SetlistWidget) holder).setSetlist(mAdapterData.get(position));
            ((SetlistWidget) holder).setListener(mListener);
        }
    }

    public interface AdapterListener extends PaginatableAdapterListener {
        void onSetlistClick(Setlists.Setlist setlist);
    }
}
