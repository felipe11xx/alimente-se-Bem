package com.example.web.alimentesebem.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.VideoDaoOld;
import com.example.web.alimentesebem.model.VideoBean;
import com.example.web.alimentesebem.view.adapter.OnItemClick;
import com.example.web.alimentesebem.view.adapter.VideoAdpeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEB on 12/03/2018.
 */

public class TabVideos extends Fragment implements OnItemClick{
    public RecyclerView recyclerView;
    public VideoDaoOld daoOld = VideoDaoOld.instance;
    public List<VideoBean> lista ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_videos, container, false);

        lista = daoOld.getList();

        recyclerView = rootView.findViewById(R.id.rv_video);
        recyclerView.setAdapter(new VideoAdpeter(this.getContext(),lista,this));
        //Cria a tela com a lista dos Foruns
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,
                false));

        return rootView;
    }

    @Override
    public void onClick(Long id) {

    }
}
