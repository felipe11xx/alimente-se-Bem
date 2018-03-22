package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by Felipe on 03/03/2018.
 */

public class NoticiaBean {

    private Long id;
    private String titulo;
    private String headline;
    private String descricao;
    private Date data_Criacao;
    private CategoriaNoticiaBean categoria;
    private byte[] capa;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public NoticiaBean(){

    }

    public NoticiaBean(long id) {
        this.id = id;
    }

    public NoticiaBean(Long id, String titulo, Date dataPublica) {
        this.id = id;
        this.titulo = titulo;
        this.data_Criacao = dataPublica;

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

    public Date getDataPublica() {
        return data_Criacao;
    }

    public void setDataPublica(Date dataPublica) {
        this.data_Criacao = dataPublica;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public CategoriaNoticiaBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNoticiaBean categoria) {
        this.categoria = categoria;
    }
}
