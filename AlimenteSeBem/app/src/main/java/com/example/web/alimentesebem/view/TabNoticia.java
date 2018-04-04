package com.example.web.alimentesebem.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.NoticiaAdpter;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabNoticia extends android.support.v4.app.Fragment implements Serializable {

    private RecyclerView recyclerView;
    private List<NoticiaBean> noticias;
    private ProgressBar prgNoticias;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private Button btnRecarregar;
    private SearchView searchView;
    private NoticiaAdpter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_noticias, container, false);
        // List<NoticiaBean> noticias = daoOld.getList();

        recyclerView = rootView.findViewById(R.id.rv_noticias);
        prgNoticias = rootView.findViewById(R.id.prg_noticias2);
        searchView = rootView.findViewById(R.id.sc_noticia);
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
                    if (noticias.size() > 0) {
                        barraProgresso.showProgress(false,prgNoticias);
                        adapter = new NoticiaAdpter(noticias, getContext());
                        recyclerView.setAdapter(adapter);
                        //Chama metodo para filtrar por titulo
                        buscaPorTitulo(adapter);
                        //Cria a tela com a lista das noticias recentes
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false);
                        recyclerView.setLayoutManager(layout);
                    }else{
                        Toast.makeText(getContext(), R.string.noticias_null, Toast.LENGTH_SHORT).show();
                        barraProgresso.showProgress(false,prgNoticias);
                        btnRecarregar.setVisibility(View.VISIBLE);
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


    //Metodo para filtrar por titulo no SearchView
    private void buscaPorTitulo(final NoticiaAdpter adapter) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                // recebo a String que quero buscar
                String tituloBuscado = s;
                // coloco um filtro no próprio adapter
                adapter.filtrarPorTitulo(tituloBuscado);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}


