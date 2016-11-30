package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.GetUserAttendedSetlistsInteractor;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.presenters.common.PaginatablePresenter;
import com.coreywjohnson.setlists.views.UserView;

import javax.inject.Inject;

/**
 * Created by coreyjohnson on 30/11/2016.
 */

public class UserPresenter extends PaginatablePresenter<Setlist> {

    private final UserView mView;
    private final GetUserAttendedSetlistsInteractor mInteractor;
    private String mUsername;

    @Inject
    public UserPresenter(UserView view, GetUserAttendedSetlistsInteractor interactor) {
        super(view, interactor);
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onCreate(boolean isRestoring) {
        if (!isRestoring) {
            onRefresh();
        }
    }

    public void setUsername(String username) {
        mUsername = username;
        mInteractor.setUsername(username);
    }
}