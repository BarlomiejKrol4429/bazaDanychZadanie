package com.example.bazazadanie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ksiazki_tabela")
public class Ksiazki {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String autor;
    private String tytul;
    private double cena;
    @ColumnInfo(name = "ilosc_stron")
    private int iloscStron;
    @ColumnInfo(name = "rok_wydania")
    private int rokWydania;

    public Ksiazki(String autor, String tytul, double cena, int iloscStron, int rokWydania) {
        this.id = 0;
        this.autor = autor;
        this.tytul = tytul;
        this.cena = cena;
        this.iloscStron = iloscStron;
        this.rokWydania = rokWydania;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }

    @Override
    public String toString() {
        return  "Autor: " + autor + ",\n" +
                "Tytul: " + tytul + ",\n" +
                "Cena: " + cena + ",\n" +
                "Ilość Stron: " + iloscStron + ",\n" +
                "Rok Wydania: " + rokWydania;
    }
}

