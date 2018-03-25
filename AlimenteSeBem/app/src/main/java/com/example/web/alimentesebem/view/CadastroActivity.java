package com.example.web.alimentesebem.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.UsuarioBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 12/03/2018.
 */

public class CadastroActivity extends AppCompatActivity {
    private  EditText edNome,edEmail,edSenha,edConfirma;
    private Button btnOK;
    private TextView tvLogo;
    private UsuarioBean usuarioBean;
    private static final int CODE_CLIENTE = 0;


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


        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = new RetrofitConfig().getRestInterface().cadastraUsuario(new UsuarioBean(edNome.getText().toString()
                                        ,edEmail.getText().toString(),edSenha.getText().toString()));

                Call<UsuarioBean> callBean = new RetrofitConfig().getRestInterface().cadastrarUsuarioBean(new UsuarioBean(edNome.getText().toString()
                        ,edEmail.getText().toString(),edSenha.getText().toString()));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });

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
