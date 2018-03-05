package com.example.web.alimentesebem.view.adapter;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

import com.example.web.alimentesebem.dao.NoticiaDaoOld;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.utils.Utilitarios;

import java.util.List;

/**
 * Created by Felipe on 04/03/2018.
 */

public class NoticiasRecycledAdapter
        extends RecyclerView.Adapter<NoticiasRecycledAdapter.NoticiaViewHolder>
        implements AdapterInterface{

    //private NoticiaDao dao = NoticiaDao.instace;
    private NoticiaDaoOld dao = NoticiaDaoOld.instance;
    private SparseLongArray mapa;
    private Fragment fragment;
    private OnItemClickListener listener;

    public NoticiasRecycledAdapter(Fragment fragment, OnItemClickListener listener) {
        this.fragment = fragment;
        this.listener = listener;
        criarMapa();
    }

    @Override
    public void setEditar(boolean value) {

    }

    @Override
    public void notificaAtualizacao() {
        criarMapa();
        notificaAtualizacao();
    }

    private void criarMapa() {

        mapa = new SparseLongArray();
        List<Long> ids = dao.listarIds();
        for (int linha = 0; linha < ids.size(); linha++) {
            mapa.put(linha, ids.get(linha));
        }
    }

    @Override
    public int getItemCount() {
        return mapa.size();
    }

    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater svc = LayoutInflater.from(parent.getContext());
        View layout = svc.inflate(R.layout.detalhe_noticias,parent, false);
        return new NoticiaViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {
        NoticiaBean obj = dao.getNoticia(mapa.get(position));
        holder.setView(obj);
    }

    public class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTitulo;
        private TextView tvConteudo;
        private TextView tvpublicação;
        private ImageView imgCapa;
        private View view;

        public NoticiaViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvTitulo = itemView.findViewById(R.id.tv_titulo_noticia);
            tvConteudo = itemView.findViewById(R.id.tv_noticia);
            tvpublicação = itemView.findViewById(R.id.tv_data_noticia);
            imgCapa = itemView.findViewById(R.id.img_capa);
        }


        public void setView(final NoticiaBean obj) {
            tvTitulo.setText(obj.getTitulo());
            tvConteudo.setText(obj.getConteudo());

            byte[] foto = obj.getCapa();
            if (foto != null) {
                // Transforma o vetor de bytes de base64 para bitmap
                Bitmap bitmap = Utilitarios.bitmapFromBase64(foto);
                // Cria uma foto circular e atribui à foto
                imgCapa.setImageBitmap(bitmap);
            } else {
                // Obtem a 1ª letra do nome da pessoa e converte para Maiuscula
                String letra = obj.getTitulo().substring(0, 1).toUpperCase();
                // Cria um bitmap contendo a letra
                Bitmap bitmap = Utilitarios.circularBitmapAndText(
                        Color.parseColor("#936A4D"), 200, 200, letra);
                // atribui à foto
                imgCapa.setBackgroundColor(Color.TRANSPARENT);
                imgCapa.setImageBitmap(bitmap);
            }
        }

        @Override
        public void onClick(View v) {

        }
    }



}
