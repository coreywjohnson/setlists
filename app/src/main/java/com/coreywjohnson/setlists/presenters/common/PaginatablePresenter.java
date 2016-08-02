package com.coreywjohnson.setlists.presenters.common;

import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.views.common.PaginatableView;

import java.util.List;

/**
 * Created by corey on 11-Jul-16.
 */
public abstract class PaginatablePresenter<Type> extends Presenter implements PaginatableRequestListener<Type> {
    protected int mPageNo = 0;
    protected int mLoadCount = 0;
    private PaginatableRequest<Type> mRequest;
    private PaginatableView<Type> mPaginatableView;

    public PaginatablePresenter(PaginatableView<Type> view, PaginatableRequest<Type> request) {
        mPaginatableView = view;
        mRequest = request;
        mRequest.setListener(this);
    }

    public void onLoadMore() {
        mRequest.loadPage(mPageNo++);
    }

    public void onRefresh() {
        mPaginatableView.clearItems();
        mPageNo = 1;
        mLoadCount = 0;
        mRequest.loadPage(mPageNo);
    }

    @Override
    public void onSuccess(List<Type> items, int totalCount) {
        mLoadCount += items.size();
        mPaginatableView.hideLoading();
        mPaginatableView.addItems(items, mLoadCount < totalCount);
    }
}
