package com.coreywjohnson.setlists;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.presenters.common.Presenter;
import com.coreywjohnson.setlists.views.common.PaginatableView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by corey on 16-Aug-16.
 */
public class PaginatablePresenterTests {
    PaginatablePresenter<Setlist> mPresenter;
    @Mock
    PaginatableView<Setlist> mView;
    @Mock
    PaginatableRequest<Setlist> mRequest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new PaginatablePresenter<Setlist>(mView, mRequest) {
            @Override
            public void onCreate(boolean isRestoring) {

            }
        };
    }

    @Test
    public void testOnLoadMore_makesRequest() {
        mPresenter.onLoadMore();
        verify(mRequest).loadPage(anyInt());
    }

    @Test
    public void testOnLoadMore_withMultipleInvocations_pageNoIncrements() {
        mPresenter.onLoadMore();
        verify(mRequest).loadPage(2);
        mPresenter.onLoadMore();
        verify(mRequest).loadPage(3);
    }

    @Test
    public void testRefresh_clearDataStartLoading() {
        mPresenter.onRefresh();
        verify(mView).clearItems();
        verify(mRequest).loadPage(1);
    }

    @Test
    public void testOnSuccess_withLoadCountZero_viewShowData() {
        ArrayList<Setlist> setlists = new ArrayList<>();
        setlists.add(new Setlist());
        mPresenter.onSuccess(setlists, 1);
        verify(mView).showDataState();
    }

    @Test
    public void testOnSuccess_withLoadCountNotZero_viewShowDataOnce() {
        ArrayList<Setlist> setlists = new ArrayList<>();
        setlists.add(new Setlist());
        mPresenter.onSuccess(setlists, 2);
        mPresenter.onSuccess(setlists, 2);
        verify(mView, times(1)).showDataState();
    }

    @Test
    public void testOnSuccess_hideLoading() {
        ArrayList<Setlist> setlists = new ArrayList<>();
        setlists.add(new Setlist());
        mPresenter.onSuccess(setlists, 2);
        verify(mView).hideLoading();
    }

    @Test
    public void testOnSuccess_withMoreData_notifyView() {
        ArrayList<Setlist> setlists = new ArrayList<>();
        setlists.add(new Setlist());
        mPresenter.onSuccess(setlists, 2);
        verify(mView).addItems(setlists, true);
    }

    @Test
    public void testOnSuccess_withoutMoreData_notifyView() {
        ArrayList<Setlist> setlists = new ArrayList<>();
        setlists.add(new Setlist());
        mPresenter.onSuccess(setlists, 1);
        verify(mView).addItems(setlists, false);
    }

    @Test
    public void testOnError_errorNotFound_showEmptyState() {
        mPresenter.onError(SetlistService.NOT_FOUND_MESSAGE);
        verify(mView).showEmptyState();
    }

    @Test
    public void testOnError_errorOther_dontShowEmptyState() {
        mPresenter.onError("some other error");
        verify(mView, never()).showEmptyState();
    }

    @Test
    public void testOnError_hideLoading() {
        mPresenter.onError("some error");
        verify(mView).hideLoading();
    }

    @Test
    public void testRestoreState_restoresPageNoProperly() {
        int pageNo = 3;
        PaginatablePresenter.PaginatablePresenterState state = new PaginatablePresenter.PaginatablePresenterState();
        state.pageNo = pageNo;
        state.loadCount = 5000;

        mPresenter.restorePresenterState(state);
        mPresenter.onLoadMore();
        verify(mRequest).loadPage(pageNo + 1);
    }

    @Test
    public void testRestoreState_restoresLoadCountProperly() {
        int totalLoadCount = 50;
        int requestItemCount = 5;

        PaginatablePresenter.PaginatablePresenterState state = new PaginatablePresenter.PaginatablePresenterState();
        state.pageNo = 3;
        state.loadCount = totalLoadCount - requestItemCount;

        ArrayList<Setlist> setlists = new ArrayList<>();
        for (int i = 0; i < requestItemCount; i++) {
            setlists.add(new Setlist());
        }

        mPresenter.restorePresenterState(state);
        mPresenter.onSuccess(setlists, totalLoadCount);
        verify(mView).addItems(setlists, false);
    }

    @Test
    public void testGetState_getsCorrectState() {
        int itemCount = 5;
        ArrayList<Setlist> setlists = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            setlists.add(new Setlist());
        }

        mPresenter.onLoadMore();
        mPresenter.onSuccess(setlists, 10);
        Presenter.PresenterState presenterState = mPresenter.getPresenterState();
        assertEquals(5, ((PaginatablePresenter.PaginatablePresenterState) presenterState).loadCount);
        assertEquals(2, ((PaginatablePresenter.PaginatablePresenterState) presenterState).pageNo);
    }
}