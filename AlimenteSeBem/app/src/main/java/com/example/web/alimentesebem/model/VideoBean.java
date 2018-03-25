package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by WEB on 15/03/2018.
 */

public class VideoBean {
    private Long id;
    private String titulo;
    private String descricao;
    private Date data;
    private UsuarioBean usuario;
    private String url;

    public VideoBean (){}

    public VideoBean(Long id) {
        this.id = id;
    }

    public VideoBean(Long id, String titulo, String descricao, Date data, String url) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
