package com.example.web.alimentesebem.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.CalendarioDaoOld;
import com.example.web.alimentesebem.model.CalendarioBean;
import com.example.web.alimentesebem.view.adapter.CalendarioAdpter;

import java.util.List;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabCalendario extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private CalendarioDaoOld daoOld = CalendarioDaoOld.instance;
    private CalendarioBean obj;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_calendario, container, false);

        List<CalendarioBean> calendarios = daoOld.getLista();

        recyclerView = rootView.findViewById(R.id.rvCalendario);

        recyclerView.setAdapter(new CalendarioAdpter(calendarios, this.getContext()));

        //Cria a tela com a lista das noticias recentes
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(layout);

        return rootView;
    }


}
