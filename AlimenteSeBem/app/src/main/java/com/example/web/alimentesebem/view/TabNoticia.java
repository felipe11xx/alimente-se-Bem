package com.example.web.alimentesebem.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.NoticiaAdpter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabNoticia extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private List<NoticiaBean> noticias;
    private ProgressBar prgNoticias;
    private BarraProgresso barraProgresso = BarraProgresso.instance;
    private Button btnRecarregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_noticias, container, false);
        // List<NoticiaBean> noticias = daoOld.getList();

        recyclerView = rootView.findViewById(R.id.rv_noticias);
        prgNoticias = rootView.findViewById(R.id.prg_noticias2);
        btnRecarregar = rootView.findViewById(R.id.btn_recarregar_noticias);
        btnRecarregar.setVisibility(View.INVISIBLE);
        //acessa os dados no servidor
        acessaServidor();

        return rootView;
    }

    private void acessaServidor(){
        Call<List<NoticiaBean>> call = new RetrofitConfig().getRestInterface().listarNoticias();
        call.enqueue(new Callback<List<NoticiaBean>>() {
            @Override
            public void onResponse(Call<List<NoticiaBean>> call, Response<List<NoticiaBean>> response) {
                barraProgresso.showProgress(true, prgNoticias);
                if (response.isSuccessful()) {

                    noticias = response.body();
                    btnRecarregar.setVisibility(View.INVISIBLE);
                    if (noticias != null) {
                        barraProgresso.showProgress(false,prgNoticias);
                        //  showProgress(false);
                        recyclerView.setAdapter(new NoticiaAdpter(noticias, getContext()));
                        //Cria a tela com a lista das noticias recentes
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false);
                        recyclerView.setLayoutManager(layout);
                    }else{
                        Toast.makeText(getContext(), R.string.noticias_null, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NoticiaBean>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.falha_de_acesso, Toast.LENGTH_SHORT).show();
                barraProgresso.showProgress(false,prgNoticias);
                btnRecarregar.setVisibility(View.VISIBLE);
                //acessa o servidor novamente em caso de falha
                btnRecarregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        barraProgresso.showProgress(true,prgNoticias);
                        btnRecarregar.setVisibility(View.INVISIBLE);
                        acessaServidor();
                    }
                });

            }
        });
    }

}


