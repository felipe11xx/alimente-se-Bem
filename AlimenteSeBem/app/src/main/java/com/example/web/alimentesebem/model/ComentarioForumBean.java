package com.example.web.alimentesebem.model;

/**
 * Created by WEB on 15/03/2018.
 */

public class ComentarioForumBean {

    private Long id;
    private String comentario;

    public ComentarioForumBean(){}

    public ComentarioForumBean(Long id) {
        this.id = id;
    }

    public ComentarioForumBean(Long id,String comentario) {
        this.id = id;
        this.comentario = comentario;
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
}
