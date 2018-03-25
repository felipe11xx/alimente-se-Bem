package com.example.web.alimentesebem.model;

/**
 * Created by WEB on 15/03/2018.
 */

public class CategoriaVideoBean {
    private Long id;
    private String descricao;

    public CategoriaVideoBean(){}

    public CategoriaVideoBean(Long id) {
        this.id = id;
    }

    public CategoriaVideoBean(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
