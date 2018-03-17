package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by WEB on 15/03/2018.
 */

public class ComentarioForumBean {

    private Long id;
    private String comentario;
    private UsuarioBean usuario;
    private ForumBean topico;
    private Date data;

    public ComentarioForumBean(){}

    public ComentarioForumBean(Long id) {
        this.id = id;
    }

    public ComentarioForumBean(Long id,String comentario, Date data, UsuarioBean usuario) {
        this.id = id;
        this.comentario = comentario;
        this.data = data;
        this.usuario = usuario;
    }

    public ComentarioForumBean(Long id, String comentario, UsuarioBean usuario, ForumBean topico, Date data) {
        this.id = id;
        this.comentario = comentario;
        this.usuario = usuario;
        this.topico = topico;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ForumBean getTopico() {
        return topico;
    }

    public void setTopico(ForumBean topico) {
        this.topico = topico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
