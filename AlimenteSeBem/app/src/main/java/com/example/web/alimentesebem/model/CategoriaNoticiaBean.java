package com.example.web.alimentesebem.model;

import java.util.List;

/**
 * Created by WEB on 20/03/2018.
 */

public class CategoriaNoticiaBean {
    private Long id;
    private String nome;
    private List<NoticiaBean> noticias;

    public CategoriaNoticiaBean() {}

    public CategoriaNoticiaBean(Long id) {
        this.id = id;
    }

    public CategoriaNoticiaBean(Long id, String nome) {
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

    public List<NoticiaBean> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<NoticiaBean> noticias) {
        this.noticias = noticias;
    }
}
