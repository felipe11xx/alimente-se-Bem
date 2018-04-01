package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private Intent intent;
    private SearchView searchView;
    private ForumAdapter adapter;
    private FloatingActionButton floatForum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_forum, container, false);
        searchView = rootView.findViewById(R.id.sc_forum);
        List<ForumBean> foruns = daoOld.getLista();
        adapter = new ForumAdapter(this.getContext(),foruns,this);
        recyclerView = rootView.findViewById(R.id.rv_forum);
        recyclerView.setAdapter(adapter);
        //Chama metodo para filtrar por titulo
        buscaPorTitulo(adapter);
        //Cria a tela com a lista dos Foruns
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(layout);


        floatForum = getActivity().findViewById(R.id.btn_ordena_forum);


        floatForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.filtro_menu, popupMenu.getMenu());

                // final Activity context = (Activity)view.getContext();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.item_titulo:
                                Toast.makeText(getContext(),"Titulo",Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.item_data:
                                Toast.makeText(getContext(),"Data",Toast.LENGTH_SHORT).show();
                                break;

                        }

                        return true;
                    }
                });

                popupMenu.show();
            }
        });
        return rootView;
    }

    @Override
    public void onClick(Long id) {

        intent = new Intent(this.getContext(), TopicoActivity.class);
        intent.putExtra("TopicoId", id);
        startActivity(intent);

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
