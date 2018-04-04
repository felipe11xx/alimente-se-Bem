package com.example.web.alimentesebem.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.ForumBean;
import com.example.web.alimentesebem.view.NoticiaActivity;
import com.example.web.alimentesebem.view.TopicoActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by WEB on 15/03/2018.
 */

public class ForumAdapter extends RecyclerView.Adapter implements AdapterInterface{

    private Context context;
    private ArrayList<ForumBean> lista;
    private List<ForumBean> forumsLista;

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

        ForumViewHolder holder = new ForumViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ForumViewHolder forumViewHolder = (ForumViewHolder) holder;

        ForumBean forum = forumsLista.get(position);
        try{
            ((ForumViewHolder) holder).preencher(forum);
        }catch (Exception e){
            Toast.makeText(context, context.getResources().getString(R.string.falha_de_acesso), Toast.LENGTH_LONG).show();
        }


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

        public final TextView tvTitulo, tvAutor,tvCategoria,tvDataAbertura,tvLblAutor;
        private Long forumId;
        public DateFormat dtFmt =  DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt","BR"));
        public final ForumAdapter adapter;

        public ForumViewHolder(final View view, final ForumAdapter adapter) {
            super(view);
            this.adapter = adapter;

            view.setOnClickListener(this);

            tvTitulo = view.findViewById(R.id.tv_titulo_forum);
            tvAutor = view.findViewById(R.id.tv_autor);
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

        public void preencher(ForumBean obj) throws Exception{
            forumId = obj.getId();
            tvTitulo.setText(obj.getTitulo());
            tvLblAutor.setText("Aberto por: ");
            tvAutor.setText( obj.getNutricionista().getNome());
            tvCategoria.setText(obj.getCategoria().getNome());
            tvDataAbertura.setText(dtFmt.format(obj.getDataAbertura()));

        }

        @Override
        public void onClick(View v) {
            //Pega o id do topico clincado do cardView
           // Toast.makeText(context,"ID"+forumId, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(v.getContext(),TopicoActivity.class );
            intent.putExtra("ForumId", forumId);
            v.getContext().startActivity(intent);
        }
    }

}
