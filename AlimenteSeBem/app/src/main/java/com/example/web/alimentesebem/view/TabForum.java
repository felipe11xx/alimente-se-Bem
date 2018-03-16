package com.example.web.alimentesebem.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.ForumDaoOld;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.view.adapter.ForumAdapter;
import com.example.web.alimentesebem.view.adapter.OnItemClick;

import java.util.List;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabForum extends Fragment implements OnItemClick {

    private RecyclerView recyclerView;
    private ForumDaoOld daoOld = ForumDaoOld.instance;
    private ForumBean obj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_forum, container, false);
        List<ForumBean> foruns = daoOld.getLista();
        recyclerView = rootView.findViewById(R.id.rv_forum);
        recyclerView.setAdapter(new ForumAdapter(this.getContext(),foruns,this));

        //Cria a tela com a lista dos Foruns
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(layout);

        return rootView;
    }

    @Override
    public void onclick(Long id) {

    }
}
