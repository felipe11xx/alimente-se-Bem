package com.example.web.alimentesebem.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.VideoDaoOld;
import com.example.web.alimentesebem.model.VideoBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.OnItemClick;
import com.example.web.alimentesebem.view.adapter.VideoAdpeter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 12/03/2018.
 */

public class TabVideos extends Fragment implements OnItemClick{
    public RecyclerView recyclerView;
  //  public VideoDaoOld daoOld = VideoDaoOld.instance;
    public List<VideoBean> videos ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_videos, container, false);

        recyclerView = rootView.findViewById(R.id.rv_video);

        Call<List<VideoBean>> call = new RetrofitConfig().getRestInterface().listaVideos();
        call.enqueue(new Callback<List<VideoBean>>() {

            @Override
            public void onResponse(Call<List<VideoBean>> call, Response<List<VideoBean>> response) {

                videos = response.body();
                recyclerView.setAdapter(new VideoAdpeter(getContext(),videos));
                //Cria a tela com a lista dos Foruns
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                        false));
            }

            @Override
            public void onFailure(Call<List<VideoBean>> call, Throwable t) {
                Log.d("TabVideos: ", t.getMessage().toString());
            }
        });


        return rootView;
    }

    @Override
    public void onClick(Long id) {

    }
}
