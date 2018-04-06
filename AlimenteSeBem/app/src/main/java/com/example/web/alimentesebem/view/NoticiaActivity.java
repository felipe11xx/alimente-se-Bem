package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.Main;
import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.NoticiaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.utils.Utilitarios;
import com.example.web.alimentesebem.view.adapter.TagEventoAdapter;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 12/03/2018.
 */

public class NoticiaActivity extends AppCompatActivity {
    private WebView web;
    private TextView tvToolbar,tvNoticia,tvHeadline,tvData,tvTitulo;
    private ImageButton btnVoltar;
    private ImageView imgNoticia;
    private Intent i;
    private Long id;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private ProgressBar progressBar;
    private Button btnRecarregar;
    private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia2);

        tvToolbar = findViewById(R.id.toolbar_noticia_text2);
        btnVoltar = findViewById(R.id.btn_voltar_noticia2);
        btnRecarregar = findViewById(R.id.btn_recarregar_noticia2);
        tvHeadline = findViewById(R.id.tv_headline);
        tvData = findViewById(R.id.tv_data_noticia);
        tvNoticia = findViewById(R.id.tv_conteudo_noticia);
        tvTitulo = findViewById(R.id.tv_titulo_noticia);
        imgNoticia = findViewById(R.id.img_noticia);
        progressBar = findViewById(R.id.prg_noticia2);
        btnRecarregar.setVisibility(View.INVISIBLE);

        //Muda a fonte de alguns textView
        mudaFonts();

        final Bundle bundle = getIntent().getExtras();
        final Long noticiaId = (bundle != null) ? bundle.getLong("NoticiaId") : null;
        mostraViews(false);
        if (noticiaId != 0) {
            id = noticiaId;
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
        Call<NoticiaBean> call = new RetrofitConfig().getRestInterface().getNoticia(id);
        call.enqueue(new Callback<NoticiaBean>() {
            @Override
            public void onResponse(Call<NoticiaBean> call, Response<NoticiaBean> response) {

                barraProgresso.showProgress(true,progressBar);
                if (response.isSuccessful()) {
                    //mostra as views que componhe a activity
                    mostraViews(true);
                    btnRecarregar.setVisibility(View.INVISIBLE);
                    NoticiaBean obj = response.body();
                    try {
                        inicializa(obj);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),getString(R.string.falha_de_acesso)
                                ,Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NoticiaBean> call, Throwable t) {
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


    private void inicializa(NoticiaBean obj) throws Exception{

        barraProgresso.showProgress(false,progressBar);
        tvTitulo.setText(obj.getTitulo());
        tvNoticia.setText(obj.getDescricao());
        tvHeadline.setText(obj.getHeadline());
        tvData.setText(dtFmt.format(obj.getData_criacao()));

        Picasso.with(getApplicationContext()).load(obj.getImagem()).into(imgNoticia);
        // Obtem a 1ª letra do nome da pessoa e converte para Maiuscula

    }

    private void mostraViews(boolean mostra ){
        if(mostra){

            tvTitulo.setVisibility(View.VISIBLE);
            tvNoticia.setVisibility(View.VISIBLE);
            tvHeadline.setVisibility(View.VISIBLE);
            tvData.setVisibility(View.VISIBLE);
            imgNoticia.setVisibility(View.VISIBLE);

        }else {
            tvTitulo.setVisibility(View.INVISIBLE);
            tvNoticia.setVisibility(View.INVISIBLE);
            tvHeadline.setVisibility(View.INVISIBLE);
            tvData.setVisibility(View.INVISIBLE);
            imgNoticia.setVisibility(View.INVISIBLE);

        }

    }

    private void mudaFonts(){
        Typeface typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        tvNoticia.setTypeface(typeFont);


        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Light.otf");
        tvNoticia.setTypeface(typeFont);
        tvData.setTypeface(typeFont);
        tvHeadline.setTypeface(typeFont);


        typeFont = Typeface.createFromAsset(getAssets(), "fonts/tahu.ttf");
        tvToolbar.setTypeface(typeFont);
    }

    //se for usar WebView
/*    private void acessaServidor(){

        String url = "http://store.steampowered.com/?l=portuguese";
        web = findViewById(R.id.web_noticia);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                barraProgresso.showProgress(true,progressBar);

                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                barraProgresso.showProgress(false,progressBar);
                btnRecarregar.setVisibility(View.INVISIBLE);
            }

        });
        web.loadUrl(url);
    }*/
}
