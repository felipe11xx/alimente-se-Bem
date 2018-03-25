package com.example.web.alimentesebem.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.VideoBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.OnItemClick;
import com.example.web.alimentesebem.view.adapter.VideoAdpeter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 12/03/2018.
 */

public class TabVideos extends Fragment implements OnItemClick{
    private RecyclerView recyclerView;
  //  public VideoDaoOld daoOld = VideoDaoOld.instance;
    private List<VideoBean> videos ;
    private BarraProgresso barraProgresso = BarraProgresso.instance;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_videos, container, false);

        recyclerView = rootView.findViewById(R.id.rv_video);
        progressBar = rootView.findViewById(R.id.prg_videos);

        Call<List<VideoBean>> call = new RetrofitConfig().getRestInterface().listaVideos();
        call.enqueue(new Callback<List<VideoBean>>() {

            @Override
            public void onResponse(Call<List<VideoBean>> call, Response<List<VideoBean>> response) {
                barraProgresso.showProgress(true,progressBar);
                if (response.isSuccessful()) {

                    videos = response.body();
                    barraProgresso.showProgress(false,progressBar);

                    if(videos != null) {
                        recyclerView.setAdapter(new VideoAdpeter(getContext(), videos));
                        //Cria a tela com a lista dos Foruns
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                                false));

                    }else{
                        Toast.makeText(getContext(),R.string.videos_null, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoBean>> call, Throwable t) {
                barraProgresso.showProgress(true, progressBar);
                Toast.makeText(getContext(),R.string.falha_de_acesso, Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

    @Override
    public void onClick(Long id) {

    }
}
