package com.example.web.alimentesebem.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.web.alimentesebem.Main;
import com.example.web.alimentesebem.model.NoticiaBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 03/03/2018.
 */

public class NoticiaDao extends SQLiteOpenHelper {

    public static NoticiaDao instace = new NoticiaDao();


    public NoticiaDao() {
        super(Main.getContext(), "DbNoticia",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table noticia ("+
                        "id integer primary key autoincrement,"+
                        "titulo text not null,"+
                        "conteudo text not null,"+
                        "publicacao long not null,"+
                        "capa blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
//                db.execSQL("alter table noticia add column delA int not null");
            case 2:
//                db.execSQL("alter table noticia add column delB int not null");
        }
    }

    private void setData(SQLiteStatement sql, NoticiaBean obj){
            sql.bindString(1,obj.getTitulo());
            sql.bindString(2, obj.getConteudo());
            sql.bindLong(3,obj.getDataPublica().getTime());
            sql.bindBlob(4,obj.getCapa() != null ? obj.getCapa() : new byte[]{});

    }

    private NoticiaBean getData(Cursor cursor){
        NoticiaBean obj = new NoticiaBean();

        obj.setId(cursor.getLong(0));
        obj.setTitulo(cursor.getString(1));
        obj.setConteudo(cursor.getString(2));
        obj.setDataPublica(new Date(cursor.getLong(3)));
        obj.setCapa(cursor.getBlob(4).length > 0 ? cursor.getBlob(4) : null);
        return obj;
    }

    public void salvar(NoticiaBean obj){
        //Abre o banco de dados para escrita
        SQLiteDatabase db = getWritableDatabase();

        //pendura registro com mesmo nome
        Cursor cursor = db.rawQuery("select * from noticia " +
                        "where lower(titulo)=?",
                new String[]{
                        obj.getTitulo().toLowerCase()
                }
        );

        if (cursor.getCount() == 0) { // Não encontrado, salva
            String sql = "insert into noticia (titulo, conteudo, publicacao, capa) " +
                    "values (?,?,?,?)";
            SQLiteStatement insert = db.compileStatement(sql);
            setData(insert, obj);
            insert.execute();
            cursor.close();
            cursor = db.rawQuery("select last_insert_rowid() from noticia", null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                obj.setId(cursor.getLong(0));
            }
        } else {       // Encontrado, atualiza
            cursor.moveToFirst();
            obj.setId(cursor.getLong(0));

            String sql = "update noticia set titulo=?, conteudo=?, publicacao=?,  capa=?" +
                    "where id=?";
            SQLiteStatement update = db.compileStatement(sql);
            setData(update, obj);
            update.bindLong(5, obj.getId());
            update.execute();
        }
        cursor.close();
        db.close();
    }

    public List<Long> listarIds() {
        String query;
        query = "select id from noticia order by publicacao";


        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Long> lista = new ArrayList<>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                lista.add(cursor.getLong(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return lista;
    }

    public NoticiaBean localizar(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from noticia where id=?",
                new String[] {String.valueOf(id)});
        NoticiaBean obj = null;
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            obj = getData(cursor);
        }
        cursor.close();
        db.close();

        return obj;
    }


}
