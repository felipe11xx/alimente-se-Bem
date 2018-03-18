package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.VideoBean;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Felipe on 18/03/2018.
 */

public class VideoAdpeter extends RecyclerView.Adapter {

    private Context context;
    private List<VideoBean> lista;
    private OnItemClick click;

    public VideoAdpeter(Context context, List<VideoBean> lista, OnItemClick click) {
        this.context = context;
        this.lista = lista;
        this.click = click;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context)
               .inflate(R.layout.detalhe_video,parent,false);

       VideoViewHolder holder = new VideoViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoViewHolder viewHolder = (VideoViewHolder) holder;
        VideoBean video = lista.get(position);

        ((VideoViewHolder)holder).preencher(video);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView tvTitulo,tvDesc,tvData;
        public final WebView webVideo;
        private Long videoId;
        public VideoAdpeter adpeter;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG,new Locale("pt","BR"));


        public VideoViewHolder(View itemView,VideoAdpeter adpeter) {
            super(itemView);
            this.adpeter = adpeter;
            itemView.setOnClickListener(this);

            tvData = itemView.findViewById(R.id.tv_data_video);
            tvTitulo = itemView.findViewById(R.id.tv_titulo_video);
            tvDesc = itemView.findViewById(R.id.tv_video_desc);
            webVideo = itemView.findViewById(R.id.web_video);

        }

        public void preencher (VideoBean video){

            videoId = video.getId();
            tvDesc.setText(video.getDescricao());
            tvTitulo.setText(video.getTitulo());
            tvData.setText(dtFmt.format(video.getData()));
            webVideo.getSettings().setJavaScriptEnabled(true);
            webVideo.loadUrl(video.getUrl());

        }

        @Override
        public void onClick(View v) {
            click.onClick(videoId);
        }
    }
}
