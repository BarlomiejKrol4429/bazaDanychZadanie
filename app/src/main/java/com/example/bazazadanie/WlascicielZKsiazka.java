package com.example.bazazadanie;

import androidx.room.Embedded;
import androidx.room.Relation;

public class WlascicielZKsiazka {
    @Embedded public Wlasciciele wlasciciele;
    @Relation(
            parentColumn = "id_ksiazki",
            entityColumn = "id"
    )
    public Ksiazki ksiazki;

    @Override
    public String toString() {
        return  "Własciciel: " + wlasciciele +
                "Tytuł: " + ksiazki.getTytul();
    }
}
