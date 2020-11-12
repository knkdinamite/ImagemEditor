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
import java.util.List;
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

    public Foto(Context context, String local, Bitmap image) {
        this.context = context;
        this.local = local;
        this.file = getFile();
        this.uri = getUri();
        this.enviada = false;
        this.imageBitmap = image;


    }



    public Foto(Context context, File file) {
        this.context = context;
        this.file = file;
    }
    public Foto() {

    }

    public String getLocal() {
        // /storage/emulated/0/Imagem_Editor/Saved_Images
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Uri getUri() {

        this.uri = Uri.fromFile(new File(this.local));

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
        return new File(this.local);
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

    public static void saveImage(Bitmap bitmap,Context context) {

        String root = Environment.getExternalStorageDirectory().toString();
        String local = root + "/Imagem_Editor/Saved_Images";
        File myDir = new File(local);
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
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file:/ " + Environment.getExternalStorageDirectory())));

        } catch (Exception e) {
            Log.d("Erro_Foto","Erro: "+ e);
        }

        String endereçoCompleto = local + fname;
        Foto foto = new Foto(context,local, bitmap);
        foto.save(endereçoCompleto);

    }

    public void uploadFile() {

        File file =new File(this.local);

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

        Call<ResponseBody> call = new RetrofitConfig(this.getContext()).setFotoService().enviarfoto(description,body);
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




















