package com.example.seriesmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.model.Temporada;

public class TemporadaDAO extends SQLiteOpenHelper {

    public TemporadaDAO(Context context) {
        super(context, "SeriesManager.db",null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists Temporadas(numeroSequencialTemp integer primary key, anoLancamento int,quantidadeEpisodios int, foreign key (nomeSerie) references Series(nome))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Temporadas");

    }
    public Boolean insertTemporadas(Temporada temporada, String s)
    {
        SQLiteDatabase DB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("numeroSequencial",temporada.getNumeroSequencial());
        contentValues.put("anoLancamento", temporada.getAnoLancamento());
        contentValues.put("quantidadeEpisodios",temporada.getQuantidadeEpisodios());
        contentValues.put("nomeSerie",s);

        long result = DB.insert("Temporadas",null,contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }
    public Cursor getTemporadas()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Temporadas",null);
        return cursor;
    }
}
