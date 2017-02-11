package com.coreywjohnson.setlists.widgets;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by corey on 08-Aug-16.
 */
public class DataStateWidget extends FrameLayout {
    public static final int DATA_VIEW_POSITION = 0;
    public static final int EMPTY_VIEW_POSITION = 1;
    public static final int LOADING_VIEW_POSITION = 2;

    public static final int CROSSFADE_DURATION = 200;

    public DataStateWidget(Context context) {
        this(context, null, 0);
    }

    public DataStateWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataStateWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onViewAdded(View child) {
        child.setVisibility(GONE);
        super.onViewAdded(child);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!hasDataAndEmptyViews()) {
            Log.i("DataStateWidget", "Data State Widget requires at least 2 children. " + getChildCount() + " were found.");
        }
    }

    public void showData() {
        showChild(DATA_VIEW_POSITION);
    }

    public void showEmpty() {
        showChild(EMPTY_VIEW_POSITION);
    }

    public void showLoading() throws Exception {
        showChild(LOADING_VIEW_POSITION);
    }

    public void setDataView(View view) {
        replaceChildAt(DATA_VIEW_POSITION, view);
    }

    public void setEmptyView(View view) {
        replaceChildAt(EMPTY_VIEW_POSITION, view);
    }

    public void setLoadingView(View view) {
        replaceChildAt(LOADING_VIEW_POSITION, view);
    }

    public void replaceChildAt(int index, View view) {
        removeViewAt(index);
        addView(view, index);
    }

    public void showChild(int index) {
        int childCount = getChildCount();
        if (index >= childCount) {
            throw new IllegalArgumentException("Invalid index provided! DataStateWidget only has " + childCount + " child views.");
        }
        for (int i = 0; i < childCount; i++) {
            if (i == index) {
                setChildVisible(getChildAt(i));
            } else {
                setChildGone(getChildAt(i));
            }
        }
    }

    public boolean hasDataAndEmptyViews() {
        return getChildCount() >= 2;
    }

    public boolean hasLoadingView() {
        return getChildCount() >= 3;
    }

    private void setChildVisible(View view) {
        view.setAlpha(0f);
        view.setVisibility(VISIBLE);
        view.animate()
                .setDuration(CROSSFADE_DURATION)
                .alpha(1f)
                .setListener(null)
                .start();
    }

    private void setChildGone(final View view) {
        view.animate()
                .setDuration(CROSSFADE_DURATION)
                .alpha(0f)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    private void setChildInvisible(final View view) {
        view.animate()
                .setDuration(CROSSFADE_DURATION)
                .alpha(0f)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    @Override
    public boolean isTransitionGroup() {
        return true;
    }
}
