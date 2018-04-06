package com.example.web.alimentesebem.model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by WEB on 06/03/2018.
 */

public class AgendaBean {

    private Long id;
    private String titulo;
    private String descricao;
    private UnidadeBean unidades_Sesi;
    private Date data_Evento;
    private String horario;
    private String url_Imagem;
    private String tag;
    private CategoriaAgendaBean categoria;
    private double preco;

    public AgendaBean(){}

    public AgendaBean(Long id) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeBean getUnidades_Sesi() {
        return unidades_Sesi;
    }

    public void setUnidades_Sesi(UnidadeBean unidades_Sesi) {
        this.unidades_Sesi = unidades_Sesi;
    }

    public Date getData_Evento() {
        return data_Evento;
    }

    public void setData_Evento(Date data_Evento) {
        this.data_Evento = data_Evento;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getUrl_Imagem() {
        return url_Imagem;
    }

    public void setUrl_Imagem(String url_Imagem) {
        this.url_Imagem = url_Imagem;
    }

    public String getTags() {
        return tag;
    }

    public void setTags(String tag) {
        this.tag = tag;
    }

    public CategoriaAgendaBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAgendaBean categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
