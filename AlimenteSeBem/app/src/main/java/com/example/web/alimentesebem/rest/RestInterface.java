package com.example.web.alimentesebem.rest;

import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.model.UnidadeBean;
import com.example.web.alimentesebem.model.UsuarioBean;
import com.example.web.alimentesebem.model.VideoBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestInterface {

    @GET("Noticias")
    Call<List<NoticiaBean>> listarNoticias();

    @GET("Agenda")
    Call<List<AgendaBean>> listarEventos();

    @GET("Agenda/{id}")
    Call<AgendaBean> getEvento(@Path("id") Long id);

    @GET("Videos")
    Call<List<VideoBean>> listaVideos();

    @POST("Usuario/Cadastrar")
    Call<ResponseBody> cadastraUsuario(@Body UsuarioBean usuarioBean);

    @POST("Usuario/Cadastrar")
    Call<UsuarioBean> cadastrarUsuarioBean(@Body UsuarioBean usuarioBean);


}