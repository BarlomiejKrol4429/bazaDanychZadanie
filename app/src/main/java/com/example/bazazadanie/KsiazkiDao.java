package com.example.bazazadanie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KsiazkiDao {
    @Insert
    public void wstawKsiazkeDoBazy(Ksiazki ksiazka);
    @Delete
    public void usunZBazy(Ksiazki ksiazka);
    @Update
    public void zaktualizuj(Ksiazki ksiazka);
    @Query("SELECT * FROM ksiazki_tabela")
    List<Ksiazki> zwrocWszytkieKsiazkiZBazy();
    @Transaction
    @Query("Select * from wlasciciele_tabela")
    List<WlascicielZKsiazka> zwrocKsiazkeIWlasciciela();
    @Insert
    public void wstawWlascicielaDoBazy(Wlasciciele wlasciciele);
    @Delete
    public void usunZBazyW(Wlasciciele wlasciciele);

}