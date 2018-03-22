package com.example.web.alimentesebem.model;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Felipe on 03/03/2018.
 */
@SuppressLint("SimpleDateFormat")
public class NoticiaBean implements  Comparable<NoticiaBean> {

    private Long id;
    private String titulo;
    private String conteudo;
    private String headLine;
    private Date dataPublica;
    private CategoriaNoticiaBean categoria;
    private String capa;
    private static SimpleDateFormat fmtData =
            new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

    public NoticiaBean(){

    }

    public NoticiaBean(long id) {
        this.id = id;
    }

    public NoticiaBean(Long id, String titulo, String conteudo, Date dataPublica) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataPublica = dataPublica;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataPublica() {
        return dataPublica;
    }

    public void setDataPublica(Date dataPublica) {
        this.dataPublica = dataPublica;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public CategoriaNoticiaBean getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNoticiaBean categoria) {
        this.categoria = categoria;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       NoticiaBean noticia = (NoticiaBean) o;

        if (!id.equals(noticia.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", headline='" + headLine + '\'' +
                ", descricao='" + conteudo + '\'' +
                ", data_Criacao=" + dataPublica + '\'' +
                ", imagem=" + capa + '\'' +
                ", link_Externo=" + '\'' +
                ", categorias_Noticias="+ null+ '\'' +
                ", id_Cat_Noticias" + categoria.getId() +
                '}';


    }

    @Override
    public int compareTo(@NonNull NoticiaBean outra) {
        return titulo.compareToIgnoreCase(outra.titulo) ;
    }
}
