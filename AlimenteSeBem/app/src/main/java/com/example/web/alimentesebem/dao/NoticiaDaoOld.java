package com.example.web.alimentesebem.dao;

import android.util.Log;

import com.example.web.alimentesebem.model.NoticiaBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Felipe on 04/03/2018.
 */

public class NoticiaDaoOld {

    public static NoticiaDaoOld instance = new NoticiaDaoOld();
    private List<NoticiaBean> lista;
    private long id = 0;

    public NoticiaDaoOld() {
        lista = new ArrayList<>();
    /*    lista.add(new NoticiaBean(id++, "Novos cursos", "Lorem ipsum dolor sit amet, consectetur adipi" +
                "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));
        lista.add(new NoticiaBean(id++, "Beneficios da Cenoura", "Lorem ipsum dolor sit amet, consectetur adipi" +
                "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));
        lista.add(new NoticiaBean(id++, "Mitos do Ovo", "Lorem ipsum dolor sit amet, consectetur adipi" +
                "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));
        lista.add(new NoticiaBean(id++, "Saudavel e Saboroso", "Lorem ipsum dolor sit amet, consectetur adipi" +
                "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));
        lista.add(new NoticiaBean(id++, "Proteinas e suas imporcias", "Lorem ipsum dolor sit amet, consectetur adipi" +
                "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));
        lista.add(new NoticiaBean(id++, "Beneficios da carne branca", "Lorem ipsum dolor sit amet, consectetur adipi" +
                "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));
        lista.add(new NoticiaBean(id++, "Como a alimentação afeta Você", "Lorem ",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime()));*/
    }

    public List<NoticiaBean> getList(){

        return Collections.synchronizedList(lista);
    }

    public List<Long> listarIds(){


        List<Long> ids = new ArrayList<>();
        for(NoticiaBean obj: lista){
            ids.add(obj.getId());
        }

        return ids;
    }

    public NoticiaBean getNoticia(final Long id){
        NoticiaBean obj = null;
        for (NoticiaBean noticia: lista) {
            if(noticia.getId() == id){
                obj = noticia;
                break;
            }
        }

        return obj;
    }

    public void salvar(NoticiaBean obj){
        if(obj.getId() == null){
            obj.setId(id++);
            lista.add(obj);
        }else{
            int posicao = lista.indexOf(new NoticiaBean(obj.getId()));
            lista.set(posicao, obj);
        }
    }
    public void apagar(long id){
        lista.remove(new NoticiaBean(id));
    }

}
