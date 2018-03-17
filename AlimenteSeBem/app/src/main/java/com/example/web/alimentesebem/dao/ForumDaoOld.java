package com.example.web.alimentesebem.dao;

import com.example.web.alimentesebem.model.CategoriaForumBean;
import com.example.web.alimentesebem.model.ComentarioForumBean;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.model.NutricionistaBean;
import com.example.web.alimentesebem.model.TagForumBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by WEB on 15/03/2018.
 */

public class ForumDaoOld {

    public static ForumDaoOld instance = new ForumDaoOld();
    private List<ForumBean> lista;
    private List<TagForumBean> tags;
    private List<ComentarioForumBean> comentarios;
    private long id;
    private long idTags;
    private long idComentario;
    private long idNutricionista;
    private long idCatergoria;

    public ForumDaoOld() {

        lista = new ArrayList<>();
        tags = new ArrayList<TagForumBean>();
        comentarios = new ArrayList<ComentarioForumBean>();

        comentarios.add(new ComentarioForumBean(idComentario++,"Diga Vegeta qual o poder de luta do Kakarotto",
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

        comentarios.add(new ComentarioForumBean(idComentario++,"É de mais de Oito mil !!!",
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

        comentarios.add(new ComentarioForumBean(idComentario++,"Isso deve ser um engano este aparelho deve estar quebrado",
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

        comentarios.add(new ComentarioForumBean(idComentario++,"Oi eu sou o Goku",
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

        comentarios.add(new ComentarioForumBean(idComentario++,"Mas eu sou calmo e meu coração é puro meu coração é pura maldade",
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

        comentarios.add(new ComentarioForumBean(idComentario++,"Diga Vegeta qual o poder de luta do Kakarotto",
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

        tags.add(new TagForumBean(idTags++,"Kiabe"));
        tags.add(new TagForumBean(idTags++,"Kale"));
        tags.add(new TagForumBean(idTags++,"Broaly"));
        tags.add(new TagForumBean(idTags++,"Gohan"));
        tags.add(new TagForumBean(idTags++,"Vegeta"));
        tags.add(new TagForumBean(idTags++,"Kakaroto"));

        lista.add(new ForumBean(id++,"Como obter proteinas da carne",new NutricionistaBean(idNutricionista++,"Renata"),
                new CategoriaForumBean(idCatergoria++,"Saúde"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),tags,comentarios));
        lista.add(new ForumBean(id++,"Mitos do Whey",new NutricionistaBean(idNutricionista++,"Vinicius"),
                new CategoriaForumBean(idCatergoria++,"Fit"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),tags,comentarios));
        lista.add(new ForumBean(id++,"Vegetais cozidos",new NutricionistaBean(idNutricionista++,"Bruna"),
                new CategoriaForumBean(idCatergoria++,"Comida"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),tags,comentarios));
        lista.add(new ForumBean(id++,"Taça de vinho todo dia",new NutricionistaBean(idNutricionista++,"Leal"),
                new CategoriaForumBean(idCatergoria++,"Bebida"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),tags,comentarios));
        lista.add(new ForumBean(id++,"Arroz ou Chia",new NutricionistaBean(idNutricionista++,"Emili"),
                new CategoriaForumBean(idCatergoria++,"Grãos"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),tags,comentarios));
        lista.add(new ForumBean(id++,"Limite de açucar diario",new NutricionistaBean(idNutricionista++,"Felipe"),
                new CategoriaForumBean(idCatergoria++,"Doces"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),tags,comentarios));

    }

    public List<ForumBean> getLista(){
        return Collections.synchronizedList(lista);
    }


    public List<Long> listarIds(){

        List<Long> ids = new ArrayList<>();
        for(ForumBean obj: lista){
            ids.add(obj.getId());
        }

        return ids;
    }

    public  ForumBean getForum(final Long id){
        ForumBean obj = null;
        for (ForumBean evento: lista) {
            if(evento.getId() == id){
                obj = evento;
                break;
            }
        }

        return obj;
    }

    public void salvar(ForumBean obj){
        if(obj.getId() == null){
            obj.setId(id++);
            lista.add(obj);
        }else{
            int posicao = lista.indexOf(new ForumBean(obj.getId()));
            lista.set(posicao, obj);
        }
    }

}
