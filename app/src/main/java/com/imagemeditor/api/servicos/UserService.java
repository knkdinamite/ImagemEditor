package com.imagemeditor.api.servicos;

import com.imagemeditor.models.Foto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {


    @POST("fotos/")
    Call<Foto> enviar(@Body Foto imagem );

    @GET("")
    Call<Foto> receberfoto(@Header("Authorization") String key);
}



