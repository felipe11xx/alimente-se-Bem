package com.example.web.alimentesebem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.AgendaDaoOld;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.utils.Utilitarios;
import com.example.web.alimentesebem.view.adapter.TagEventoAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by WEB on 07/03/2018.
 */

public class EventoActivity extends AppCompatActivity {

    private ImageView imgCapaEvento, imgData;
    private ImageButton btnShare, btnVoltar;
    private TextView tvLocalHorario, tvDecricao, tvtitulo, lblPreco, tvPreco, tvToolbar;
    //  private AgendaDaoOld daoOld = AgendaDaoOld.instance;
    private Long id;
    private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
    private List<String> tags;
    private RecyclerView recyclerView;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        //cria os objetos da tela
        imgCapaEvento = findViewById(R.id.img_capa_evento);
        imgData = findViewById(R.id.img_data_evento);
        btnShare = findViewById(R.id.btn_share);
        tvDecricao = findViewById(R.id.tv_descricao_evento);
        tvLocalHorario = findViewById(R.id.tv_local_horario);
        tvtitulo = findViewById(R.id.tv_evento_titulo);
        lblPreco = findViewById(R.id.lbl_preco);
        tvPreco = findViewById(R.id.tv_preco);
        tvToolbar = findViewById(R.id.toolbar_evento_text);
        btnVoltar = findViewById(R.id.btn_voltar_evento);

        //Muda a fonte de alguns textView
        Typeface typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        tvtitulo.setTypeface(typeFont);
        lblPreco.setTypeface(typeFont);

        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Light.otf");
        tvDecricao.setTypeface(typeFont);
        tvLocalHorario.setTypeface(typeFont);
        tvPreco.setTypeface(typeFont);

        typeFont = Typeface.createFromAsset(getAssets(), "fonts/tahu.ttf");
        tvToolbar.setTypeface(typeFont);

        tags = new ArrayList<>();
        tags.add("bacon1");
        tags.add("Churrasco1");
        tags.add("frango1");
        tags.add("fit1");
        tags.add("bacon2");
        tags.add("Churrasco2");
        tags.add("frango2");
        tags.add("fit2");
        tags.add("bacon3");
        tags.add("Churrasco3");
        tags.add("frango3");
        tags.add("fit3");

        recyclerView = findViewById(R.id.rv_tag);
        recyclerView.setAdapter(new TagEventoAdapter(tags, this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        //usa o ID no Bundle para atribuir valor aos elementos da tela

        final Bundle bundle = getIntent().getExtras();
        final Long EventoId = (bundle != null) ? bundle.getLong("EventoId") : null;

        if (EventoId != 0) {
            id = EventoId;

            Call<AgendaBean> call = new RetrofitConfig().getRestInterface().getEvento(id);
            call.enqueue(new Callback<AgendaBean>() {
                @Override
                public void onResponse(Call<AgendaBean> call, Response<AgendaBean> response) {
                    if (response.isSuccessful()) {
                        AgendaBean obj = response.body();
                        tvtitulo.setText(obj.getTitulo());
                        tvDecricao.setText(obj.getDescricao());
                        tvLocalHorario.setText(obj.getUnidades_Sesi().getLocal() + "  " +
                                obj.getHorario());

                        if (obj.getPreco() == 0) {
                            tvPreco.setText("Gratuito");
                        } else {
                            tvPreco.setText("R$: " + String.valueOf(String.format("%.2f", obj.getPreco())));
                        }

                        if (obj.getCapa() != null) {
                            imgCapaEvento.setImageBitmap(Utilitarios.bitmapFromBase64(obj.getCapa()));
                        }
                        // Obtem a 1ª letra do nome da pessoa e converte para Maiuscula
                        String dia = dtFmt.format(obj.getData_Evento()).substring(0, 2);
                        String mes = dtFmt.format(obj.getData_Evento()).substring(6, 9);
                        String diaMes = dia + " " + mes;
                        // Cria um bitmap contendo Dia e mês
                        // Bitmap bitmap = Utilitarios.quadradoBitmapAndText(
                        Bitmap bitmap = Utilitarios.circularBitmapAndText(
                                Color.parseColor("#ef8219"), 150, 150, diaMes, 45);
                        imgData.setImageBitmap(bitmap);
                    }
                }

                @Override
                public void onFailure(Call<AgendaBean> call, Throwable t) {
                    Log.d("EventoActivity: ", t.getMessage().toString());
                }
            });


        }


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
