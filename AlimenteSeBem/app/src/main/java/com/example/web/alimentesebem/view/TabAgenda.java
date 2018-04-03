package com.example.web.alimentesebem.view;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

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
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private ProgressBar progressBar;
    private Button btnRecarregar;
    private SearchView searchView;
    private AgendaAdpter adapter;
/*
    private MyPreference myPreference = MyPreference.getInstance(getContext());
    private String ordemPreference;
    private String ordemDefault;
*/

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_agenda, container, false);
        // List<AgendaBean> calendarios = daoOld.getLista();
        recyclerView = rootView.findViewById(R.id.rv_calendario);
        progressBar = rootView.findViewById(R.id.prg_agenda);
        btnRecarregar = rootView.findViewById(R.id.btn_recarregar_agenda);
        searchView = rootView.findViewById(R.id.sc_agenda);
        CharSequence query = searchView.getQuery();
        btnRecarregar.setVisibility(View.INVISIBLE);

        // Acessa os dados no servidor
        acessaServidor();



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter != null)
        adapter.notifyDataSetChanged();
    }

    private void acessaServidor(){
        Call<List<AgendaBean>> call = new RetrofitConfig().getRestInterface().listarEventos();
        call.enqueue(new Callback<List<AgendaBean>>() {
            @Override
            public void onResponse(Call<List<AgendaBean>> call, Response<List<AgendaBean>> response) {
                barraProgresso.showProgress(true,progressBar);
                if (response.isSuccessful()) {
                    btnRecarregar.setVisibility(View.INVISIBLE);
                    eventos = response.body();

                    barraProgresso.showProgress(false,progressBar);
                    if (eventos != null) {

                        adapter = new AgendaAdpter(eventos, getContext());
                        recyclerView.setAdapter(adapter);
                        //Chama metodo para filtrar por titulo
                        buscaPorTitulo(adapter);
                        //Cria a tela com a lista das noticias recentes
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false);
                        recyclerView.setLayoutManager(layout);

                    }else{
                        Toast.makeText(getContext(),R.string.agenda_null, Toast.LENGTH_SHORT).show();
                        barraProgresso.showProgress(false,progressBar);
                        btnRecarregar.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<AgendaBean>> call, Throwable t) {
                Toast.makeText(getContext(),R.string.falha_de_acesso, Toast.LENGTH_SHORT).show();
                Log.d("TabAgenda",t.getMessage());
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
    private void buscaPorTitulo(final AgendaAdpter adapter){
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
