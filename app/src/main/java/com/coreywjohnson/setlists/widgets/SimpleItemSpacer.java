package com.coreywjohnson.setlists.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by coreyjohnson on 6/05/16.
 */
public class SimpleItemSpacer extends RecyclerView.ItemDecoration {
    private int mSpacingHeight;

    public SimpleItemSpacer(int height) {
        mSpacingHeight = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mSpacingHeight;
    }
}
