package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.widgets.ArtistWidget;
import com.coreywjohnson.setlists.widgets.LoadingWidget;

import javax.inject.Inject;

/**
 * Created by corey on 20-Jun-16.
 */
public class ArtistAdapter extends PaginatableAdapter<Artists.Artist> {
    public static final int ITEM_LOADING = 1;
    public static final int ITEM_ARTIST = 2;

    private AdapterListener mListener;

    @Inject
    public ArtistAdapter(AdapterListener listener) {
        super(listener);
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mAdapterData.size()) {
            return ITEM_LOADING;
        } else {
            return ITEM_ARTIST;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_ARTIST) {
            return ArtistWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return LoadingWidget.create(LayoutInflater.from(parent.getContext()), parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ArtistWidget) {
            ((ArtistWidget) holder).setArtist(mAdapterData.get(position));
        }
    }

    public interface AdapterListener extends PaginatableAdapterListener {
        void onArtistClick(Artists.Artist artist);
    }
}
