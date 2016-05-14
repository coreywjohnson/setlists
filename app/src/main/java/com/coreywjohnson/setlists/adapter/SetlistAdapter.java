package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.models.Setlists;
import com.coreywjohnson.setlists.widgets.LoadingWidget;
import com.coreywjohnson.setlists.widgets.SetlistWidget;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 5/05/16.
 */
public class SetlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_SETLIST = 1;
    public static final int ITEM_LOADING = 2;
    private ArrayList<Setlists.Setlist> mAdapterData;
    private AdapterListener mListener;
    private boolean mIsLoading = false;

    @Inject
    public SetlistAdapter(AdapterListener adapterListener) {
        mAdapterData = new ArrayList<>();
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
        if (holder instanceof SetlistWidget) {
            ((SetlistWidget) holder).setSetlist(mAdapterData.get(position));
            ((SetlistWidget) holder).setListener(mListener);
        }

        if (position > mAdapterData.size() - 10 && !mIsLoading) {
            mListener.onLoadMore();
            mIsLoading = true;
        }
    }

    @Override
    public int getItemCount() {
        if (mIsLoading) {
            return mAdapterData.size() + 1;
        } else {
            return mAdapterData.size();
        }
    }

    public void addItems(List<Setlists.Setlist> setlists) {
        if (mAdapterData.size() > 0) {
            notifyItemRemoved(mAdapterData.size());
        }
        int rangeStart = mAdapterData.size();
        mAdapterData.addAll(setlists);
        notifyItemRangeInserted(rangeStart, setlists.size());
        mIsLoading = false;
    }

    public void removeAllItems() {
        int rangeEnd = mAdapterData.size();
        mAdapterData.clear();
        if (mIsLoading) {
            notifyItemRangeRemoved(0, rangeEnd + 1);
        } else {
            notifyItemRangeRemoved(0, rangeEnd);
        }
    }

    public interface AdapterListener {
        void onLoadMore();

        void onSetlistClick(Setlists.Setlist setlist);
    }
}
