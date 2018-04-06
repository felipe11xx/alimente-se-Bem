package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.CategoriaForumBean;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.model.NutricionistaBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.example.web.alimentesebem.view.TopicoActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Felipe
 * on 15/03/2018.
 */

public class ForumAdapter extends RecyclerView.Adapter implements AdapterInterface{

    private Context context;
    private ArrayList<ForumBean> lista;
    private List<ForumBean> forumsLista;
    private NutricionistaBean nutricionista;
    private CategoriaForumBean categoria;


    public ForumAdapter(Context context, List<ForumBean> forumsLista) {
        this.forumsLista = forumsLista;
        this.context = context;
        this.lista = new ArrayList<>();
        this.lista.addAll(forumsLista);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_forum,parent,false);

        return new ForumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ForumBean forum = forumsLista.get(position);

        ((ForumViewHolder) holder).preencher(forum);

    }

    @Override
    public int getItemCount() {
        return forumsLista.size();
    }

    @Override
    public void filtrarPorTitulo(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        forumsLista.clear();
        if (charText.length() == 0) {
            forumsLista.addAll(lista);
        } else {
            for (ForumBean l : lista) {
                if (l.getTitulo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    forumsLista.add(l);
                }
            }
        }
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tvTitulo, tvAutor,tvCategoria,tvDataAbertura,tvLblAutor;
        private Long forumId;
        private DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt","BR"));

        private ForumViewHolder(final View view) {
            super(view);

            view.setOnClickListener(this);

            tvTitulo = view.findViewById(R.id.tv_titulo_forum);
            tvAutor = view.findViewById(R.id.tv_autor_noticia);
            tvCategoria = view.findViewById(R.id.tv_categoria_forum);
            tvDataAbertura = view.findViewById(R.id.tv_data_forum);
            tvLblAutor = view.findViewById(R.id.tv_lbl_autor);
            Typeface typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Light.otf");
            tvAutor.setTypeface(typeFont);
            tvCategoria.setTypeface(typeFont);
            tvDataAbertura.setTypeface(typeFont);
            typeFont = Typeface.createFromAsset(context.getAssets(),"fonts/Gotham_Condensed_Bold.otf");
            tvTitulo.setTypeface(typeFont);
            tvLblAutor.setTypeface(typeFont);

        }

        private void preencher(ForumBean obj) {
            forumId = obj.getId();
            tvTitulo.setText(obj.getTitulo());
            tvLblAutor.setText(context.getString(R.string.label_autor));
            tvDataAbertura.setText(dtFmt.format(obj.getData_criacao()));
            getCategoria(obj.getId_Cat_Forum());
            getNutricionista(obj.getId_Nutricionista());

        }

        @Override
        public void onClick(View v) {
            //Pega o id do topico clincado do cardView
           // Toast.makeText(context,"ID"+forumId, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(v.getContext(),TopicoActivity.class );
            intent.putExtra("ForumId", forumId);
            v.getContext().startActivity(intent);
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

}
