package com.coreywjohnson.setlists.presenters;

/**
 * Created by corey on 11-Jul-16.
 */
public abstract class PaginatablePresenter<Type> extends Presenter {
    protected int mPageNo = -1;
    protected int mLoadCount = -1;

    public void onLoadMore() {
        mPageNo++;
    }

    public void onRefresh() {
        mPageNo = 1;
        mLoadCount = -1;
    }
}
