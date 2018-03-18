package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.NoticiaDaoOld;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.view.adapter.NoticiaAdpter;
import com.example.web.alimentesebem.view.adapter.OnItemClick;

import java.util.List;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabNoticia extends android.support.v4.app.Fragment implements OnItemClick {

   private RecyclerView recyclerView;
   private NoticiaDaoOld daoOld = NoticiaDaoOld.instance;
   private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_noticias, container, false);

        List<NoticiaBean> noticias = daoOld.getList();

        recyclerView = rootView.findViewById(R.id.rv_noticias);

        recyclerView.setAdapter(new NoticiaAdpter(noticias,this.getContext(),this));

        //Cria a tela com a lista das noticias recentes
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(layout);

        return rootView;
    }

    @Override
    public void onClick(Long id) {
        intent = new Intent(this.getContext(),NoticiaActivity.class );
        intent.putExtra("NoticiaId",id);
        startActivity(intent);
    }
}
