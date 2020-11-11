package com.imagemeditor.api.servicos;

import android.location.Location;

import com.imagemeditor.models.Foto;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FotoService {



    @GET("")
    Call<Foto> receberfoto(@Header("Authorization") String key);

    @Multipart
    @POST("fotos/")
    Call<ResponseBody> enviarfoto(
            @Part("foto") RequestBody description,
            @Part MultipartBody.Part file
    );

}





