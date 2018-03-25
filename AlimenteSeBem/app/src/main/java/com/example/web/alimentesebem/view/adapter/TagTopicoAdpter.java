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
import com.example.web.alimentesebem.model.TagForumBean;

import java.util.List;

/**
 * Created by WEB on 16/03/2018.
 */

public class TagTopicoAdpter extends RecyclerView.Adapter {

    private List<TagForumBean> tags;
    private Context context;

    public TagTopicoAdpter(List<TagForumBean> tags, Context context) {
        this.tags = tags;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_tag_topico,parent,false);

        TagTopicoViewHolder holder = new TagTopicoViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagTopicoViewHolder tagTopicoViewHolder = (TagTopicoViewHolder) holder;
        TagForumBean tag = tags.get(position);

        ((TagTopicoViewHolder) holder).preencher(tag);

    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    public class TagTopicoViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvTag;
        private final CardView card;
        private final TagTopicoAdpter adpter;
        private long tagId;

        public TagTopicoViewHolder(View itemView,TagTopicoAdpter adpter) {
            super(itemView);
            this.adpter = adpter;
            tvTag = itemView.findViewById(R.id.tv_tag_topico);
            card = itemView.findViewById(R.id.cv_tag_topico);
        }

        public void preencher (TagForumBean tag){
            tagId = tag.getId();
            tvTag.setText(tag.getTag());

            //pega primeira letra da tag
            String primeiraLetra = tvTag.getText().toString().substring(0,1).toLowerCase();
            //muda cores da tag de acordo com a letra
            if(primeiraLetra.equals("b") ) {
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.laranjaPadrao));
            }else if (primeiraLetra.equals("k")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            }else if (primeiraLetra.equals("v")){
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lilasPadrão));
            }else{
                card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.azulPadrão));
            }
        }
    }
}