package com.coreywjohnson.setlists.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.coreywjohnson.setlists.views.common.BaseView;

/**
 * Created by corey on 02-May-16.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasMenu()) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(getMenu(), menu);
    }

    protected boolean hasMenu() {
        return false;
    }

    protected int getMenu() {
        return 0;
    }

    protected abstract int getLayout();

    @Override
    public void makeTextSnackbar(String text) {
        if (getView() != null) {
            Snackbar.make(getView(), text, Snackbar.LENGTH_SHORT).show();
        }
    }
}
