package com.example.web.alimentesebem.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.AgendaAdpter;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabAgenda extends Fragment {

    private RecyclerView recyclerView;
   // private AgendaDaoOld daoOld = AgendaDaoOld.instance;
    private List<AgendaBean> eventos;
    private Intent intent;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_agenda, container, false);
     //   List<AgendaBean> calendarios = daoOld.getLista();
        recyclerView = rootView.findViewById(R.id.rv_calendario);

        Call<List<AgendaBean>> call = new RetrofitConfig().getRestInterface().listarEventos();
        call.enqueue(new Callback<List<AgendaBean>>() {
            @Override
            public void onResponse(Call<List<AgendaBean>> call, Response<List<AgendaBean>> response) {
                if (response.isSuccessful()) {

                    eventos = response.body();
                    if (eventos != null) {
                        recyclerView.setAdapter(new AgendaAdpter(eventos, getContext()));
                        //Cria a tela com a lista das noticias recentes
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false);
                        recyclerView.setLayoutManager(layout);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<AgendaBean>> call, Throwable t) {
                Log.d("TabAgenda: ", t.getMessage().toString());

            }
        });

        return rootView;
    }



}
