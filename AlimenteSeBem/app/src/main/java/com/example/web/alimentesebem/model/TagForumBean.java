package com.example.web.alimentesebem.model;

/**
 * Created by WEB on 15/03/2018.
 */

public class TagForumBean {
    private Long id;
    private String tag;

    public TagForumBean(){}

    public TagForumBean(Long id) {
        this.id = id;
    }

    public TagForumBean(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
