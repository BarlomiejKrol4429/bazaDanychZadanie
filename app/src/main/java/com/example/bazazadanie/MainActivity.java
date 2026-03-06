package com.example.bazazadanie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    KsiazkiDatabase ksiazkiDatabase;
    Ksiazki modyfikowanaKsiazka;
    int modyfikowaneid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ksiazkiDatabase = KsiazkiDatabase.zwrocInstancjeBazyDanych(MainActivity.this);
        //ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki("Władysław Reymont", "Chłopi", 20, 736, 1904));
        //ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki("Andrzej Sapkowski", "Wiedźmin: Ostatnie życzenie", 40, 332, 1993));
        //ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki("George Orwell", "Rok 1984", 40, 230, 1949));

        Button dodaj = findViewById(R.id.dodaj);
        Button edytuj = findViewById(R.id.edytuj);
        EditText tytul = findViewById(R.id.tytul);
        EditText autor = findViewById(R.id.autor);
        EditText cena = findViewById(R.id.cena);
        EditText strony = findViewById(R.id.strony);
        EditText rok = findViewById(R.id.rok);
        ListView listView = findViewById(R.id.przepisy);
        List<Ksiazki> wszystkieKsiazkiList = ksiazkiDatabase.zwrocKsiazkiDao().zwrocWszytkieKsiazkiZBazy();
        ArrayAdapter<Ksiazki> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszystkieKsiazkiList);
        listView.setAdapter(arrayAdapter);


        dodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String autori = modyfikowanaKsiazka.getAutor();
                        String tytuli = modyfikowanaKsiazka.getTytul();
                        double cenai  = modyfikowanaKsiazka.getCena();
                        int stronyi = modyfikowanaKsiazka.getIloscStron();
                        int roki = modyfikowanaKsiazka.getRokWydania();

                        ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki(autori, tytuli, cenai, stronyi, roki));
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ksiazkiDatabase.zwrocKsiazkiDao().usunZBazy(wszystkieKsiazkiList.get(i));
                        wszystkieKsiazkiList.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        modyfikowanaKsiazka = wszystkieKsiazkiList.get(i);
                        modyfikowaneid = i;
                        String autori = modyfikowanaKsiazka.getAutor();
                        String tytuli = modyfikowanaKsiazka.getTytul();
                        double cenai  = modyfikowanaKsiazka.getCena();
                        int stronyi = modyfikowanaKsiazka.getIloscStron();
                        int roki = modyfikowanaKsiazka.getRokWydania();

                        autor.setText(autori);
                        tytul.setText(tytuli);
                        cena.setText(String.valueOf(cenai));
                        strony.setText(String.valueOf(stronyi));
                        rok.setText(String.valueOf(roki));

                        edytuj.setEnabled(true);
                        return false;
                    }
                }
        );
        edytuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String autori = modyfikowanaKsiazka.getAutor();
                        String tytuli = modyfikowanaKsiazka.getTytul();
                        double cenai  = modyfikowanaKsiazka.getCena();
                        int stronyi = modyfikowanaKsiazka.getIloscStron();
                        int roki = modyfikowanaKsiazka.getRokWydania();

                        wszystkieKsiazkiList.get(modyfikowaneid).setAutor(autori);
                        wszystkieKsiazkiList.get(modyfikowaneid).setTytul(tytuli);
                        wszystkieKsiazkiList.get(modyfikowaneid).setCena(cenai);
                        wszystkieKsiazkiList.get(modyfikowaneid).setIloscStron(stronyi);
                        wszystkieKsiazkiList.get(modyfikowaneid).setRokWydania(roki);

                        arrayAdapter.notifyDataSetChanged();
                        ksiazkiDatabase.zwrocKsiazkiDao().zaktualizuj(wszystkieKsiazkiList.get(modyfikowaneid));
                        edytuj.setEnabled(false);
                    }
                }
        );
    }
}