package com.example.web.alimentesebem.dao;

import android.provider.MediaStore;

import com.example.web.alimentesebem.model.VideoBean;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Felipe on 18/03/2018.
 */

public class VideoDaoOld {
    public static VideoDaoOld instance = new VideoDaoOld();
    private List<VideoBean> lista;
    private long id;

    public VideoDaoOld() {
        lista = new ArrayList<>();

    lista.add(new VideoBean(id++,"Sesi Chef - RECEITA SANDUICHE",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "XXf5JQs4c7g"));
    lista.add(new VideoBean(id++,"Concurso Sesi Chef - 16/08/2017",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "1eneYODBl60"));
    lista.add(new VideoBean(id++,"Final Sesi Chef 2016",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "4uq2Dhy-xx4"));
    lista.add(new VideoBean(id++,"Sesi Chef - RECEITA SORVETE",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "b-5n8tmrvUE"));
/*
    lista.add(new VideoBean(id++,"Battlefield 4 - Pedrinho O Tarado do BF",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "JdI5zRQBSAk"));

    lista.add(new VideoBean(id++,"Battlefield 3 - Pescadores de Jatos, snipers falidos",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "70MSFxyGTEg"));

    lista.add(new VideoBean(id++,"Os Heróis da Cidade - Saints Row the third",
                "Lorem ipsum dolor sit amet, consectetur adipi" +
                        "scing elit, sed do eiusmod tempor incididunt ut labore",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 10).getTime(),
                "Z2ki9jyhrwQ"));
*/

    }

    public List<VideoBean> getList(){

        return Collections.synchronizedList(lista);
    }

    public List<Long> listarIds(){


        List<Long> ids = new ArrayList<>();
        for(VideoBean obj: lista){
            ids.add(obj.getId());
        }

        return ids;
    }

    public VideoBean getVideo(final Long id){
        VideoBean obj = null;
        for (VideoBean video: lista) {
            if(video.getId() == id){
                obj = video;
                break;
            }
        }

        return obj;
    }

    public void salvar(VideoBean obj){
        if(obj.getId() == null){
            obj.setId(id++);
            lista.add(obj);
        }else{
            int posicao = lista.indexOf(new VideoBean(obj.getId()));
            lista.set(posicao, obj);
        }
    }
    public void apagar(long id){
        lista.remove(new VideoBean(id));
    }
}
