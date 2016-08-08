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
        setChildVisible(getChildAt(DATA_VIEW_POSITION));
        setChildGone(getChildAt(EMPTY_VIEW_POSITION));
        if (hasLoadingView()) {
            setChildGone(getChildAt(LOADING_VIEW_POSITION));
        }
    }

    public void showEmpty() {
        setChildGone(getChildAt(DATA_VIEW_POSITION));
        setChildVisible(getChildAt(EMPTY_VIEW_POSITION));
        if (hasLoadingView()) {
            setChildGone(getChildAt(LOADING_VIEW_POSITION));
        }
    }

    public void showLoading() throws Exception {
        if (!hasLoadingView()) {
            throw new Exception("Loading view has not been provided. Please provide a third view!");
        }
        setChildGone(getChildAt(DATA_VIEW_POSITION));
        setChildGone(getChildAt(EMPTY_VIEW_POSITION));
        setChildVisible(getChildAt(LOADING_VIEW_POSITION));
    }

    public void setDataView(View view) {
        removeViewAt(DATA_VIEW_POSITION);
        addView(view, DATA_VIEW_POSITION);
    }

    public void setEmptyView(View view) {
        removeViewAt(EMPTY_VIEW_POSITION);
        addView(view, EMPTY_VIEW_POSITION);
    }

    public void setLoadingView(View view) {
        removeViewAt(LOADING_VIEW_POSITION);
        addView(view, LOADING_VIEW_POSITION);
    }

    private boolean hasDataAndEmptyViews() {
        return getChildCount() >= 2;
    }

    private boolean hasLoadingView() {
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
}
