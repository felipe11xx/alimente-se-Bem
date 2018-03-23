package com.example.web.alimentesebem.model;

import java.util.List;

/**
 * Created by WEB on 22/03/2018.
 */

public class UnidadeBean {
    private Long id;
    private String nome;
    private int codigo_unidade;
    private List<AgendaBean> agenda;
    private String local;
    private String cep;
    private String cidade;
    private String estado;

    public UnidadeBean(){}

    public UnidadeBean(Long id) {
        this.id = id;
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

    public int getCodigo_unidade() {
        return codigo_unidade;
    }

    public void setCodigo_unidade(int codigo_unidade) {
        this.codigo_unidade = codigo_unidade;
    }

    public List<AgendaBean> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<AgendaBean> agenda) {
        this.agenda = agenda;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
}
