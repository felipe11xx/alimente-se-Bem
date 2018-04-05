package com.example.web.alimentesebem.rest;

import android.icu.lang.UScript;

import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.model.CategoriaForumBean;
import com.example.web.alimentesebem.model.ComentarioForumBean;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.model.NutricionistaBean;
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
import retrofit2.http.Query;

public interface RestInterface {

    @GET("Noticias")
    Call<List<NoticiaBean>> listarNoticias();

    @GET("Agenda")
    Call<List<AgendaBean>> listarEventos();

    @GET("Agenda/{id}")
    Call<AgendaBean> getEvento(@Path("id") Long id);

    @GET("Videos")
    Call<List<VideoBean>> listaVideos();

    @GET("Forum")
    Call<List<ForumBean>> listaForuns();

    @GET("Forum/{id}")
    Call<ForumBean> getForum(@Path("id") Long id);

    @GET("Nutricionistas/{id}")
    Call<NutricionistaBean> getNutricionista(@Path("id") Long id);

    @GET("Categorias_Forum/{id}")
    Call<CategoriaForumBean> getCategoriaForum(@Path("id") Long id);

    @GET("Usuario/{id}")
    Call<UsuarioBean> getUsuario(@Path("id") Long id);

    @GET("Usuario/email?")
    Call<List<UsuarioBean>> getUsuarioEmail(@Query("email") String email);

    @POST("Usuario/Cadastrar")
    Call<ResponseBody> cadastraUsuario(@Body UsuarioBean usuarioBean);

    @POST("Usuario/Cadastrar")
    Call<UsuarioBean> cadastrarUsuarioBean(@Body UsuarioBean usuarioBean);

    @POST("Comentario")
    Call<ResponseBody> cadastraComentario (@Body ComentarioForumBean comentarioForumBean);

    @POST("Comentario")
    Call<ComentarioForumBean> cadastraComentarioBean (@Body ComentarioForumBean comentarioForumBean);

}