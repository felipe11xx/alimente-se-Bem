package com.example.web.alimentesebem.model;

import java.util.Date;

/**
 * Created by WEB on 15/03/2018.
 */

public class ComentarioForumBean {

    private Long id_Comentario;
    private String descricao;
    private UsuarioBean usuario;
    private long id_User;
    private ForumBean forum;
    private long id_Forum;
    private Date data_Criacao;

    public ComentarioForumBean() {
    }

    public ComentarioForumBean(String descricao, long id_User, long id_Forum, Date data_Criacao) {
        this.descricao = descricao;
        this.id_User = id_User;
        this.id_Forum = id_Forum;
        this.data_Criacao = data_Criacao;
    }

    public ComentarioForumBean(Long id_Comentario) {
        this.id_Comentario = id_Comentario;
    }

    public Long getId_Comentario() {
        return id_Comentario;
    }

    public void setId_Comentario(Long id_Comentario) {
        this.id_Comentario = id_Comentario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public long getId_user() {
        return id_User;
    }

    public void setId_user(long id_User) {
        this.id_User = id_User;
    }

    public ForumBean getForum() {
        return forum;
    }

    public void setForum(ForumBean forum) {
        this.forum = forum;
    }

    public Date getData_criacao() {
        return data_Criacao;
    }

    public void setData_criacao(Date data_Criacao) {
        this.data_Criacao = data_Criacao;
    }
}