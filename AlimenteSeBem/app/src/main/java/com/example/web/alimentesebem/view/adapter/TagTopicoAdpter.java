package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

import java.util.List;

/**
 * Created by WEB on 16/03/2018.
 */

public class TagTopicoAdpter extends RecyclerView.Adapter {

    private List<String> tags;
    private Context context;

    public TagTopicoAdpter(List<String> tags, Context context) {
        this.tags = tags;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_tag_topico,parent,false);

        TagTopicoViewHolder holder = new TagTopicoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagTopicoViewHolder tagTopicoViewHolder = (TagTopicoViewHolder) holder;
        String tag = tags.get(position);
        ((TagTopicoViewHolder) holder).preencher(tag);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    public class TagTopicoViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvTag;
        private final CardView card;

        private long tagId;

        public TagTopicoViewHolder(View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag_topico);
            card = itemView.findViewById(R.id.cv_tag_topico);
        }

        public void preencher (String tag){

            tvTag.setText(tag);

            mudaCor();

        }

        private void mudaCor(){
            //pega primeira letra da tag
            String primeiraLetra = tvTag.getText().toString().substring(0,1).toLowerCase();
            //muda cores da tag de acordo com a letra

            if(primeiraLetra.matches("a|f|k|p|u|z")  ) {
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.laranjaPadrao));
            }else if (primeiraLetra.matches("b|g|l|q|v")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            }else if (primeiraLetra.matches("c|h|m|r|x")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lilasPadrão));
            }else if (primeiraLetra.matches("d|i|n|s|w")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.azulPadrão));
            }else {
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cianoPadrao));
            }
        }
    }
}
