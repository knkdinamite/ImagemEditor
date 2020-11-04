package com.imagemeditor.api.servicos;

import com.imagemeditor.models.Usuario;
import com.imagemeditor.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {


    @POST("account/login/")
    Call<Usuario> logar(@Body Usuario usuario);
}
