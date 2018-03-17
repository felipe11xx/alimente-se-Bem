package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.ForumBean;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by WEB on 15/03/2018.
 */

public class ForumAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<ForumBean> lista;
    private OnItemClick onItemClick;

    public ForumAdapter(Context context, List<ForumBean> lista, OnItemClick onItemClick) {
        this.context = context;
        this.lista = lista;
        this.onItemClick = onItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_forum,parent,false);

        ForumViewHolder holder = new ForumViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ForumViewHolder forumViewHolder = (ForumViewHolder) holder;

        ForumBean forum = lista.get(position);

        ((ForumViewHolder) holder).preencher(forum);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView tvTitulo, tvAutor,tvCategoria,tvDataAbertura,tvLblAutor;
        private Long forumId;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt","BR"));
        public final ForumAdapter adapter;

        public ForumViewHolder(final View view, final ForumAdapter adapter) {
            super(view);
            this.adapter = adapter;

            view.setOnClickListener(this);

            tvTitulo = view.findViewById(R.id.tv_titulo_forum);
            tvAutor = view.findViewById(R.id.tv_autor);
            tvCategoria = view.findViewById(R.id.tv_categoria_forum);
            tvDataAbertura = view.findViewById(R.id.tv_data_forum);
            tvLblAutor = view.findViewById(R.id.tv_lbl_autor);
            Typeface typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Light.otf");
            tvAutor.setTypeface(typeFont);
            tvCategoria.setTypeface(typeFont);
            tvDataAbertura.setTypeface(typeFont);
            typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Condensed_Bold.otf");
            tvTitulo.setTypeface(typeFont);
            tvLblAutor.setTypeface(typeFont);

        }

        public void preencher(ForumBean obj){
            forumId = obj.getId();
            tvTitulo.setText(obj.getTitulo());
            tvLblAutor.setText("Aberto por: ");
            tvAutor.setText( obj.getAutor().getNome());
            tvCategoria.setText(obj.getCategoria().getNome());
            tvDataAbertura.setText(dtFmt.format(obj.getDataAbertura()));

        }

        @Override
        public void onClick(View v) {
            //Pega o id do topico clincado do cardView
           // Toast.makeText(context,"ID"+forumId, Toast.LENGTH_SHORT).show();
            onItemClick.onClick(forumId);
        }
    }

}
