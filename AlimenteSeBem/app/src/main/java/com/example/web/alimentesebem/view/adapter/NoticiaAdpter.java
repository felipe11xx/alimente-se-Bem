package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.view.NoticiaActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Felipe on 04/03/2018.
 */

public class NoticiaAdpter extends RecyclerView.Adapter implements AdapterInterface{

   private ArrayList<NoticiaBean> lista;
   private Context context;
   private List<NoticiaBean> listaNoticias;

   public NoticiaAdpter(List<NoticiaBean> listaNoticias, Context context) {
       this.listaNoticias = listaNoticias;
       this.context = context;
       this.lista = new ArrayList<>();
       this.lista.addAll(listaNoticias);
   }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_noticias, parent,false);

        NoticiaViewHolder holder = new NoticiaViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        NoticiaBean noticiaBean = listaNoticias.get(position);

            ((NoticiaViewHolder)holder).preencher(noticiaBean);

    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    @Override
    public void filtrarPorTitulo(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listaNoticias.clear();
        if (charText.length() == 0) {
            listaNoticias.addAll(lista);
        } else {
            for (NoticiaBean l : lista) {
                if (l.getTitulo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listaNoticias.add(l);
                }
            }
        }

    }

    public class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTitulo;
        private final TextView tvConteudo;
        private final TextView tvPublicacao;
        private final ImageView imgCapa;
        private Long noticiaId;

        private DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG,new Locale("pt","BR"));

        private NoticiaViewHolder(final View view) {
            super(view);

            view.setOnClickListener(this);

            tvTitulo = view.findViewById(R.id.tv_titulo_forum);
            tvConteudo = view.findViewById(R.id.tv_autor_noticia);
            tvPublicacao = view.findViewById(R.id.tv_data_noticia);
            imgCapa = view.findViewById(R.id.img_capa_noticia);

            Typeface typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Condensed_Bold.otf");
            tvTitulo.setTypeface(typeFont);
            typeFont = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham_Light.otf");
            tvConteudo.setTypeface(typeFont);
            tvPublicacao.setTypeface(typeFont);

        }

        private void preencher(NoticiaBean noticiaBean) {

            noticiaId = noticiaBean.getId();
            tvTitulo.setText(noticiaBean.getTitulo());
            tvConteudo.setText(noticiaBean.getDescricao());
            tvPublicacao.setText(dtFmt.format(noticiaBean.getData_criacao()) );
            Picasso.with(context).load(noticiaBean.getImagem()).into(imgCapa);
            imgCapa.setAdjustViewBounds(true);

        }


        @Override
        public void onClick(View v) {
            //Log.d("idNoticia: ", noticiaId.toString());
            Intent intent = new Intent(v.getContext(),NoticiaActivity.class );
            intent.putExtra("NoticiaId", noticiaId);
            v.getContext().startActivity(intent);
        }


    }
}
