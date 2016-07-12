package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by corey on 14-May-16.
 */
public abstract class PaginatableAdapter<T> extends BaseAdapter<T> {
    public static final int LOAD_OFFSET = 10;
    protected PaginatableAdapterListener mPaginatableAdapterListener;
    private boolean mHasMoreItems = true;

    public PaginatableAdapter(PaginatableAdapterListener adapterListener) {
        mPaginatableAdapterListener = adapterListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > mAdapterData.size() - LOAD_OFFSET && !mIsLoading && mHasMoreItems) {
            mPaginatableAdapterListener.onLoadMore();
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

    @Override
    public void addItems(List<T> data) {
        // Remove Loader
        if (mAdapterData.size() > 0) {
            notifyItemRemoved(mAdapterData.size());
        }
        super.addItems(data);
    }

    @Override
    public void removeAllItems() {
        int rangeEnd = mAdapterData.size();
        mAdapterData.clear();
        if (mIsLoading) {
            notifyItemRangeRemoved(0, rangeEnd + 1);
        } else {
            notifyItemRangeRemoved(0, rangeEnd);
        }
        mHasMoreItems = true;
    }

    public void notifyNoMoreItems() {
        if (mIsLoading) {
            notifyItemRemoved(mAdapterData.size());
            mIsLoading = false;
        }
        mHasMoreItems = false;
    }

    public interface PaginatableAdapterListener extends AdapterListener {
        void onLoadMore();
    }
}
