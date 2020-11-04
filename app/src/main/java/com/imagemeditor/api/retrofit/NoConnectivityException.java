package com.imagemeditor.api.retrofit;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.imagemeditor.R;

import java.io.IOException;


public class NoConnectivityException extends IOException {

    private Context context;

    public NoConnectivityException(Context mContext) {
        this.context = mContext;
    }



}
