package com.example.bazazadanie;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ksiazki.class}, version = 3)
public abstract class KsiazkiDatabase extends RoomDatabase{
    public abstract KsiazkiDao zwrocKsiazkiDao();

    private static KsiazkiDatabase instancja;

    public static KsiazkiDatabase zwrocInstancjeBazyDanych(Context context){
        if(instancja == null){
            instancja = Room.databaseBuilder(
                    context, KsiazkiDatabase.class, "ksiazki_db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instancja;
    }
}
