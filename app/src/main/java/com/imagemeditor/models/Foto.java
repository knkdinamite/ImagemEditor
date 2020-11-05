package com.imagemeditor.models;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.imagemeditor.api.retrofit.RetrofitConfig;
import com.orm.SugarRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Foto extends SugarRecord {
    private static Bitmap imageBitmap;
    private Context context;


    public static Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public static void setImageBitmap(Bitmap imageBitmap) {
        Foto.imageBitmap = imageBitmap;
    }

    private void enviar() {
        Call<Foto> call = new RetrofitConfig(this.context).setUserService().enviar(this);

        call.enqueue(new Callback<Foto>() {


            @Override
            public void onResponse(@NonNull Call<Foto> call, @NonNull Response<Foto> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Aplicacao.irParaFotoActivity(context);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Foto> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());
                //((RegisterActivity)context).esconderProgressBar();
            }
        });

    }


        public static void saveBitmap(String path, String bitName,
                Bitmap mBitmap) {//  ww  w.j  a va 2s.c  o  m

            File f = new File(Environment.getExternalStorageDirectory()
                    .toString() + "/" + bitName + ".png");
            try {
                f.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            try {
                fOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

















