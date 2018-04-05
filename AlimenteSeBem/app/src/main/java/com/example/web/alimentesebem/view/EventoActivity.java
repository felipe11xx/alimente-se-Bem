package com.example.web.alimentesebem.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.Main;
import com.example.web.alimentesebem.R;
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

/**
 * Created by WEB on 07/03/2018.
 */

public class EventoActivity extends AppCompatActivity {

    private ImageView imgCapaEvento, imgData;
    private ImageButton btnShare, btnVoltar;
    private TextView tvLocalHorario, tvDecricao, tvtitulo, lblPreco, tvPreco, tvToolbar, tvUnidade;
    private Long id;
    private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
    private RecyclerView recyclerView;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private ProgressBar progressBar;
    private Button btnRecarregar;

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
        tvUnidade = findViewById(R.id.tv_unidade);
        tvToolbar = findViewById(R.id.toolbar_evento_text);
        btnVoltar = findViewById(R.id.btn_voltar_evento);
        progressBar = findViewById(R.id.prg_evento);
        btnRecarregar = findViewById(R.id.btn_recarregar_evento);
        recyclerView = findViewById(R.id.rv_tag);
        btnRecarregar.setVisibility(View.INVISIBLE);

        //Muda a fonte de alguns textView
        mudaFonts();

        //usa o ID no Bundle para atribuir valor aos elementos da tela

        final Bundle bundle = getIntent().getExtras();
        final Long eventoId = (bundle != null) ? bundle.getLong("EventoId") : null;
        mostraViews(false);
        if (eventoId != 0) {
            id = eventoId;
                acessaServidor();

        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void acessaServidor(){
        Call<AgendaBean> call = new RetrofitConfig().getRestInterface().getEvento(id);
        call.enqueue(new Callback<AgendaBean>() {
            @Override
            public void onResponse(Call<AgendaBean> call, Response<AgendaBean> response) {

                barraProgresso.showProgress(true,progressBar);
                if (response.isSuccessful()) {
                    //mostra as views que componhe a activity
                    mostraViews(true);
                    btnRecarregar.setVisibility(View.INVISIBLE);
                    AgendaBean obj = response.body();
                    try {
                        inicializa(obj);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),getString(R.string.falha_de_acesso)
                                            ,Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AgendaBean> call, Throwable t) {
                //Em caso de erro oculta as views e mostras só o botão de recarregar
                mostraViews(false);

                btnRecarregar.setVisibility(View.VISIBLE);

                Toast.makeText(Main.getContext(),R.string.falha_de_acesso,Toast.LENGTH_SHORT).show();
                barraProgresso.showProgress(false,progressBar);

                //acessa o servidor novamente em caso de falha
                btnRecarregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        barraProgresso.showProgress(true,progressBar);
                        btnRecarregar.setVisibility(View.INVISIBLE);
                        try {
                            acessaServidor();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),R.string.falha_de_acesso,Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void inicializa(AgendaBean obj) throws Exception{

        barraProgresso.showProgress(false,progressBar);
        tvtitulo.setText(obj.getTitulo());
        tvDecricao.setText(obj.getDescricao());
        String horario = String.valueOf(obj.getData_Evento());
        tvLocalHorario.setText(obj.getUnidades_Sesi().getLocal() + " horário "  + horario.substring(11,16));

        tvUnidade.setText(obj.getUnidades_Sesi().getNome());

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
        String mes = dtFmt.format(obj.getData_Evento()).substring(5, 9).toUpperCase().trim();
        String diaMes = dia + " " + mes;
        // Cria um bitmap contendo Dia e mês
        // Bitmap bitmap = Utilitarios.quadradoBitmapAndText(
        Bitmap bitmap = Utilitarios.circularBitmapAndText(
                Color.parseColor("#ef8219"), 150, 150, diaMes, 40);
        imgData.setImageBitmap(bitmap);

        List<String> tags = new ArrayList<>();

        for (String tag:obj.getTags().split(",")) {
            tags.add(tag);
        }

        recyclerView.setAdapter(new TagEventoAdapter(tags, getApplicationContext()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
       // Toast.makeText(this,dtFmt.format(obj.getData_Evento()),Toast.LENGTH_LONG).show();
    }

    private void mostraViews(boolean mostra ){
       if(mostra){
           imgCapaEvento.setVisibility(View.VISIBLE);
           imgData.setVisibility(View.VISIBLE);
           btnShare.setVisibility(View.VISIBLE);
           tvDecricao.setVisibility(View.VISIBLE);
           tvLocalHorario.setVisibility(View.VISIBLE);
           tvtitulo.setVisibility(View.VISIBLE);
           lblPreco.setVisibility(View.VISIBLE);
           tvPreco.setVisibility(View.VISIBLE);
           recyclerView.setVisibility(View.VISIBLE);
           tvUnidade.setVisibility(View.VISIBLE);
       }else {
           imgCapaEvento.setVisibility(View.INVISIBLE);
           imgData.setVisibility(View.INVISIBLE);
           btnShare.setVisibility(View.INVISIBLE);
           tvDecricao.setVisibility(View.INVISIBLE);
           tvLocalHorario.setVisibility(View.INVISIBLE);
           tvtitulo.setVisibility(View.INVISIBLE);
           lblPreco.setVisibility(View.INVISIBLE);
           tvPreco.setVisibility(View.INVISIBLE);
           recyclerView.setVisibility(View.INVISIBLE);
           tvUnidade.setVisibility(View.INVISIBLE);
       }

    }

    private void mudaFonts(){
        Typeface typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        tvtitulo.setTypeface(typeFont);
        lblPreco.setTypeface(typeFont);

        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Light.otf");
        tvDecricao.setTypeface(typeFont);
        tvLocalHorario.setTypeface(typeFont);
        tvPreco.setTypeface(typeFont);
        tvUnidade.setTypeface(typeFont);

        typeFont = Typeface.createFromAsset(getAssets(), "fonts/tahu.ttf");
        tvToolbar.setTypeface(typeFont);
    }
}
