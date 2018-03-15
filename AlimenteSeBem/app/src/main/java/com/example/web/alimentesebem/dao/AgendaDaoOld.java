package com.example.web.alimentesebem.dao;

import com.example.web.alimentesebem.model.AgendaBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by WEB on 06/03/2018.
 */

public class AgendaDaoOld {

    public static AgendaDaoOld instance = new AgendaDaoOld();
    private List<AgendaBean> lista;
    private long id;

    public AgendaDaoOld() {
        lista = new ArrayList<>();

        lista.add(new AgendaBean(id++,"Feira da Fruta","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santos",new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),"10:00",20.00));
        lista.add(new AgendaBean(id++,"Curso De Nutrição","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santo Amaro",new GregorianCalendar(2017, Calendar.DECEMBER, 12).getTime(),"12:00",0));
        lista.add(new AgendaBean(id++,"Feira da Fruta","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santos",new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),"14:00",10.00));
        lista.add(new AgendaBean(id++,"Feira da Fruta","Lorem ipsum dolor sit amet, ad nec tamquam disputando voluptatibus." +
                " In eam dicam vidisse philosophia, et est alia persecuti. An per sonet cetero. Ne per vitae nusquam vivendum." +
                " Eius mucius posidonium ad ius, vero maluisset maiestatis vis an. Vim singulis platonem complectitur te, nonumy" +
                " ponderum an mel.","Sesi Santos",new GregorianCalendar(2018, Calendar.FEBRUARY, 15).getTime(),"11:30",15.99));

    }


    public List<AgendaBean> getLista(){
        return Collections.synchronizedList(lista);
    }


    public List<Long> listarIds(){


        List<Long> ids = new ArrayList<>();
        for(AgendaBean obj: lista){
            ids.add(obj.getId());
        }

        return ids;
    }

    public AgendaBean getEvento(final Long id){
        AgendaBean obj = null;
        for (AgendaBean evento: lista) {
            if(evento.getId() == id){
                obj = evento;
                break;
            }
        }

        return obj;
    }

    public void salvar(AgendaBean obj){
        if(obj.getId() == null){
            obj.setId(id++);
            lista.add(obj);
        }else{
            int posicao = lista.indexOf(new AgendaBean(obj.getId()));
            lista.set(posicao, obj);
        }
    }

}
