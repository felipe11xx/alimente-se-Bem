package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by WEB on 14/03/2018.
 */

public class TagEventoAdapter extends RecyclerView.Adapter {
    private List<String> tags;
    private Context context;

    public TagEventoAdapter(List<String> tags, Context context){
        this.context = context;
        this.tags = tags;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_tag_evento,parent,false);

        TagEventoViewHolder holder = new TagEventoViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagEventoViewHolder TagHolder = (TagEventoViewHolder) holder;
        String tag = tags.get(position);

        try{
            ((TagEventoViewHolder) holder).preencher(tag);
        }catch (Exception e){
            Toast.makeText(context, context.getResources().getString(R.string.falha_de_acesso), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class TagEventoViewHolder extends RecyclerView.ViewHolder{
       // private final List<String> tags;
        private final TagEventoAdapter adapter;
        private final TextView tvTag;
        private final CardView card;

        public TagEventoViewHolder(View view, TagEventoAdapter adapter) {
            super(view);
            this.adapter = adapter;
            tvTag = view.findViewById(R.id.tv_tag_evento);
            card = view.findViewById(R.id.cv_tag_evento);
            // tvTag.setBackgroundColor(Color.WHITE);
        }

        public void preencher(String tag) throws  Exception{
            tvTag.setText(tag);

            mudaCor();

        }
        private void mudaCor(){
            //pega primeira letra da tag
            String primeiraLetra = tvTag.getText().toString().substring(0,1).toLowerCase();
            //muda cores da tag de acordo com a letra

            if(primeiraLetra.matches("a|f|k|p|u|w")  ) {
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.laranjaPadrao));
            }else if (primeiraLetra.matches("b|g|l|q|v")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            }else if (primeiraLetra.matches("c|h|m|r|x")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lilasPadrão));
            }else if (primeiraLetra.matches("d|i|n|s|y")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.azulPadrão));
            }else {
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cianoPadrao));
            }
        }

    }
}
