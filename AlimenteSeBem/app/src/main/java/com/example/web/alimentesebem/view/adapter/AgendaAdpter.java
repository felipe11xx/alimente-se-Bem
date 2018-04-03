package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.utils.Utilitarios;
import com.example.web.alimentesebem.view.EventoActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Felipe on 07/03/2018.
 */

public class AgendaAdpter extends RecyclerView.Adapter implements AdapterInterface{
    private ArrayList<AgendaBean> lista;
    private Context context;
    private final List<AgendaBean> agendaLista;

    public AgendaAdpter(List<AgendaBean>agendaLista, Context context) {
        this.agendaLista = agendaLista;
        this.context = context;
        this.lista = new ArrayList<>();
        this.lista.addAll(agendaLista);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_agenda,parent, false);

        return  new EventoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EventoViewHolder viewHolder = (EventoViewHolder) holder;

        AgendaBean evento = agendaLista.get(position);

        //caso tenha erro ao tentar acessar o servidor
        try {
            ((EventoViewHolder) holder).preencher(evento);
        } catch (Exception e) {
            Toast.makeText(context,context.getResources().getString(R.string.falha_de_acesso),Toast.LENGTH_SHORT).show();
        }

    }

    private void ordena(List<AgendaBean> eventos){

        for (AgendaBean a:eventos) {

            Date date = new Date();
            date.getTime();
            if(date.getTime() < a.getData_Evento().getTime()){
                eventos.remove(a);
            }

        }
            Collections.sort(eventos, new Comparator<AgendaBean>() {
                @Override
                public int compare(AgendaBean obj1, AgendaBean obj2) {
                    return obj1.getData_Evento().compareTo(obj2.getData_Evento());
                }
            });

    }

    @Override
    public int getItemCount() {
        return agendaLista.size();
    }

    @Override
    public void filtrarPorTitulo(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        agendaLista.clear();
        if (charText.length() == 0) {
            agendaLista.addAll(lista);
        } else {
            for (AgendaBean l : lista) {
                if (l.getTitulo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    agendaLista.add(l);
                }
            }
        }

    }

    public class EventoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private final TextView tvTituloEvento;
        private final TextView tvLocal;
        private final TextView tvHorario;
        private final ImageView imgCapaEvento;
        private final ImageView imgData;
        private final AgendaAdpter adpter;
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

        private void preencher(AgendaBean obj) throws Exception{
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
            String mes = dtFmt.format(obj.getData_Evento()).substring(5,9).toUpperCase();
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
