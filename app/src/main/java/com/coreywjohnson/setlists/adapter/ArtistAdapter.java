package com.coreywjohnson.setlists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.adapter.common.PaginatableAdapter;
import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.widgets.ArtistWidget;

import javax.inject.Inject;

/**
 * Created by corey on 20-Jun-16.
 */
public class ArtistAdapter extends PaginatableAdapter<Artists.Artist> {
    private AdapterListener mListener;

    @Inject
    public ArtistAdapter(AdapterListener listener) {
        super(listener);
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CONTENT) {
            return ArtistWidget.create(LayoutInflater.from(parent.getContext()), parent);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ArtistWidget) {
            ((ArtistWidget) holder).setArtist(mAdapterData.get(position));
            ((ArtistWidget) holder).setListener(mListener);
        }
    }

    public interface AdapterListener extends PaginatableAdapterListener {
        void onArtistClick(Artists.Artist artist);
    }
}
