package com.example.web.alimentesebem.model;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("senha")
    private String senha;

    public UsuarioBean (){}

    public UsuarioBean(Long id) {
        this.id = id;
    }

    public UsuarioBean(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public UsuarioBean(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
