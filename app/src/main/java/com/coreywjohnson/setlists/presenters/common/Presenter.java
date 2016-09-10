package com.coreywjohnson.setlists.presenters.common;

import java.io.Serializable;

/**
 * Created by corey on 24-Apr-16.
 */
public abstract class Presenter {

    public abstract void onCreate(boolean isRestoring);

    public static class PresenterState implements Serializable {

    }
}
