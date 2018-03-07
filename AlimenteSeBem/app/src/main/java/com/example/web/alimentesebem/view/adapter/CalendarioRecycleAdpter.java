package com.example.web.alimentesebem.view.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseLongArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.CalendarioDaoOld;
import com.example.web.alimentesebem.model.CalendarioBean;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.utils.Utilitarios;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by WEB on 06/03/2018.
 */

public class CalendarioRecycleAdpter extends RecyclerView.Adapter<CalendarioRecycleAdpter.CalendarioViewHolder>
                                    implements AdapterInterface{

    private CalendarioDaoOld dao = CalendarioDaoOld.instance;
    private SparseLongArray mapa;
    private Fragment fragment;
    private OnItemClickListener listener;


    public CalendarioRecycleAdpter(Fragment fragment, OnItemClickListener listener){
        this.fragment = fragment;
        this.listener = listener;
        criarMapa();
    }

    @Override
    public CalendarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CalendarioViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void setEditar(boolean value) {

    }

    @Override
    public void notificaAtualizacao() {
        criarMapa();
        notificaAtualizacao();
    }

    private void criarMapa(){
        mapa = new SparseLongArray();
        List<Long> ids =dao.listarIds();

        for (int linha = 0; linha < ids.size(); linha++){
            mapa.put(linha, ids.get(linha));
        }
    }

    public class CalendarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_titulo_evento;
        private TextView tv_local;
        private TextView tv_horario;
        private ImageView img_capa_evento;
        private ImageView img_data;
        private View view;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG);


        public CalendarioViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv_titulo_evento = itemView.findViewById(R.id.tv_titulo_evento);
            tv_local = itemView.findViewById(R.id.tv_local);
            tv_horario = itemView.findViewById(R.id.tv_horario);
            img_capa_evento = itemView.findViewById(R.id.img_capa_evento);
            img_data = itemView.findViewById(R.id.img_data);
        }


        public void setView(final CalendarioBean obj) {
            tv_titulo_evento.setText(obj.getTitulo());
            tv_local.setText(obj.getLocal());
            tv_horario.setText(obj.getHorario());

            byte[] foto = obj.getCapa();
            if (foto != null) {
                // Transforma o vetor de bytes de base64 para bitmap
                Bitmap bitmap = Utilitarios.bitmapFromBase64(foto);
                // Cria uma foto circular e atribui à foto
                img_capa_evento.setImageBitmap(bitmap);
            } else {

                // atribui à foto
                img_capa_evento.setBackgroundColor(Color.TRANSPARENT);
            }

            // Obtem a 1ª letra do nome da pessoa e converte para Maiuscula
            String dia = dtFmt.format(obj.getData()).substring(0,2);
            String mes = dtFmt.format(obj.getData()).substring(6,9);
            // Cria um bitmap contendo a letra
            Bitmap bitmap = Utilitarios.circularBitmapAndText(
                    Color.parseColor("#936A4D"), 200, 200,dia );
            img_data.setImageBitmap(bitmap);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
