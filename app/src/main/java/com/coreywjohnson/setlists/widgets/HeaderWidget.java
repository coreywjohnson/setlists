package com.coreywjohnson.setlists.widgets;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.coreywjohnson.setlists.R;

/**
 * Created by coreyjohnson on 28/07/16.
 */
public class HeaderWidget extends LinearLayout {
    public HeaderWidget(Context context) {
        super(context);
        init(context, null, 0);
    }

    public HeaderWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public HeaderWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DataBindingUtil.inflate(inflater, R.layout.widget_header, this, true);
    }
}
