package com.example.web.alimentesebem.model;

import java.util.Date;
import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class ForumBean {

    private Long id_forum;
    private String titulo;
    private List<ComentarioForumBean> cometarios;
    private NutricionistaBean nutricionista;
    private CategoriaForumBean categoria;
    private Date dataAbertura;
    private List<TagForumBean> tags;

    public ForumBean (){}

    public ForumBean(long id_forum) {
        this.id_forum = id_forum;
    }

    public ForumBean(long id, String titulo, NutricionistaBean nutricionista, CategoriaForumBean categoria,
                     Date dataAbertura,List<TagForumBean> tags,List<ComentarioForumBean> cometarios) {
        this.id_forum = id;
        this.titulo = titulo;
        this.nutricionista = nutricionista;
        this.categoria = categoria;
        this.dataAbertura = dataAbertura;
        this.tags = tags;
        this.cometarios = cometarios;

    }

    public Long getId() {
        return id_forum;
    }

    public void setId(Long id_forum) {
        this.id_forum = id_forum;
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
    public void addComentarios(List<ComentarioForumBean> cometarios,ComentarioForumBean comentario){
        cometarios.add(comentario);
    }
    public NutricionistaBean getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(NutricionistaBean autor) {
        this.nutricionista = autor;
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
