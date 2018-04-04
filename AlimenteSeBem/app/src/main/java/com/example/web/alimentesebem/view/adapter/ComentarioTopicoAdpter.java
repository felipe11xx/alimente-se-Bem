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
import com.example.web.alimentesebem.model.ComentarioForumBean;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by WEB on 16/03/2018.
 */

public class ComentarioTopicoAdpter extends RecyclerView.Adapter {
    private Context context;
    private List<ComentarioForumBean> comentarios;

    public ComentarioTopicoAdpter(List<ComentarioForumBean> comentarios, Context context) {
        this.context = context;
        this.comentarios = comentarios;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_comentario,parent,false);

        ComentarioTopicoViewHolder holder = new ComentarioTopicoViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ComentarioTopicoViewHolder comentarioTopicoViewHolder =  (ComentarioTopicoViewHolder) holder;
        ComentarioForumBean comentarioForumBean = comentarios.get(position);

        try {
            ((ComentarioTopicoViewHolder)holder).preencher(comentarioForumBean);
        } catch (Exception e) {
            Toast.makeText(context,context.getResources().getString(R.string.falha_de_acesso), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class ComentarioTopicoViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvAutor,tvData, tvComentario;
        private final ComentarioTopicoAdpter adpter;
        private long comentarioId;
        private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG);

        public ComentarioTopicoViewHolder(View itemView,ComentarioTopicoAdpter adpter) {

            super(itemView);
            this.adpter = adpter;

            tvAutor = itemView.findViewById(R.id.tv_autor_comentario);
            tvComentario = itemView.findViewById(R.id.tv_comentario);
            tvData = itemView.findViewById(R.id.tv_data_comentario);

            Typeface typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Condensed_Bold.otf");
            tvAutor.setTypeface(typeFont);
            typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Light.otf");
            tvData.setTypeface(typeFont);
            tvComentario.setTypeface(typeFont);
        }


        public void preencher(ComentarioForumBean comentario) throws Exception{
            comentarioId = comentario.getId_Comentario();

            tvAutor.setText(comentario.getUsuario().getNome());
            tvComentario.setText(comentario.getComentario());
            tvData.setText(dtFmt.format(comentario.getData_Criacao()));
        }
    }
}
