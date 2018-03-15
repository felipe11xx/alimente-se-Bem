package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.utils.Utilitarios;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Felipe on 07/03/2018.
 */

public class AgendaAdpter extends RecyclerView.Adapter{
    private List<AgendaBean> lista;
    private Context context;
    private OnItemClick onItemClick;

    public AgendaAdpter(List<AgendaBean> lista, Context context, OnItemClick click) {
        this.lista = lista;
        this.context = context;
        this.onItemClick = click;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_agenda,parent, false);

        CalendarioViewHolder holder = new CalendarioViewHolder(view, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CalendarioViewHolder viewHolder = (CalendarioViewHolder) holder;

        AgendaBean eventos = lista.get(position);

        ((CalendarioViewHolder) holder).preencher(eventos);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class CalendarioViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public final TextView tvTituloEvento;
        public final TextView tvLocal;
        public final TextView tvHorario;
        public final ImageView imgCapaEvento;
        public final ImageView imgData;
        public final AgendaAdpter adpter;
        private Long eventoId;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG);
        //private Intent i = new Intent(Main.getContext(), EventoActivity.class);



        public CalendarioViewHolder(final View view, final AgendaAdpter adpter) {
            super(view);

            this.adpter = adpter;

            view.setOnClickListener(this);

            tvTituloEvento = itemView.findViewById(R.id.tv_titulo_evento);
            tvLocal = itemView.findViewById(R.id.tv_local);
            tvHorario = itemView.findViewById(R.id.tv_horario);
            imgCapaEvento = itemView.findViewById(R.id.img_capa_evento);
            imgData = itemView.findViewById(R.id.img_data);

            Typeface typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Light.otf");
            tvLocal.setTypeface(typeFont);
            tvHorario.setTypeface(typeFont);

            typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Condensed_Bold.otf");
            tvTituloEvento.setTypeface(typeFont);

        }


        public void preencher(AgendaBean obj){
            eventoId = obj.getId();
            tvTituloEvento.setText(obj.getTitulo());
            tvLocal.setText(obj.getLocal());
            tvHorario.setText(obj.getHorario());
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
                    Color.parseColor("#ef8219"), 150, 150,diaMes,45 );
            imgData.setImageBitmap(bitmap);

        }

        @Override
        public void onClick(View v) {
            //Pega o id do eventon clincado do cardView
            onItemClick.onclick(eventoId);
        }


    }
}
