package com.example.bazazadanie;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wlasciciele_tabela")
public class Wlasciciele {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_ksiazki;
    private String imie;
    private String nazwisko;
    private String adres;

    public Wlasciciele(int id_ksiazki, String imie, String nazwisko, String adres) {
        this.id = 0;
        this.id_ksiazki = id_ksiazki;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(int id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return  "Imie: " + imie + ",\n" +
                "Nazwisko: " + nazwisko + ",\n" +
                "Adres: " + adres + ",\n";
    }
}
