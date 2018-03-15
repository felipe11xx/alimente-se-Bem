package com.example.web.alimentesebem.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.AgendaDaoOld;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.view.adapter.AgendaAdpter;
import com.example.web.alimentesebem.view.adapter.OnItemClick;

import java.util.List;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabAgenda extends android.support.v4.app.Fragment implements OnItemClick {

    private RecyclerView recyclerView;
    private AgendaDaoOld daoOld = AgendaDaoOld.instance;
    private AgendaBean obj;
    private Intent i;
    private final int CHAMA_EVENTO = 0;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_agenda, container, false);
        List<AgendaBean> calendarios = daoOld.getLista();
        recyclerView = rootView.findViewById(R.id.rvCalendario);
        recyclerView.setAdapter(new AgendaAdpter(calendarios, this.getContext(),this));
        //Cria a tela com a lista das noticias recentes
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(layout);

        return rootView;
    }


    @Override
    public void onclick(Long id) {
        i = new Intent(this.getContext(), EventoActivity.class);
        i.putExtra("EventoId",id);
        startActivity(i);
    }
}
