package com.imagemeditor.api.retrofit;

import android.content.Context;

import com.imagemeditor.models.Android;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (!Android.isNetworkConnected(mContext)) {
            throw new NoConnectivityException(mContext);
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}