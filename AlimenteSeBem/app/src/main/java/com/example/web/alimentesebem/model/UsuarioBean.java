package com.example.web.alimentesebem.model;

/**
 * Created by WEB on 15/03/2018.
 */

public class UsuarioBean {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;

    public UsuarioBean (){}

    public UsuarioBean(Long id) {
        this.id = id;
    }

    public UsuarioBean(Long id, String nome, String email, String senha, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
