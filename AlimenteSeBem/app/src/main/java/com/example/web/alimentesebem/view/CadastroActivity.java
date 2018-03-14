package com.example.web.alimentesebem.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

/**
 * Created by WEB on 12/03/2018.
 */

public class CadastroActivity extends AppCompatActivity {
    private EditText edNome,edEmail,edSenha,edConfirma;
    private Button btnOK;
    private TextView tvLogo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        edNome = findViewById(R.id.ed_nome);
        edEmail = findViewById(R.id.ed_email);
        edSenha =findViewById(R.id.ed_senha);
        edConfirma = findViewById(R.id.ed_confirma_senha);
        btnOK = findViewById(R.id.btn_submit);

        tvLogo = findViewById(R.id.tv_logo_alimente_se);
        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        tvLogo.setTypeface(typeFont);


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
