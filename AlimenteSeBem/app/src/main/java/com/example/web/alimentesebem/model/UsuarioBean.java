package com.example.web.alimentesebem.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class UsuarioBean {
    // @SerializedName("id")
    private Long id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("email")
    private String email;

    private List<ComentarioForumBean> comentario;

    public UsuarioBean (){}

    public UsuarioBean(Long id) {
        this.id = id;
    }

    public UsuarioBean(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public UsuarioBean(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ComentarioForumBean> getComentario() {
        return comentario;
    }

    public void setComentario(List<ComentarioForumBean> comentario) {
        this.comentario = comentario;
    }
}
