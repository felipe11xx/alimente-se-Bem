package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.NoticiaBean;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Felipe on 04/03/2018.
 */

public class NoticiaAdpter extends RecyclerView.Adapter {

   private List<NoticiaBean> noticias;
   private Context context;
   private OnItemClick onItemClick;

   public NoticiaAdpter(List<NoticiaBean> noticias, Context context, OnItemClick click){
       this.noticias = noticias;
       this.context = context;
       this.onItemClick = click;
   }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_noticias, parent,false);

        NoticiaViewHolder holder = new NoticiaViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoticiaViewHolder viewHolder = (NoticiaViewHolder) holder;

        NoticiaBean noticiaBean = noticias.get(position);

        ((NoticiaViewHolder)holder).preencher(noticiaBean);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tvTitulo;
        public final TextView tvConteudo;
        public final TextView tvPublicacao;
        public final ImageView imgCapa;
        private Long noticiaId;
        public final NoticiaAdpter adpter;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG);

        public NoticiaViewHolder(final View view, final NoticiaAdpter adpter) {
            super(view);

            this.adpter = adpter;

            view.setOnClickListener(this);

            tvTitulo = view.findViewById(R.id.tv_titulo_forum);
            tvConteudo = view.findViewById(R.id.tv_autor);
            tvPublicacao = view.findViewById(R.id.tv_data_noticia);
            imgCapa = view.findViewById(R.id.img_capa_noticia);

            Typeface typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Condensed_Bold.otf");
            tvTitulo.setTypeface(typeFont);
            typeFont = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham_Light.otf");
            tvConteudo.setTypeface(typeFont);
            tvPublicacao.setTypeface(typeFont);

        }

        public void preencher(NoticiaBean noticiaBean){


            noticiaId = noticiaBean.getId();
            tvTitulo.setText(noticiaBean.getTitulo());
            tvConteudo.setText(noticiaBean.getConteudo());
            tvPublicacao.setText(dtFmt.format(noticiaBean.getDataPublica()) );
        }


        @Override
        public void onClick(View v) {
            onItemClick.onClick(noticiaId);

        }


    }
}
