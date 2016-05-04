package com.coreywjohnson.setlists.presenters;

/**
 * Created by corey on 24-Apr-16.
 */
public class Presenter<T> {
    T mView;

    public void init(T view) {
        mView = view;
    }
}
