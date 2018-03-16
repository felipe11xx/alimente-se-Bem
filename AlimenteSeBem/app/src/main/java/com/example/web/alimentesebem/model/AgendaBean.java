package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by WEB on 06/03/2018.
 */

public class AgendaBean {

    private Long id;
    private String titulo;
    private String descricao;
    private String local;
    private Date data;
    private String horario;
    private byte[] capa;
    private String[] tags;
    private CategoriaAgendaBean categoria;
    private double preco;

    public AgendaBean(){}

    public AgendaBean(Long id) {
        this.id = id;
    }

    public AgendaBean(Long id, String titulo, String descricao, String local, Date data, String horario, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.horario = horario;
        this.preco = preco;

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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CategoriaAgendaBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAgendaBean categoria) {
        this.categoria = categoria;
    }
}
