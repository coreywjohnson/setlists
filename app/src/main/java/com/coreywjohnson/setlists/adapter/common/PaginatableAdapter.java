package com.coreywjohnson.setlists.adapter.common;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.widgets.LoadingWidget;

import java.util.List;

/**
 * Created by corey on 14-May-16.
 */
public abstract class PaginatableAdapter<T> extends BaseAdapter<T> {
    public static final int TYPE_LOADING = 2;

    public static final int LOAD_OFFSET = 10;
    protected PaginatableAdapterListener mPaginatableAdapterListener;
    private boolean mHasMoreItems = true;

    public PaginatableAdapter(PaginatableAdapterListener adapterListener) {
        mPaginatableAdapterListener = adapterListener;
    }

    @Override
    @CallSuper
    public int getItemViewType(int position) {
        if (position == mAdapterData.size()) {
            return TYPE_LOADING;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    @CallSuper
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING) {
            return LoadingWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return null;
        }
    }

    @Override
    @CallSuper
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > mAdapterData.size() - LOAD_OFFSET && !mIsLoading && mHasMoreItems) {
            mPaginatableAdapterListener.onLoadMore();
            mIsLoading = true;
        }
    }

    @Override
    public int getItemCount() {
        if (mIsLoading) {
            return super.getItemCount() + 1;
        } else {
            return super.getItemCount();
        }
    }

    public void addItems(List<T> data, boolean hasMore) {
        mHasMoreItems = hasMore;
        addItems(data);
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

    public interface PaginatableAdapterListener extends AdapterListener {
        void onLoadMore();
    }
}
