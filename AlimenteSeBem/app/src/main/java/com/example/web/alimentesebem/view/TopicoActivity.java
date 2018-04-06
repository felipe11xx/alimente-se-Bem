package com.example.web.alimentesebem.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.Main;
import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.CategoriaForumBean;
import com.example.web.alimentesebem.model.ComentarioForumBean;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.model.NutricionistaBean;
import com.example.web.alimentesebem.model.UsuarioBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.adapter.ComentarioTopicoAdpter;
import com.example.web.alimentesebem.view.adapter.TagTopicoAdpter;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WEB on 16/03/2018.
 */

public class TopicoActivity extends AppCompatActivity {
    private TextView tvTitulo, tvAutor, lblAutor, tvCategoria, lblCategoria, lblData, tvData, tvToolbar;
    private RecyclerView recyclerTag, recyclerComentario;
    private Button btnEnviar, btnRecarregar;
    private ImageButton btnVoltar;
    private EditText edComentario;
    private View separator;
    private Long id;
    private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
    private ProgressBar progressBar;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private NutricionistaBean nutricionista;
    private CategoriaForumBean categoria;
    private List<UsuarioBean> usuario;
    private ForumBean topico;
    private String email;
    private ComentarioTopicoAdpter topicoAdpter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico);

        //cria os objetos da tela
        tvAutor = findViewById(R.id.tv_autor_topico);
        tvCategoria = findViewById(R.id.tv_categoria_topico);
        tvTitulo = findViewById(R.id.tv_titulo_topico);
        tvData = findViewById(R.id.tv_data_topico);
        lblAutor = findViewById(R.id.tv_lbl_autor_topico);
        lblCategoria = findViewById(R.id.tv_lbl_categoria_topico);
        lblData = findViewById(R.id.tv_lbl_data_topico);
        btnEnviar = findViewById(R.id.btn_enviar_coment);
        edComentario = findViewById(R.id.ed_comentario);
        separator =findViewById(R.id.view_separator);
        tvToolbar = findViewById(R.id.toolbar_topico_text);
        btnVoltar = findViewById(R.id.btn_voltar_topico);
        btnRecarregar = findViewById(R.id.btn_recarregar_topico);
        progressBar = findViewById(R.id.prg_topico);
        btnRecarregar.setVisibility(View.INVISIBLE);
        //Muda a fonte de alguns textView
        mudaFonts();

        //Pega o ID do intent acessa o servidor e retorna um topico
        final Bundle bundle = getIntent().getExtras();
        final Long forumId = (bundle != null) ? bundle.getLong("ForumId") : null;
        mostraViews(false);
        if (forumId != 0) {
            id = forumId;
            acessaServidor();
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edComentario.getText().toString().isEmpty()){
                    //Usa o Email e usuario cadastrado na SharedPrefeces
                    SharedPreferences preferencesGet = getSharedPreferences("KEY", getApplicationContext().MODE_PRIVATE);
                    email = preferencesGet.getString("email", "default");

                    getUsuario(email,edComentario.getText().toString());
                    InputMethodManager inputManager = (InputMethodManager)

                            getSystemService(TopicoActivity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    edComentario.setText("");

                }
            }
        });
    }

    private void insereComentario(long idUsuario,long idForum,String comentario){

        Call<ResponseBody> call = new RetrofitConfig().getRestInterface().cadastraComentario(
                new ComentarioForumBean(comentario,idUsuario,idForum
                        , new GregorianCalendar().getTime()));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                acessaServidor();
                recyclerComentario.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TopicoActivity 2", t.getMessage());
                Toast.makeText(getApplicationContext(), "1" +R.string.falha_de_acesso, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUsuario(final String email, final String comentario){
        email.replace("@","%40");
        Call<List<UsuarioBean>> call = new RetrofitConfig().getRestInterface().getUsuarioEmail(email);
        call.enqueue(new Callback<List<UsuarioBean>>() {
            @Override
            public void onResponse(Call<List<UsuarioBean>> call, Response<List<UsuarioBean>> response) {

                if (response.isSuccessful()) {

                    usuario = response.body();
                    if (usuario.size() > 0){
                         insereComentario(usuario.get(0).getId(),topico.getId(),comentario);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<UsuarioBean>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TopicoActivity 1", t.getMessage());
            }

        });
    }

    private void acessaServidor() {
        Call<ForumBean> call = new RetrofitConfig().getRestInterface().getForum(id);

        call.enqueue(new Callback<ForumBean>() {
            @Override
            public void onResponse(Call<ForumBean> call, Response<ForumBean> response) {

                barraProgresso.showProgress(true, progressBar);
                if (response.isSuccessful()) {
                    //mostra as views que componhe a activity
                    mostraViews(true);
                    btnRecarregar.setVisibility(View.INVISIBLE);
                    topico = response.body();
                    inicializa(topico);

                }
            }

            @Override
            public void onFailure(Call<ForumBean> call, Throwable t) {
                //Em caso de erro oculta as views e mostras só o botão de recarregar
                mostraViews(false);

                btnRecarregar.setVisibility(View.VISIBLE);

                Toast.makeText(Main.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                barraProgresso.showProgress(false, progressBar);

                //acessa o servidor novamente em caso de falha
                btnRecarregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        barraProgresso.showProgress(true, progressBar);
                        btnRecarregar.setVisibility(View.INVISIBLE);
                        try {
                            acessaServidor();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), getString(R.string.falha_de_acesso)
                                           , Toast.LENGTH_LONG).show();
                        }
                    }
                });

                Log.d("TopicoActivity",t.getMessage());
            }
        });
    }

    private void inicializa(ForumBean obj)  {

        barraProgresso.showProgress(false,progressBar);
        recyclerTag = findViewById(R.id.rv_topico_tags);
        List<String> tags = new ArrayList<>();

        for (String tag:obj.getTags().split(",")) {
            tags.add(tag);
        }
        recyclerTag.setAdapter(new TagTopicoAdpter(tags, this));
        recyclerTag.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topicoAdpter = new ComentarioTopicoAdpter(obj.getComentarios(), this);
        recyclerComentario = findViewById(R.id.rv_comentarios);
        recyclerComentario.setAdapter(topicoAdpter);
        topicoAdpter.ordena();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        //faz a lista começar pelo fim
        recyclerComentario.setLayoutManager(layoutManager);

        lblAutor.setText("Aberto por: ");
        getNutricionista(obj.getId_Nutricionista());
        lblCategoria.setText("Categoria: ");
        getCategoria(obj.getId_Cat_Forum());
        tvTitulo.setText(obj.getTitulo());
        lblData.setText("Aberto em ");
        tvData.setText(dtFmt.format(obj.getData_criacao()));
    }

    private void mostraViews(boolean mostra) {
        if (mostra) {
            tvAutor.setVisibility(View.VISIBLE);
            tvCategoria.setVisibility(View.VISIBLE);
            tvTitulo.setVisibility(View.VISIBLE);
            tvData.setVisibility(View.VISIBLE);
            lblAutor.setVisibility(View.VISIBLE);
            lblCategoria.setVisibility(View.VISIBLE);
            lblData.setVisibility(View.VISIBLE);
            btnEnviar.setVisibility(View.VISIBLE);
            edComentario.setVisibility(View.VISIBLE);
            separator.setVisibility(View.VISIBLE);

        } else {
            tvAutor.setVisibility(View.INVISIBLE);
            tvCategoria.setVisibility(View.INVISIBLE);
            tvTitulo.setVisibility(View.INVISIBLE);
            tvData.setVisibility(View.INVISIBLE);
            lblAutor.setVisibility(View.INVISIBLE);
            lblCategoria.setVisibility(View.INVISIBLE);
            lblData.setVisibility(View.INVISIBLE);
            btnEnviar.setVisibility(View.INVISIBLE);
            edComentario.setVisibility(View.INVISIBLE);
            edComentario.setHint(null);
            separator.setVisibility(View.INVISIBLE);

        }

    }

    private void mudaFonts() {
        Typeface typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        lblAutor.setTypeface(typeFont);
        lblCategoria.setTypeface(typeFont);
        lblData.setTypeface(typeFont);
        tvTitulo.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Light.otf");
        tvCategoria.setTypeface(typeFont);
        tvAutor.setTypeface(typeFont);
        tvData.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(), "fonts/tahu.ttf");
        tvToolbar.setTypeface(typeFont);
    }

    private void getNutricionista(long idNutricionista){
        Call<NutricionistaBean> call = new RetrofitConfig().getRestInterface().getNutricionista(idNutricionista);
        call.enqueue(new Callback<NutricionistaBean>() {
            @Override
            public void onResponse(Call<NutricionistaBean> call, Response<NutricionistaBean> response) {

                if (response.isSuccessful()) {

                    nutricionista = response.body();
                    if(nutricionista != null)
                        tvAutor.setText( nutricionista.getNome());
                }
            }

            @Override
            public void onFailure(Call<NutricionistaBean> call, Throwable t) {
                Log.d("ForumAdapter",t.getMessage());
            }
        });

    }

    private void getCategoria(long idCategoria){

        Call<CategoriaForumBean> call = new RetrofitConfig().getRestInterface().getCategoriaForum(idCategoria);
        call.enqueue(new Callback<CategoriaForumBean>() {
            @Override
            public void onResponse(Call<CategoriaForumBean> call, Response<CategoriaForumBean> response) {

                if (response.isSuccessful()) {

                    categoria = response.body();
                    tvCategoria.setText(categoria.getNome());
                }
            }

            @Override
            public void onFailure(Call<CategoriaForumBean> call, Throwable t) {
                Log.d("ForumAdapter",t.getMessage());
            }
        });

    }
}
