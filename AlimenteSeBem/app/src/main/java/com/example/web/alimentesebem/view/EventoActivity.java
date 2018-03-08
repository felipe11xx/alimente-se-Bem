package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        imgCapaEvento = findViewById(R.id.img_capa_evento);
        imgData = findViewById(R.id.img_data_evento);
        shareFace = findViewById(R.id.btn_share_facebook);
        shareWhat = findViewById(R.id.btn_share_what);
        tvDecricao = findViewById(R.id.tv_descricao_evento);
        tvLocalHorario = findViewById(R.id.tv_local_horario);
        tvtitulo = findViewById(R.id.tv_evento_titulo);

        obj = new CalendarioBean();

        i = new Intent(this, TabCalendario.class);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getLong("EventoId");
            obj = daoOld.getEvento(id);
            tvtitulo.setText(obj.getTitulo().toString());
            tvDecricao.setText(obj.getDescricao().toString());
            tvLocalHorario.setText(obj.getLocal().toString() + "  " +
                                    obj.getHorario().toString());

            if(obj.getCapa() != null){
                imgCapaEvento.setImageBitmap(Utilitarios.bitmapFromBase64(obj.getCapa()));
            }

        }


        setContentView(R.layout.activity_evento);
    }
}
