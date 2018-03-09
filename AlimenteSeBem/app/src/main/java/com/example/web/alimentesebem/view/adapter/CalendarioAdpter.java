package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.CalendarioBean;
import com.example.web.alimentesebem.utils.Utilitarios;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Felipe on 07/03/2018.
 */

public class CalendarioAdpter extends RecyclerView.Adapter{
    private List<CalendarioBean> lista;
    private Context context;
    private OnItemClick onItemClick;

    public CalendarioAdpter(List<CalendarioBean> lista, Context context, OnItemClick click) {
        this.lista = lista;
        this.context = context;
        this.onItemClick = click;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_evento,parent, false);

        CalendarioViewHolder holder = new CalendarioViewHolder(view, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CalendarioViewHolder viewHolder = (CalendarioViewHolder) holder;

        CalendarioBean eventos = lista.get(position);

        ((CalendarioViewHolder) holder).preencher(eventos);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class CalendarioViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public final TextView tv_titulo_evento;
        public final TextView tv_local;
        public final TextView tv_horario;
        public final ImageView img_capa_evento;
        public final ImageView img_data;
        public final CalendarioAdpter adpter;
        private Long eventoId;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG);
        //private Intent i = new Intent(Main.getContext(), EventoActivity.class);



        public CalendarioViewHolder(final View view, final CalendarioAdpter adpter) {
            super(view);

            this.adpter = adpter;

            view.setOnClickListener(this);

            tv_titulo_evento = itemView.findViewById(R.id.tv_titulo_evento);
            tv_local = itemView.findViewById(R.id.tv_local);
            tv_horario = itemView.findViewById(R.id.tv_horario);
            img_capa_evento = itemView.findViewById(R.id.img_capa_evento);
            img_data = itemView.findViewById(R.id.img_data);
        }


        public void preencher(CalendarioBean obj){
            eventoId = obj.getId();
            tv_titulo_evento.setText(obj.getTitulo());
            tv_local.setText(obj.getLocal());
            tv_horario.setText(obj.getHorario());
          /*  byte[] foto = obj.getCapa();
            if (foto != null) {
                // Transforma o vetor de bytes de base64 para bitmap
                Bitmap bitmap = Utilitarios.bitmapFromBase64(foto);
                // Cria uma foto circular e atribui à foto
                img_capa_evento.setImageBitmap(bitmap);
            } else {

                // atribui à foto
                img_capa_evento.setBackgroundColor(Color.TRANSPARENT);
            }*/
            // Obtem a 1ª letra do nome da pessoa e converte para Maiuscula
            String dia = dtFmt.format(obj.getData()).substring(0,2);
            String mes = dtFmt.format(obj.getData()).substring(6,9);
            String diaMes = dia + " " + mes;
            // Cria um bitmap contendo a letra
            // Bitmap bitmap = Utilitarios.quadradoBitmapAndText(
            Bitmap bitmap = Utilitarios.circularBitmapAndText(
                    Color.parseColor("#ef8219"), 150, 150,diaMes );
            img_data.setImageBitmap(bitmap);

        }
        @Override
        public void onClick(View v) {
            //Toast.makeText(v.getContext(),"Titulo:" + eventoId.toString(), Toast.LENGTH_SHORT).show();
            //i.putExtra("EventoId", eventoId);
            onItemClick.onclick(eventoId);
        }


    }
}
