package com.coreywjohnson.setlists.widgets;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coreywjohnson.setlists.R;
import com.coreywjohnson.setlists.adapter.SetlistAdapter;
import com.coreywjohnson.setlists.databinding.WidgetSetlistBinding;
import com.coreywjohnson.setlists.interfaces.SharedViewWidget;
import com.coreywjohnson.setlists.models.Setlists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by coreyjohnson on 5/05/16.
 */
public class SetlistWidget extends RecyclerView.ViewHolder implements SharedViewWidget {
    public static final String TRANSITION_NAME = "setlistTransition";

    private WidgetSetlistBinding mBinding;
    private SetlistAdapter.AdapterListener mListener;

    public SetlistWidget(WidgetSetlistBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public static SetlistWidget create(LayoutInflater inflater, ViewGroup parent) {
        WidgetSetlistBinding binding = DataBindingUtil.inflate(inflater, R.layout.widget_setlist, parent, false);
        return new SetlistWidget(binding);
    }

    public void setSetlist(final Setlists.Setlist setlist) {
        mBinding.setSetlist(setlist);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = simpleDateFormat.parse(setlist.getEventDate());
            mBinding.dateDay.setText(new SimpleDateFormat("d").format(date));
            mBinding.dateMonth.setText(new SimpleDateFormat("MMM ''yy").format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mBinding.dateCircle.setTransitionName(TRANSITION_NAME);
                    }
                    mListener.onSetlistClick(setlist, SetlistWidget.this);
                }
            }
        });
    }

    public void setListener(SetlistAdapter.AdapterListener adapterListener) {
        mListener = adapterListener;
    }

    @Override
    public View getSharedView() {
        return mBinding.dateCircle;
    }

    @Override
    public String getSharedViewName() {
        return TRANSITION_NAME;
    }
}
