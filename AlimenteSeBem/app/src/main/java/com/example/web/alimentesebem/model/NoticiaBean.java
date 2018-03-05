package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by Felipe on 03/03/2018.
 */

public class NoticiaBean {

    private Long id;
    private String titulo;
    private String conteudo;
    private Date dataPublica;
    private byte[] capa;

    public NoticiaBean(){

    }

    public NoticiaBean(long id) {
        this.id = id;
    }

    public NoticiaBean(Long id, String titulo, String conteudo, Date dataPublica) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataPublica = dataPublica;

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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataPublica() {
        return dataPublica;
    }

    public void setDataPublica(Date dataPublica) {
        this.dataPublica = dataPublica;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }


}
