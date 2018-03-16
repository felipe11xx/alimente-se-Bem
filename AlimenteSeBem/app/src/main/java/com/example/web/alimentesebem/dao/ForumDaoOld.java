package com.example.web.alimentesebem.dao;

import com.example.web.alimentesebem.model.CategoriaForumBean;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.model.NutricionistaBean;

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
    private long id;
    private long idNutricionista;
    private long idCatergoria;

    public ForumDaoOld() {

        lista = new ArrayList<>();

        lista.add(new ForumBean(id++,"Como obter proteinas da carne",new NutricionistaBean(idNutricionista++,"Vinicius"),
                new CategoriaForumBean(idCatergoria++,"Saúde"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));
        lista.add(new ForumBean(id++,"Mitos do Whey",new NutricionistaBean(idNutricionista++,"Renata"),
                new CategoriaForumBean(idCatergoria++,"Fit"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));
        lista.add(new ForumBean(id++,"Vegetais cozidos",new NutricionistaBean(idNutricionista++,"Bruna"),
                new CategoriaForumBean(idCatergoria++,"Comida"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));
        lista.add(new ForumBean(id++,"Taça de vinho todo dia",new NutricionistaBean(idNutricionista++,"Leal"),
                new CategoriaForumBean(idCatergoria++,"Bebida"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));
        lista.add(new ForumBean(id++,"Arroz ou Chia",new NutricionistaBean(idNutricionista++,"Emili"),
                new CategoriaForumBean(idCatergoria++,"Grãos"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));
        lista.add(new ForumBean(id++,"Limite de açucar diario",new NutricionistaBean(idNutricionista++,"Felipe"),
                new CategoriaForumBean(idCatergoria++,"Doces"),
                new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime()));

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
