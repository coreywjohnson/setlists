package com.coreywjohnson.setlists.widgets;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.databinding.WidgetSetlistBinding;
import com.coreywjohnson.setlists.models.Setlists;

/**
 * Created by coreyjohnson on 5/05/16.
 */
public class SetlistWidget extends RecyclerView.ViewHolder {
    private WidgetSetlistBinding mBinding;

    public SetlistWidget(WidgetSetlistBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public static SetlistWidget create(LayoutInflater inflater, ViewGroup parent) {
        WidgetSetlistBinding binding = DataBindingUtil.inflate(inflater, R.layout.widget_setlist, parent, false);
        return new SetlistWidget(binding);
    }

    public void setSetlist(Setlists.Setlist setlist) {
        mBinding.setSetlist(setlist);
    }
}
