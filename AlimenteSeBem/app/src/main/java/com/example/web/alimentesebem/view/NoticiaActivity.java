package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.utils.Utilitarios;


/**
 * Created by WEB on 12/03/2018.
 */

public class NoticiaActivity extends AppCompatActivity {
    private WebView web;
    private TextView tvToolbar;
    private ImageButton btnVoltar;
    private Intent i;
    private Long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_noticia);

        tvToolbar = findViewById(R.id.toolbar_noticia_text);
        btnVoltar = findViewById(R.id.btn_voltar_noticia);

        i  = getIntent();

        if(i != null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                id = bundle.getLong("NoticiaId");
              /*  obj = daoOld.getEvento(id);
                if(obj !=null) {
                    tvtitulo.setText(obj.getTitulo());
                    tvDecricao.setText(obj.getDescricao());

                }*/
            }
        }

        String url = "http://store.steampowered.com/?l=portuguese";
        web = findViewById(R.id.web_noticia);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);

        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        tvToolbar.setTypeface(typeFont);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
