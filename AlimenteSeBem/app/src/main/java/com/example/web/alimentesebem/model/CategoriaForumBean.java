package com.example.web.alimentesebem.model;

/**
 * Created by WEB on 15/03/2018.
 */

public class CategoriaForumBean {
    private Long id;
    private String nome;

    public CategoriaForumBean(){
    }

    public CategoriaForumBean(Long id) {
        this.id = id;
    }

    public CategoriaForumBean(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
