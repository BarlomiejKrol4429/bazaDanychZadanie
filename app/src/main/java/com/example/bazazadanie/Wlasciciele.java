package com.example.bazazadanie;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wlasciciele_tabela")
public class Wlasciciele {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_ksiazki;
    private String imie_i_nazwisko;
    private String adres;

    public Wlasciciele(int id_ksiazki, String imie_i_nazwisko, String adres) {
        this.id = 0;
        this.id_ksiazki = id_ksiazki;
        this.imie_i_nazwisko = imie_i_nazwisko;
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
    public String getAdres() {
        return adres;
    }

    public String getImie_i_nazwisko() {
        return imie_i_nazwisko;
    }

    public void setImie_i_nazwisko(String imie_i_nazwisko) {
        this.imie_i_nazwisko = imie_i_nazwisko;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return  "Imie I Nazwisko: " + imie_i_nazwisko + ",\n" +
                "Adres: " + adres + ",\n";
    }
}
