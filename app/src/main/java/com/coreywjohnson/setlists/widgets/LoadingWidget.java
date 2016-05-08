package com.coreywjohnson.setlists.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;

/**
 * Created by corey on 08-May-16.
 */
public class LoadingWidget extends RecyclerView.ViewHolder {
    public LoadingWidget(View itemView) {
        super(itemView);
    }

    public static LoadingWidget create(LayoutInflater inflater, ViewGroup parent) {
        return new LoadingWidget(inflater.inflate(R.layout.widget_loading, parent, false));
    }
}
