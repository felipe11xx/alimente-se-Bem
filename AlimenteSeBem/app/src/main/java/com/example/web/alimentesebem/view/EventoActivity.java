package com.example.web.alimentesebem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.CalendarioDaoOld;
import com.example.web.alimentesebem.model.CalendarioBean;
import com.example.web.alimentesebem.utils.Utilitarios;

/**
 * Created by WEB on 07/03/2018.
 */

public class EventoActivity extends AppCompatActivity{

    private ImageView imgCapaEvento;
    private ImageView imgData;
    private ImageButton shareFace;
    private ImageButton shareWhat;
    private TextView tvLocalHorario;
    private TextView tvDecricao;
    private TextView tvtitulo;
    private CalendarioBean obj;
    private CalendarioDaoOld daoOld = CalendarioDaoOld.instance;
    private Long id;
    private Intent i;




    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        //getSupportActionBar().setTitle("Seu titulo aqui");

        imgCapaEvento = findViewById(R.id.img_capa_evento);
        imgData = findViewById(R.id.img_data_evento);
        shareFace = findViewById(R.id.btn_share_facebook);
        shareWhat = findViewById(R.id.btn_share_what);
        tvDecricao = findViewById(R.id.tv_descricao_evento);
        tvLocalHorario = findViewById(R.id.tv_local_horario);
        tvtitulo = findViewById(R.id.tv_evento_titulo);
        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/Gotham_Light.otf");

        tvDecricao.setTypeface(typeFont);
        tvLocalHorario.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        tvtitulo.setTypeface(typeFont);

        obj = new CalendarioBean();

        i  = getIntent();

        if(i != null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                id = bundle.getLong("EventoId");
                obj = daoOld.getEvento(id);
                if(obj !=null) {
                    tvtitulo.setText(obj.getTitulo());
                    tvDecricao.setText(obj.getDescricao());
                    tvLocalHorario.setText(obj.getLocal() + "  " +
                            obj.getHorario());

                    if (obj.getCapa() != null) {
                        imgCapaEvento.setImageBitmap(Utilitarios.bitmapFromBase64(obj.getCapa()));
                    }
                }
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                finish();
                break;
            default: break;
        }
        return true;
    }
}
