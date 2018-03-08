package com.example.web.alimentesebem.dao;

import com.example.web.alimentesebem.model.CalendarioBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by WEB on 06/03/2018.
 */

public class CalendarioDaoOld {

    public static CalendarioDaoOld instance = new CalendarioDaoOld();
    private List<CalendarioBean> lista;
    private long id;

    public CalendarioDaoOld () {
        lista = new ArrayList<>();

        lista.add(new CalendarioBean(id++,"Feira da Fruta","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santos",new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),"10:00"));
        lista.add(new CalendarioBean(id++,"Curso De Nutrição","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santo Amaro",new GregorianCalendar(2017, Calendar.DECEMBER, 12).getTime(),"12:00"));
        lista.add(new CalendarioBean(id++,"Feira da Fruta","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santos",new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),"14:00"));
        lista.add(new CalendarioBean(id++,"Feira da Fruta","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santos",new GregorianCalendar(2018, Calendar.FEBRUARY, 15).getTime(),"11:30"));

    }


    public List<CalendarioBean> getLista(){
        return Collections.synchronizedList(lista);
    }


    public List<Long> listarIds(){


        List<Long> ids = new ArrayList<>();
        for(CalendarioBean obj: lista){
            ids.add(obj.getId());
        }

        return ids;
    }

    public CalendarioBean getEvento(final Long id){
        CalendarioBean obj = null;
        for (CalendarioBean evento: lista) {
            if(evento.getId() == id){
                obj = evento;
                break;
            }
        }

        return obj;
    }

    public void salvar(CalendarioBean obj){
        if(obj.getId() == null){
            obj.setId(id++);
            lista.add(obj);
        }else{
            int posicao = lista.indexOf(new CalendarioBean(obj.getId()));
            lista.set(posicao, obj);
        }
    }

}
