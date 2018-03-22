package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.NoticiaDaoOld;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.NoticiaAdpter;
import com.example.web.alimentesebem.view.adapter.OnItemClick;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabNoticia extends android.support.v4.app.Fragment {

   private RecyclerView recyclerView;
   private Intent intent;
   private List<NoticiaBean> noticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_noticias, container, false);
        // List<NoticiaBean> noticias = daoOld.getList();

        recyclerView = rootView.findViewById(R.id.rv_noticias);

        Call<List<NoticiaBean>> call = new RetrofitConfig().getRestInterface().listarNoticias();
        call.enqueue(new Callback<List<NoticiaBean>>() {
            @Override
            public void onResponse(Call<List<NoticiaBean>> call, Response<List<NoticiaBean>> response) {
                if (response.isSuccessful()) {
                    Log.d("passei", "passei");
                    noticias = response.body();
                    if (noticias != null) {
                        recyclerView.setAdapter(new NoticiaAdpter(noticias, getContext()));
                        //Cria a tela com a lista das noticias recentes
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false);
                        recyclerView.setLayoutManager(layout);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NoticiaBean>> call, Throwable t) {
                Log.d("Noticia: ", t.getMessage().toString());

            }
        });

        return rootView;
    }

}
