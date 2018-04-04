package com.example.web.alimentesebem.model;

import java.util.Date;
import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class ForumBean {

    private Long id_Forum;
    private String titulo;
    private List<ComentarioForumBean> comentario;
    private NutricionistaBean nutricionista;
    private long id_Nutricionista;
    private CategoriaForumBean categoria;
    private long id_Cat_Forum;
    private Date data_Criacao;
    private String tags;

    public ForumBean (){}

    public ForumBean(long id_Forum) {
        this.id_Forum = id_Forum;
    }


    public Long getId() {
        return id_Forum;
    }

    public void setId(Long id_forum) {
        this.id_Forum = id_forum;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<ComentarioForumBean> getComentarios() {
        return comentario;
    }

    public void setComentarios(List<ComentarioForumBean> comentario) {
        this.comentario = comentario;
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

    public Date getData_criacao() {
        return data_Criacao;
    }

    public void setData_criacao(Date dataAbertura) {
        this.data_Criacao = data_Criacao;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getId_Nutricionista() {
        return id_Nutricionista;
    }

    public void setId_Nutricionista(long id_Nutricionista) {
        this.id_Nutricionista = id_Nutricionista;
    }

    public long getId_Cat_Forum() {
        return id_Cat_Forum;
    }

    public void setId_Cat_Forum(long id_Cat_Forum) {
        this.id_Cat_Forum = id_Cat_Forum;
    }
}
