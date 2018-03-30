package com.example.web.alimentesebem.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.utils.Utilitarios;
import com.example.web.alimentesebem.view.EventoActivity;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Felipe on 07/03/2018.
 */

public class AgendaAdpter extends RecyclerView.Adapter{
    private List<AgendaBean> lista;
    private Context context;
    private List<AgendaAdpter> agendaAdpter;


    public AgendaAdpter(List<AgendaBean> lista, Context context) {
        this.lista = lista;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_agenda,parent, false);

        EventoViewHolder holder = new EventoViewHolder(view, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EventoViewHolder viewHolder = (EventoViewHolder) holder;

/*        // Obtém a identificação da preferência para Ordenação
        String ordemPreference = activity.getResources().getString(R.string.ordem_key);
        // Obtém o valor padrão para a Ordenação
        String ordemDefault = activity.getResources().getString(R.string.ordem_default);
        // Obtém o recurso de leitura de preferências
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        // Localiza a configuração selecionada para Ordenação de Albuns
        String ordem = preferences.getString(ordemPreference, ordemDefault);*/

        ordena("Titulo",lista);

        AgendaBean eventos = lista.get(position);

        ((EventoViewHolder) holder).preencher(eventos);

    }

    private void ordena(String ordem, List<AgendaBean> eventos){

        if(ordem.equals("Titulo")){
            Collections.sort(eventos, new Comparator<AgendaBean>() {
                @Override
                public int compare(AgendaBean obj1, AgendaBean obj2) {
                    return obj1.getTitulo().compareToIgnoreCase(obj2.getTitulo());
                }
            });
        }else{
            Collections.sort(eventos, new Comparator<AgendaBean>() {
                @Override
                public int compare(AgendaBean obj1, AgendaBean obj2) {
                    return obj1.getData_Evento().compareTo(obj2.getData_Evento());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public final TextView tvTituloEvento;
        public final TextView tvLocal;
        public final TextView tvHorario;
        public final ImageView imgCapaEvento;
        public final ImageView imgData;
        public final AgendaAdpter adpter;
        private Long eventoId;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt","BR"));


        public EventoViewHolder(final View view, final AgendaAdpter adpter) {
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
            tvLocal.setText(obj.getUnidades_Sesi().getNome() );
            String horario = String.valueOf(obj.getData_Evento());
            tvHorario.setText(horario.substring(11,16));
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
            // Cria um bitmap contendo o dia e Mês
            String dia = dtFmt.format(obj.getData_Evento()).substring(0,2);
            String mes = dtFmt.format(obj.getData_Evento()).substring(6,9).toUpperCase();
            String diaMes = dia + " " + mes;
            Bitmap bitmap = Utilitarios.circularBitmapAndText(
                    Color.parseColor("#ef8219"), 150, 150,diaMes,40 );
            imgData.setImageBitmap(bitmap);

        }

        @Override
        public void onClick(View v) {
            //Pega o id do evento clincado do cardView
            Intent intent = new Intent(v.getContext(),EventoActivity.class );
            intent.putExtra("EventoId", eventoId);
            v.getContext().startActivity(intent);
        }

    }
}
