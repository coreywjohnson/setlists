package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.views.PaginatableView;

import java.util.List;

/**
 * Created by corey on 11-Jul-16.
 */
public abstract class PaginatablePresenter<Type> extends Presenter implements PaginatableRequestListener<Type> {
    protected int mPageNo = -1;
    protected int mLoadCount = -1;
    private PaginatableRequest<Type> mRequest;
    private PaginatableView<Type> mPaginatableView;

    public PaginatablePresenter(PaginatableView<Type> view, PaginatableRequest<Type> request) {
        mPaginatableView = view;
        mRequest = request;
        mRequest.setListener(this);
    }

    public void firstLoad() {
        mPaginatableView.clearItems();
        mPageNo = 1;
        mLoadCount = 0;
        mRequest.loadPage(mPageNo);
    }

    public void onLoadMore() {
        mRequest.loadPage(mPageNo++);
    }

    public void onRefresh() {
        firstLoad();
    }

    @Override
    public void onSuccess(List<Type> items, int totalCount) {
        mLoadCount += items.size();
        mPaginatableView.hideLoading();
        mPaginatableView.addItems(items, mLoadCount < totalCount);
    }
}
