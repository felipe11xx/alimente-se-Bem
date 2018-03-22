package com.example.web.alimentesebem.rest;

import com.example.web.alimentesebem.model.NoticiaBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {

    @GET("Noticias")
    Call<List<NoticiaBean>> listarNoticias();

}