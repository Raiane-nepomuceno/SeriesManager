package com.example.seriesmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.seriesmanager.model.Episodio;

public class EpisodioDAO extends SQLiteOpenHelper{
    public EpisodioDAO(Context context) {
        super(context, "SeriesManager.db",null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Episodios(numeroSequencialEp int primary key, nome TEXT,tempoDuracao float, flag boolean, foreign key (numeroSequencialTemp ) references Temporadas(numeroSequencialTemp ))");
 }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Episodios");

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
    public Cursor getEpisodios()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Episodios",null);
        return cursor;
    }
}
