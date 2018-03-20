package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.ForumDaoOld;
import com.example.web.alimentesebem.model.ComentarioForumBean;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.model.UsuarioBean;
import com.example.web.alimentesebem.view.adapter.ComentarioTopicoAdpter;
import com.example.web.alimentesebem.view.adapter.TagTopicoAdpter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by WEB on 16/03/2018.
 */

public class TopicoActivity extends AppCompatActivity{
    private TextView tvTitulo,tvAutor,lblAutor,tvCategoria,lblCategoria,lblData,tvData,tvToolbar;
    private RecyclerView recyclerTag, recyclerComentario;
    private Button btnEnviar;
    private ImageButton btnVoltar;
    private EditText edComentario;
    private ForumBean topico;
    private ForumDaoOld daoOld = ForumDaoOld.instance;
    private Intent intent;
    private Long id;
    private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));

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
        tvToolbar = findViewById(R.id.toolbar_topico_text);
        btnVoltar =  findViewById(R.id.btn_voltar_topico);

        //Muda a fonte de alguns textView
        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/Gotham_Condensed_Bold.otf");
        lblAutor.setTypeface(typeFont);
        lblCategoria.setTypeface(typeFont);
        lblData.setTypeface(typeFont);
        tvTitulo.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(),"fonts/Gotham_Light.otf");
        tvCategoria.setTypeface(typeFont);
        tvAutor.setTypeface(typeFont);
        tvData.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        tvToolbar.setTypeface(typeFont);

        topico = new ForumBean();

        intent = getIntent();

        if(intent != null){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                id = bundle.getLong("TopicoId");
                topico = daoOld.getForum(id);
                if(topico != null){
                    //inicia os recycleViews de tags e comentarios
                    recyclerTag = findViewById(R.id.rv_topico_tags);
                    recyclerTag.setAdapter(new TagTopicoAdpter(topico.getTags(),this));
                    recyclerTag.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

                    recyclerComentario = findViewById(R.id.rv_comentarios);
                    recyclerComentario.setAdapter(new ComentarioTopicoAdpter(topico.getCometarios(),this));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    //faz a lista começar pelo fim
                    recyclerComentario.setLayoutManager(layoutManager);


                    lblAutor.setText("Aberto por: ");
                    tvAutor.setText(topico.getAutor().getNome());
                    lblCategoria.setText("Categoria: ");
                    tvCategoria.setText(topico.getCategoria().getNome());
                    tvTitulo.setText(topico.getTitulo());
                    lblData.setText("Aberto em ");
                    tvData.setText(dtFmt.format(topico.getDataAbertura()));
                }

            }
        }

        //Gambiarra pra testa um novo comentario no topico
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coment = edComentario.getText().toString();
               if(!TextUtils.isEmpty(coment)) {
                   List<ComentarioForumBean> comentarios = topico.getCometarios();
                   long comentarioId = comentarios.size();
                   long usuarioId = comentarios.size();
                   ComentarioForumBean comentarioNovo = new ComentarioForumBean(comentarioId, edComentario.getText().toString(),
                           new GregorianCalendar(2018, Calendar.JANUARY, 22).getTime(),
                           new UsuarioBean(usuarioId, "Eu"));
                   topico.addComentarios(comentarios, comentarioNovo);
                   InputMethodManager inputManager = (InputMethodManager)
                           getSystemService(TopicoActivity.INPUT_METHOD_SERVICE);

                   inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                   edComentario.setText("");

                   recyclerComentario.smoothScrollToPosition(recyclerComentario.getAdapter().getItemCount() -1);
                   recyclerComentario.getAdapter().notifyDataSetChanged();

               }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
