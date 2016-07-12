package com.coreywjohnson.setlists.interfaces;

import java.util.List;

/**
 * Created by coreyjohnson on 12/07/16.
 */
public interface PaginatableRequestListener<Type> {
    void onSuccess(List<Type> items, int totalCount);

    void onError(String error);
}
