package com.imagemeditor.models;

import android.content.Context;
import android.content.Intent;

import com.imagemeditor.activity.FotoActivity;


public class Aplicacao {

    private Context context;
    private Class<?> activityDestino;

    public Aplicacao(Context context, Class<?> activityDestino) {
        this.context = context;
        this.activityDestino = activityDestino;
    }

    public Aplicacao(Context context) {
        this.context = context;
    }




    public static void fecharApp(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(homeIntent);
    }

    public static void irParaFotoActivity(Context context) {
        Intent intent = new Intent(context, FotoActivity.class);
        context.startActivity(intent);
    }


}
