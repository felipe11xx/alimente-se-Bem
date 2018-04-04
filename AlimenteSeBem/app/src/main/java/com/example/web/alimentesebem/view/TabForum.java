package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.ForumDaoOld;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.ForumAdapter;
import com.example.web.alimentesebem.view.adapter.OnItemClick;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabForum extends Fragment  {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ForumAdapter adapter;
    private List<ForumBean> foruns;
    private ProgressBar progressBar;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private Button btnRecarregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_forum, container, false);
        searchView = rootView.findViewById(R.id.sc_forum);
        progressBar = rootView.findViewById(R.id.prg_forum);
        btnRecarregar = rootView.findViewById(R.id.btn_recarregar_foruns);
        recyclerView = rootView.findViewById(R.id.rv_forum);
        acessaServidor();

        return rootView;
    }

    private void acessaServidor(){
        Call<List<ForumBean>> call = new RetrofitConfig().getRestInterface().listaForuns();
        call.enqueue(new Callback<List<ForumBean>>() {
            @Override
            public void onResponse(Call<List<ForumBean>> call, Response<List<ForumBean>> response) {
                barraProgresso.showProgress(true,progressBar);
                if (response.isSuccessful()) {
                    btnRecarregar.setVisibility(View.INVISIBLE);
                    foruns = response.body();

                    barraProgresso.showProgress(false,progressBar);
                    if (foruns.size() > 0) {

                        adapter = new ForumAdapter(getContext(),foruns);
                        recyclerView.setAdapter(adapter);
                        //Chama metodo para filtrar por titulo
                        buscaPorTitulo(adapter);
                        //Cria a tela com a lista das noticias recentes
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false);
                        recyclerView.setLayoutManager(layout);

                    }else{
                        Toast.makeText(getContext(),R.string.foruns_null, Toast.LENGTH_SHORT).show();
                        barraProgresso.showProgress(false,progressBar);
                        btnRecarregar.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<ForumBean>> call, Throwable t) {
                Toast.makeText(getContext(),R.string.falha_de_acesso, Toast.LENGTH_SHORT).show();
                Log.d("TabForum",t.getMessage());
                barraProgresso.showProgress(false,progressBar);
                btnRecarregar.setVisibility(View.VISIBLE);
                // Acessa o servidor novamente em caso de falha
                btnRecarregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        barraProgresso.showProgress(true,progressBar);
                        btnRecarregar.setVisibility(View.INVISIBLE);
                        acessaServidor();
                    }
                });
            }
        });
    }

    //Metodo para filtrar por titulo no SearchView
    private void buscaPorTitulo(final ForumAdapter adapter){
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
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
