package com.example.web.alimentesebem.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_evento);
    }
}
