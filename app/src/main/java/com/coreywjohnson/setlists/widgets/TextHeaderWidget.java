package com.coreywjohnson.setlists.widgets;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.databinding.WidgetTextHeaderBinding;

/**
 * Created by corey on 23-Aug-16.
 */
public class TextHeaderWidget extends RecyclerView.ViewHolder {
    WidgetTextHeaderBinding mBinding;

    public TextHeaderWidget(WidgetTextHeaderBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public static TextHeaderWidget create(LayoutInflater inflater, ViewGroup parent) {
        WidgetTextHeaderBinding binding = DataBindingUtil.inflate(inflater, R.layout.widget_text_header, parent, false);
        return new TextHeaderWidget(binding);
    }

    public void setText(String text) {
        mBinding.textName.setText(text);
    }
}
