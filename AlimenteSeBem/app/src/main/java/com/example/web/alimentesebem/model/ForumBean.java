package com.example.web.alimentesebem.model;

import java.util.Date;
import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class ForumBean {

    private Long id;
    private String titulo;
    private List<ComentarioForumBean> cometarios;
    private NutricionistaBean autor;
    private CategoriaForumBean categoria;
    private Date dataAbertura;
    private List<TagForumBean> tags;

    public ForumBean (){}

    public ForumBean(long id) {
        this.id = id;
    }

    public ForumBean(long id, String titulo, NutricionistaBean autor, CategoriaForumBean categoria,
                     Date dataAbertura,List<TagForumBean> tags,List<ComentarioForumBean> cometarios) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.dataAbertura = dataAbertura;
        this.tags = tags;
        this.cometarios = cometarios;

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

    public List<ComentarioForumBean> getCometarios() {
        return cometarios;
    }

    public void setCometarios(List<ComentarioForumBean> cometarios) {
        this.cometarios = cometarios;
    }

    public NutricionistaBean getAutor() {
        return autor;
    }

    public void setAutor(NutricionistaBean autor) {
        this.autor = autor;
    }

    public CategoriaForumBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaForumBean categoria) {
        this.categoria = categoria;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public List<TagForumBean> getTags() {
        return tags;
    }

    public void setTags(List<TagForumBean> tags) {
        this.tags = tags;
    }
}
