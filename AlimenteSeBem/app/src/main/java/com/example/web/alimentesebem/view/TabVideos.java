package com.example.web.alimentesebem.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.VideoBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.VideoAdpter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 12/03/2018.
 */

public class TabVideos extends Fragment {
    private RecyclerView recyclerView;
    private List<VideoBean> videos ;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private ProgressBar progressBar;
    private Button btnRecarregar;
    private SearchView searchView;
    private VideoAdpter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_videos, container, false);

        recyclerView = rootView.findViewById(R.id.rv_video);
        progressBar = rootView.findViewById(R.id.prg_videos);
        searchView = rootView.findViewById(R.id.sc_videos);
        btnRecarregar = rootView.findViewById(R.id.btn_recarregar_videos);
        btnRecarregar.setVisibility(View.INVISIBLE);

        //acessa os dados no servidor
        acessaServidor();

        return rootView;
    }

    private void acessaServidor(){

        Call<List<VideoBean>> call = new RetrofitConfig().getRestInterface().listaVideos();
        call.enqueue(new Callback<List<VideoBean>>() {

            @Override
            public void onResponse(Call<List<VideoBean>> call, Response<List<VideoBean>> response) {
                barraProgresso.showProgress(true,progressBar);
                if (response.isSuccessful()) {

                    btnRecarregar.setVisibility(View.INVISIBLE);
                    videos = response.body();
                    barraProgresso.showProgress(false,progressBar);

                    if(videos.size() > 0) {
                        adapter = new VideoAdpter(getContext(), videos);
                        recyclerView.setAdapter(adapter);
                        //Chama metodo para filtrar por titulo
                        buscaPorTitulo(adapter);
                        //Cria a tela com a lista dos Foruns
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false));

                    }else{
                        Toast.makeText(getContext(),R.string.videos_null, Toast.LENGTH_SHORT).show();
                        barraProgresso.showProgress(false, progressBar);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoBean>> call, Throwable t) {
                Toast.makeText(getContext(),R.string.falha_de_acesso, Toast.LENGTH_SHORT).show();
                barraProgresso.showProgress(false, progressBar);
                btnRecarregar.setVisibility(View.VISIBLE);

                //acessa o servidor novamente em caso de falha
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
    private void buscaPorTitulo(final VideoAdpter adapter){
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
