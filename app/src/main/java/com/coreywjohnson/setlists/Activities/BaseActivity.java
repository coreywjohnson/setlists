package com.coreywjohnson.setlists.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.coreywjohnson.setlists.views.BaseView;

/**
 * Created by corey on 24-Apr-16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    public int getLayout() {
        return 0;
    }

    @Override
    public void makeTextSnackbar(String text) {
        Snackbar.make(getWindow().getDecorView(), text, Snackbar.LENGTH_SHORT).show();
    }
}
