package com.example.web.alimentesebem.model;

import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class CategoriaForumBean {
    private Long id;
    private String nome;
    private List<ForumBean> forum;

    public CategoriaForumBean(){
    }

    public CategoriaForumBean(Long id) {
        this.id = id;
    }

    public CategoriaForumBean(Long id, String nome, List<ForumBean> forum) {
        this.id = id;
        this.nome = nome;
        this.forum = forum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ForumBean> getForum() {
        return forum;
    }

    public void setForum(List<ForumBean> forum) {
        this.forum = forum;
    }
}
