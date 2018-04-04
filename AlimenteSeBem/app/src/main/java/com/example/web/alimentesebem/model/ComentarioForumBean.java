package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by WEB on 15/03/2018.
 */

public class ComentarioForumBean {

    private Long id_Comentario;
    private String comentario;
    private UsuarioBean usuario;
    private ForumBean forum;
    private Date data_Criacao;

    public ComentarioForumBean() {
    }

    public ComentarioForumBean(Long id_Comentario) {
        this.id_Comentario = id_Comentario;
    }

    public ComentarioForumBean(Long id_Comentario, String comentario, UsuarioBean usuario, ForumBean forum, Date data_Criacao) {
        this.id_Comentario = id_Comentario;
        this.comentario = comentario;
        this.usuario = usuario;
        this.forum = forum;
        this.data_Criacao = data_Criacao;
    }

    public Long getId_Comentario() {
        return id_Comentario;
    }

    public void setId_Comentario(Long id_Comentario) {
        this.id_Comentario = id_Comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public ForumBean getForum() {
        return forum;
    }

    public void setForum(ForumBean forum) {
        this.forum = forum;
    }

    public Date getData_Criacao() {
        return data_Criacao;
    }

    public void setData_Criacao(Date data_Criacao) {
        this.data_Criacao = data_Criacao;
    }
}