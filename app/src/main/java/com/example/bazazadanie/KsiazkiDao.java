package com.example.bazazadanie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KsiazkiDao {
    @Insert
    public void wstawKsiazkeDoBazy(Ksiazki ksiazka);
    @Insert
    public void wstawKilkaKsiazek(Ksiazki ...ksiazka);
    @Delete
    public void usunZBazy(Ksiazki ksiazka);
    @Update
    public void zaktualizuj(Ksiazki ksiazka);
    @Query("SELECT * FROM ksiazki_tabela")
    List<Ksiazki> zwrocWszytkieWypiekiZBazy();
}