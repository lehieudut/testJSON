package com.example.lehieudut.jsontest.Base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Copyright © 2016 BAP CO., LTD
 * Created by HieuLe on 05/11/2016.
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();

    protected abstract void afterView();

    @AfterViews
    protected void initView() {
        this.afterView();
    }

    protected void showAlertDialog(@NonNull String msg) {
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    protected void showAlertDialog(@StringRes int resId) {
        showAlertDialog(getString(resId));
    }

    @Override
    public void finish() {
        //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
