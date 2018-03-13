package com.example.web.alimentesebem.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

/**
 * Created by WEB on 12/03/2018.
 */

public class CadastroActivity extends AppCompatActivity {
    private EditText edNome,edEmail,edSenha,edConfirma;
    private ImageButton imgOK;
    private TextView tvLogo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edNome = findViewById(R.id.ed_nome);
        edEmail = findViewById(R.id.ed_email);
        edSenha =findViewById(R.id.ed_senha);
        edConfirma = findViewById(R.id.ed_confirma_senha);
        imgOK = findViewById(R.id.img_submit);

        tvLogo = findViewById(R.id.tv_logo_alimente_se);
        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        tvLogo.setTypeface(typeFont);


    }
}
