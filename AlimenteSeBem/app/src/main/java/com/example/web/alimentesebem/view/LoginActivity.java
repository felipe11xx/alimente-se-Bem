package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.Main;
import com.example.web.alimentesebem.R;

/**
 * Created by WEB on 09/03/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edLogin, edSenha;
    private TextView tvLogo;
    private Button btnLogar, btnCadastrar,  btnLogarFace,btnLogarGmail;
    private ImageView imgLogoSesi;
    private Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = findViewById(R.id.ed_login);
        edSenha = findViewById(R.id.ed_senha);
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnLogar = findViewById(R.id.btn_logar);
        btnLogarFace = findViewById(R.id.btn_logar_face);
        btnLogarGmail = findViewById(R.id.btn_logar_gmail);
        imgLogoSesi = findViewById(R.id.img_logo);
        tvLogo = findViewById(R.id.tv_logo_alimente_se);

        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        tvLogo.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Klavika_Bold.otf");
        btnLogarFace.setTypeface(typeFont);

    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();
        switch (idView){
            case R.id.btn_logar:
                String login = edLogin.getText().toString();
                String senha = edSenha.getText().toString();

                if(!login.isEmpty() && !senha.isEmpty()){
                    i = new Intent(this, MainActivity.class);
                    finish();
                    startActivity(i);
                }else{
                    String msg;


                    if(login.isEmpty()){
                        msg = "Preencha o campo de Login !";
                    }else{
                        msg = "Insira a sua Senha !";
                    }

                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_cadastrar:
                i = new Intent(this, CadastroActivity.class);
                startActivity(i);
                break;

        }





    }
}
