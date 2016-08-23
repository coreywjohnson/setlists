package com.coreywjohnson.setlists.adapter.common;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.widgets.TextHeaderWidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by corey on 14-May-16.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_CONTENT = 1;
    public static final int TYPE_HEADER = 2;
    public static final int EMPTY = 0;

    protected ArrayList<T> mAdapterData;
    protected Map<Integer, String> mHeaders;
    protected boolean mIsLoading;

    public BaseAdapter() {
        mAdapterData = new ArrayList<>();
        mHeaders = new HashMap<>();
    }

    @Override
    @CallSuper
    public int getItemViewType(int position) {
        if(mHeaders.containsKey(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    @CallSuper
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return TextHeaderWidget.create(LayoutInflater.from(parent.getContext()), parent);
            default:
                return null;
        }
    }

    @Override
    @CallSuper
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextHeaderWidget) {
            ((TextHeaderWidget) holder).setText(mHeaders.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(mAdapterData.size() != EMPTY) {
            int validHeaders = 0;
            for (Map.Entry<Integer, String> entry : mHeaders.entrySet()) {
                if(entry.getKey() < mAdapterData.size()) {
                    validHeaders++;
                }
            }
            return mAdapterData.size() + validHeaders;
        } else {
            return EMPTY;
        }
    }

    public T getItemAtPosition(int position) {
        int offset = 0;
        for (Map.Entry<Integer, String> entry : mHeaders.entrySet()) {
            if(entry.getKey() < position) {
                offset--;
            }
        }
        return mAdapterData.get(position + offset);
    }

    public void addItems(List<T> data) {
        int rangeStart = getItemCount();
        mAdapterData.addAll(data);
        notifyItemRangeInserted(rangeStart, getItemCount());
        mIsLoading = false;
    }

    public void removeAllItems() {
        int rangeEnd = getItemCount();
        mAdapterData.clear();
        notifyItemRangeRemoved(0, rangeEnd);
    }

    public void addHeader(int position, String text) {
        mHeaders.remove(position);
        mHeaders.put(position, text);
    }

    public void removeHeader(int position) {
        mHeaders.remove(position);
    }

    public interface AdapterListener {

    }
}
