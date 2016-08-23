package com.coreywjohnson.setlists.views.common;

import java.util.List;

/**
 * Created by corey on 11-Jul-16.
 */
public interface PaginatableView<Type> extends BaseView {
    void addItems(List<Type> items, boolean hasMore);

    void clearItems();

    void showLoading();

    void hideLoading();

    void showDataState();

    void showEmptyState();
}
