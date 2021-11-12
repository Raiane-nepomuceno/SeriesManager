package com.example.seriesmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.seriesmanager.model.Episodio;
import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.model.Temporada;


public class Banco extends SQLiteOpenHelper {
    public Banco(Context context) {
        super(context, "SeriesManager.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Series(nome TEXT primary key, anoLancamento int,emissora TEXT, genero TEXT)");
        sqLiteDatabase.execSQL("create table if not exists Temporadas(numeroSequencialTemp int primary key, anoLancamento int,quantidadeEpisodios int,nomeSerie TEXT, foreign key (nomeSerie) references Series(nome))");
        sqLiteDatabase.execSQL("create Table if not exists Episodios(numeroSequencialEp int primary key, nome TEXT,tempoDuracao float, flag boolean,numeroSequencialTemp int, foreign key (numeroSequencialTemp ) references Temporadas(numeroSequencialTemp ))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Series");
        sqLiteDatabase.execSQL("drop table if exists Temporadas");
        sqLiteDatabase.execSQL("drop Table if exists Episodios");
        onCreate(sqLiteDatabase);
    }
    public Boolean updateSerie(Serie serie, String nomeAntigoSerieEt) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", serie.getNome());
        contentValues.put("anoLancamento", serie.getAnoLancamento());
        contentValues.put("emissora", serie.getEmissora());
        contentValues.put("genero", serie.getGenero());

        Cursor cursor = DB.rawQuery("Select * from Series where nome = ?", new String[]{nomeAntigoSerieEt});
        if (cursor.getCount() > 0) {
            long result = DB.update("Series", contentValues, "nome=?", new String[]{nomeAntigoSerieEt});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean insertEpisosios(Episodio episodio)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("numeroSequencial",episodio.getNumeroSequencial());
        contentValues.put("nome", episodio.getNome());
        contentValues.put("tempoDuracao",episodio.getTempoDuracao());
        contentValues.put("numeroSequencialTemp",episodio.getNumeroSequencialTemp());

        long result = DB.insert("Episodios",null,contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
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
    public Boolean insertTemporadas(Temporada temporada, String s)
    {
        SQLiteDatabase DB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("numeroSequencialTemp",temporada.getNumeroSequencial());
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
    public Boolean deleteTemporadas(int numTemp)
    {
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("Select * from Temporadas where numeroSequencialTemp= ?" + numTemp,null);
        if(cursor.getCount() > 0)
        {
            long result = DB.delete("Temporadas","numeroSequencialTemp=?"+numTemp,null);
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

    public Cursor getEpisodios()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Episodios",null);
        return cursor;
    }

    public Cursor getTemporadas(String nomeSerie)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Temporadas where nomeSerie = ?",new String[]{nomeSerie});
        return cursor;
    }



}
