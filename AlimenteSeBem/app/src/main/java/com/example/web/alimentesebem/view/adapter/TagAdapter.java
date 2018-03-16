package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

import java.util.List;

/**
 * Created by WEB on 14/03/2018.
 */

public class TagAdapter extends RecyclerView.Adapter {
    private List<String> tags;
    private Context context;

    public TagAdapter(List<String> tags,Context context){
        this.context = context;
        this.tags = tags;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_tag,parent,false);

        TagViewHolder holder = new TagViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagViewHolder TagHolder = (TagViewHolder) holder;
        String tag = tags.get(position);
        ((TagViewHolder) holder).preencher(tag);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder{
       // private final List<String> tags;
        private final TagAdapter adapter;
        private final TextView tvTag;

        public TagViewHolder(View view, TagAdapter adapter) {
            super(view);
            this.adapter = adapter;
            tvTag = view.findViewById(R.id.tv_tag);
            // tvTag.setBackgroundColor(Color.WHITE);
        }

        public void preencher(String tag){
            tvTag.setText(tag);
        }
    }
}
