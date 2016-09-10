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
    public static final int TYPE_LOADING = 3;
    public static final int LOAD_OFFSET = 10;
    protected boolean mHasMoreItems = true;
    private PaginatableAdapterListener mPaginatableAdapterListener;

    public PaginatableAdapter(PaginatableAdapterListener adapterListener) {
        mPaginatableAdapterListener = adapterListener;
    }

    @Override
    @CallSuper
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING) {
            return LoadingWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    @CallSuper
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position > super.getItemCount() - LOAD_OFFSET && !mIsLoading && mHasMoreItems) {
            mPaginatableAdapterListener.onLoadMore();
            mIsLoading = true;
        }
    }

    @Override
    @CallSuper
    public int getItemViewType(int position) {
        if (position == super.getItemCount()) {
            return TYPE_LOADING;
        } else {
            return super.getItemViewType(position);
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

    @Override
    public void addItems(List<T> data) {
        // Remove Loader
        if (mAdapterData.size() > 0) {
            notifyItemRemoved(super.getItemCount());
        }
        super.addItems(data);
    }

    @Override
    public void removeAllItems() {
        int rangeEnd = super.getItemCount();
        mAdapterData.clear();
        if (mIsLoading) {
            notifyItemRangeRemoved(0, rangeEnd + 1);
        } else {
            notifyItemRangeRemoved(0, rangeEnd);
        }
        mHasMoreItems = true;
    }

    @Override
    public AdapterState getAdapterState() {
        PaginatableAdapterState adapterState = new PaginatableAdapterState();
        adapterState.writeState(this);
        return adapterState;
    }

    @Override
    public void restoreAdapterState(AdapterState adapterState) {
        super.restoreAdapterState(adapterState);
        mHasMoreItems = ((PaginatableAdapterState) adapterState).hasMoreItems;
    }

    public void addItems(List<T> data, boolean hasMore) {
        mHasMoreItems = hasMore;
        addItems(data);
    }

    public interface PaginatableAdapterListener extends AdapterListener {
        void onLoadMore();
    }

    public static class PaginatableAdapterState extends AdapterState {
        boolean hasMoreItems;

        public void writeState(PaginatableAdapter adapter) {
            super.writeState(adapter);
            hasMoreItems = adapter.mHasMoreItems;
        }
    }
}
