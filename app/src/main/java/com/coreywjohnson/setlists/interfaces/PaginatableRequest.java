package com.coreywjohnson.setlists.interfaces;

/**
 * Created by coreyjohnson on 12/07/16.
 */
public interface PaginatableRequest<Type> {
    void loadPage(int pageNo);

    void setListener(PaginatableRequestListener<Type> listener);

    void cancel();
}
