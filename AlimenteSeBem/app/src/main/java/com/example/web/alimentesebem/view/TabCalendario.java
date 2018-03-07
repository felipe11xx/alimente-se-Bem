package com.example.web.alimentesebem.view;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.CalendarioDaoOld;
import com.example.web.alimentesebem.model.CalendarioBean;
import com.example.web.alimentesebem.view.adapter.CalendarioRecycleAdpter;
import com.example.web.alimentesebem.view.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by WEB on 02/03/2018.
 */

public class TabCalendario extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
   // private CalendarioDaoOld dao = CalendarioDaoOld.instance;

    private CalendarioRecycleAdpter adpter;
    private OnItemClickListener listener;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_calendario, container, false);
       // List<CalendarioBean> lista = dao.getLista();

        adpter = new CalendarioRecycleAdpter(this.getParentFragment(), listener );
        recyclerView = rootView.findViewById(R.id.rvCalendario);

       // recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this.getContext()
                ,1);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }


}
