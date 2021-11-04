package com.example.seriesmanager.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.seriesmanager.model.Serie;

import java.util.List;


public class SerieDAO extends SQLiteOpenHelper {

    public SerieDAO(Context context) {
        super(context, "SeriesManager.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Series(nome TEXT primary key, anoLancamento int,emissora TEXT, genero TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("drop Table if exists Series");
    }
    public Boolean insertSerie(Serie serie)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",serie.getNome());
        contentValues.put("anoLancamento", serie.getAnoLancamento());
        contentValues.put("emissora",serie.getEmissora());
        contentValues.put("genero",serie.getGenero());

        long result = DB.insert("Series",null,contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateSerie(Serie serie) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", serie.getNome());
        contentValues.put("anoLancamento", serie.getAnoLancamento());
        contentValues.put("emissora", serie.getEmissora());
        contentValues.put("genero", serie.getGenero());

        Cursor cursor = DB.rawQuery("Select * from Series where nome = ?", new String[]{serie.getNome()});
        if (cursor.getCount() > 0) {
            long result = DB.update("Series", contentValues, "nome=?", new String[]{serie.getNome()});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteSeries(String nome)
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Series where nome = ?", new String[]{nome});
        if(cursor.getCount() > 0)
        {
            long result = DB.delete("Series","nome=?",new String[] {nome});
            if(result == -1)
            {
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public Cursor getSeries()
    {
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Series",null);
        return cursor;
    }
}
