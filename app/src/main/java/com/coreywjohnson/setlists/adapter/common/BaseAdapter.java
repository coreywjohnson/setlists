package com.coreywjohnson.setlists.adapter.common;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corey on 14-May-16.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_CONTENT = 1;

    protected ArrayList<T> mAdapterData;
    protected boolean mIsLoading;

    public BaseAdapter() {
        mAdapterData = new ArrayList<>();
    }

    @Override
    @CallSuper
    public int getItemViewType(int position) {
        return TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    public void addItems(List<T> data) {
        int rangeStart = mAdapterData.size();
        mAdapterData.addAll(data);
        notifyItemRangeInserted(rangeStart, mAdapterData.size());
        mIsLoading = false;
    }

    public void removeAllItems() {
        int rangeEnd = mAdapterData.size();
        mAdapterData.clear();
        notifyItemRangeRemoved(0, rangeEnd);
    }

    public interface AdapterListener {

    }
}
