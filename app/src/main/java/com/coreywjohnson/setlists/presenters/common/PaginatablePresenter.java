package com.coreywjohnson.setlists.presenters.common;

import android.support.annotation.CallSuper;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.views.common.PaginatableView;

import java.util.List;

/**
 * Created by corey on 11-Jul-16.
 */
public abstract class PaginatablePresenter<Type> extends Presenter implements PaginatableRequestListener<Type> {
    protected int mPageNo = 1;
    protected int mLoadCount = 0;
    private PaginatableRequest<Type> mRequest;
    private PaginatableView<Type> mPaginatableView;

    public PaginatablePresenter(PaginatableView<Type> view, PaginatableRequest<Type> request) {
        mPaginatableView = view;
        mRequest = request;
        mRequest.setListener(this);
    }

    public void onLoadMore() {
        mRequest.loadPage(++mPageNo);
    }

    public void onRefresh() {
        mPaginatableView.clearItems();
        mPageNo = 1;
        mLoadCount = 0;
        mRequest.loadPage(mPageNo);
    }

    @Override
    public void onSuccess(List<Type> items, int totalCount) {
        if (mLoadCount == 0) {
            mPaginatableView.showDataState();
        }
        mLoadCount += items.size();
        mPaginatableView.hideLoading();
        mPaginatableView.addItems(items, mLoadCount < totalCount);
    }

    @Override
    public void onError(String error) {
        if (error.equals(SetlistService.NOT_FOUND_MESSAGE)) {
            mPaginatableView.showEmptyState();
        }
        mPaginatableView.hideLoading();
    }

    public PresenterState getPresenterState() {
        PaginatablePresenterState state = new PaginatablePresenterState();
        state.writeState(this);
        return state;
    }

    @CallSuper
    public void restorePresenterState(PresenterState state) {
        mLoadCount = ((PaginatablePresenterState) state).loadCount;
        mPageNo = ((PaginatablePresenterState) state).pageNo;
    }

    public static class PaginatablePresenterState extends PresenterState {
        public int pageNo;
        public int loadCount;

        public void writeState(PaginatablePresenter paginatablePresenter) {
            pageNo = paginatablePresenter.mPageNo;
            loadCount = paginatablePresenter.mLoadCount;
        }
    }
}
