package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;

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
    private ImageButton btnVoltar;
    private TextView tvLogo;
    private UsuarioBean usuarioBean;
    private static final int CODE_CLIENTE = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edNome = findViewById(R.id.ed_nome);
        edEmail = findViewById(R.id.ed_email);
        edSenha =findViewById(R.id.ed_senha);
        edConfirma = findViewById(R.id.ed_confirma_senha);
        btnOK = findViewById(R.id.btn_submit);
        btnVoltar = findViewById(R.id.btn_voltar_cadastrar);

        tvLogo = findViewById(R.id.toolbar_cadastro_text);
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

    //Realiza o cadastro e trata as excessões
    public void cadastrar(View v) {
        String senha = edSenha.getText().toString();
        String confirma = edConfirma.getText().toString();
        String email = edEmail.getText().toString();
        String nome = edNome.getText().toString();

        View focus = null;
        boolean cancel = false;

        if(!senha.equals(confirma)){
            //Realiza o cadastro e trata as excessões  // Toast.makeText(getApplicationContext(),, Toast.LENGTH_LONG).show();
            edConfirma.setError(getString(R.string.erro_cofirma_igual_senha));
            focus = edConfirma;
            cancel = true;
        }

        if(confirma.length() < 8 ){
            edConfirma.setError(getString(R.string.erro_senha_deve_conter));
            focus = edConfirma;
            cancel = true;
        }

        if(confirma.isEmpty()){
            edConfirma.setError(getString(R.string.erro_confirma_n_preechida));
            focus = edConfirma;
            cancel = true;
        }

        if(senha.length() < 8 ){
            edSenha.setError(getString(R.string.erro_senha_deve_conter));
            focus = edSenha;
            cancel = true;
        }

        if(senha.isEmpty()){
            edSenha.setError(getString(R.string.erro_senha_n_preechida));
            focus = edSenha;
            cancel = true;
        }

        if(email.isEmpty()){
            edEmail.setError(getString(R.string.erro_email_n_preechida));
            focus = edEmail;
            cancel = true;
        }

        if(!email.contains("@")){

            edEmail.setError(getString(R.string.erro_email_invalido));
            focus = edEmail;
            cancel = true;
        }

        if(nome.isEmpty()){
            edNome.setError(getString(R.string.erro_nome_n_preechida));
            focus = edNome;
            cancel = true;
        }

        if(!cancel){
            Call<ResponseBody> call = new RetrofitConfig().getRestInterface().cadastraUsuario(new UsuarioBean(edNome.getText().toString()
                    ,edEmail.getText().toString(),edSenha.getText().toString()));

            Call<UsuarioBean> callBean = new RetrofitConfig().getRestInterface().cadastrarUsuarioBean(new UsuarioBean(edNome.getText().toString()
                    ,edEmail.getText().toString(),edSenha.getText().toString()));

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //Se cadastrar com sucesso abre tela de login
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.sucesso_cadastro), Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), R.string.falha_de_acesso, Toast.LENGTH_LONG).show();
                }
            });
        }else {

            focus.requestFocus();
        }
    }

    public void voltar(View v){
        finish();
    }
}
