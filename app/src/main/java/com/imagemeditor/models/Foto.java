package com.imagemeditor.models;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

import android.os.Environment;
import android.util.Log;

import com.imagemeditor.activity.ViewActivity;
import com.imagemeditor.api.retrofit.RetrofitConfig;
import com.imagemeditor.api.servicos.FotoService;
import com.orm.SugarRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Foto extends SugarRecord {
    private Bitmap imageBitmap;
    private Context context;
    private boolean enviada;
    private File file;
    private Uri uri;
    private String local;

    public Foto() {
    }

    public Foto(Context context, boolean enviada, Bitmap imageBitmap) {
        this.context = context;
        this.enviada = enviada;
        this.imageBitmap = imageBitmap;
    }

    public Foto(Context context, File file) {
        this.context = context;
        this.file = file;
    }

    public String getLocal() {
        //storage/emulated/0/Imagem_Editor/Saved_Images
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Uri getUri() {

        this.uri = Uri.fromFile(this.file);

        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isEnviada() {
        return enviada;
    }

    public void setEnviada(boolean enviada) {
        this.enviada = enviada;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    private void enviar() {


    }

    public void saveImage() {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Imagem_Editor/Saved_Images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".png";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            this.imageBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            this.context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));

        } catch (Exception e) {
            Log.d("Erro_Foto","Erro: "+ e);
        }

        this.file = file;

        this.save();

    }

    public void uploadFile() {
        // create upload service client
        FotoService fotoService = null;

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        File file =new File(this.getUri().getPath());

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse(Objects.requireNonNull(this.context.getContentResolver().getType(this.getUri()))), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("foto", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        // finally, execute the request
        Call<ResponseBody> call = fotoService.enviarfoto(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }
}




















