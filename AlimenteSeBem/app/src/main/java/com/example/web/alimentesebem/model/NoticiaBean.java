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
    private Date data_criacao;
    private CategoriaNoticiaBean categoria;
    private String imagem;


    public NoticiaBean(Long id) {
        this.id = id;
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

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public CategoriaNoticiaBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNoticiaBean categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
