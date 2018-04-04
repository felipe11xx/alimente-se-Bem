package com.example.web.alimentesebem.model;

import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class NutricionistaBean {
    private Long id;
    private String nome;
    private String email;
    private long nif;
    private String cargo;
    private List<ForumBean> forum;
    private String cep;
    private String cidade;
    private String estado;
    private String local;


    public NutricionistaBean() {}

    public NutricionistaBean(Long id) {
        this.id = id;
    }

    public NutricionistaBean(Long id, String nome, String email, long nif, String cargo, List<ForumBean> forum, String cep, String cidade, String estado, String local) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nif = nif;
        this.cargo = cargo;
        this.forum = forum;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.local = local;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<ForumBean> getForum() {
        return forum;
    }

    public void setForum(List<ForumBean> forum) {
        this.forum = forum;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
