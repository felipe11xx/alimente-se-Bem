package com.example.web.alimentesebem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.dao.AgendaDaoOld;
import com.example.web.alimentesebem.model.AgendaBean;
import com.example.web.alimentesebem.utils.Utilitarios;
import com.example.web.alimentesebem.view.adapter.TagEventoAdapter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by WEB on 07/03/2018.
 */

public class EventoActivity extends AppCompatActivity{

    private ImageView imgCapaEvento,imgData;
    private ImageButton shareFace,shareWhat;
    private TextView tvLocalHorario,tvDecricao,tvtitulo,lblPreco,tvPreco;
    private AgendaBean obj;
    private AgendaDaoOld daoOld = AgendaDaoOld.instance;
    private Long id;
    private Intent intent;
    private DateFormat dtFmt = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
    private List<String> tags;
    private RecyclerView recyclerView;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        //cria os objetos da tela
        imgCapaEvento = findViewById(R.id.img_capa_evento);
        imgData = findViewById(R.id.img_data_evento);
        shareFace = findViewById(R.id.btn_share_facebook);
        shareWhat = findViewById(R.id.btn_share_what);
        tvDecricao = findViewById(R.id.tv_descricao_evento);
        tvLocalHorario = findViewById(R.id.tv_local_horario);
        tvtitulo = findViewById(R.id.tv_evento_titulo);
        lblPreco = findViewById(R.id.lbl_preco);
        tvPreco = findViewById(R.id.tv_preco);

        //Muda a fonte de alguns textView
        Typeface typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        tvtitulo.setTypeface(typeFont);
        lblPreco.setTypeface(typeFont);

        typeFont = Typeface.createFromAsset(getAssets(),"fonts/Gotham_Light.otf");
        tvDecricao.setTypeface(typeFont);
        tvLocalHorario.setTypeface(typeFont);
        tvPreco.setTypeface(typeFont);


        obj = new AgendaBean();

        tags = new ArrayList<>();
        tags.add("bacon1");
        tags.add("Churrasco1");
        tags.add("frango1");
        tags.add("fit1");
        tags.add("bacon2");
        tags.add("Churrasco2");
        tags.add("frango2");
        tags.add("fit2");
        tags.add("bacon3");
        tags.add("Churrasco3");
        tags.add("frango3");
        tags.add("fit3");

        recyclerView = findViewById(R.id.rv_tag);
        recyclerView.setAdapter(new TagEventoAdapter(tags,this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        intent  = getIntent();
        //usa o ID no Bundle para atribuir valor aos elementos da tela
        if(intent != null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                id = bundle.getLong("EventoId");
                obj = daoOld.getEvento(id);
                if(obj !=null) {
                    tvtitulo.setText(obj.getTitulo());
                    tvDecricao.setText(obj.getDescricao());
                    tvLocalHorario.setText(obj.getLocal() + "  " +
                            obj.getHorario());

                    if(obj.getPreco() == 0){
                        tvPreco.setText("Gratuito");
                    }else{
                        tvPreco.setText("R$: " + String.valueOf(String.format("%.2f", obj.getPreco())));
                    }

                    if (obj.getCapa() != null) {
                        imgCapaEvento.setImageBitmap(Utilitarios.bitmapFromBase64(obj.getCapa()));
                    }
                    // Obtem a 1ª letra do nome da pessoa e converte para Maiuscula
                    String dia = dtFmt.format(obj.getData()).substring(0,2);
                    String mes = dtFmt.format(obj.getData()).substring(6,9);
                    String diaMes = dia + " " + mes;
                    // Cria um bitmap contendo Dia e mês
                    // Bitmap bitmap = Utilitarios.quadradoBitmapAndText(
                    Bitmap bitmap = Utilitarios.circularBitmapAndText(
                            Color.parseColor("#ef8219"), 150, 150,diaMes, 45 );
                    imgData.setImageBitmap(bitmap);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //fecha a activity ao clicar na setinha do actionBar
            case android.R.id.home:

                finish();
                break;
            default: break;
        }
        return true;
    }
}
