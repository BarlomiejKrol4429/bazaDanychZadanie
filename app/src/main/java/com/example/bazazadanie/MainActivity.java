package com.example.bazazadanie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki("Władysław Reymont", "Chłopi", 20, 736, 1904));
        ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki("Andrzej Sapkowski", "Wiedźmin: Ostatnie życzenie", 40, 332, 1993));
        ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkeDoBazy(new Ksiazki("George Orwell", "Rok 1984", 40, 230, 1949));
        ksiazkiDatabase.zwrocKsiazkiDao().wstawWlascicielaDoBazy(new Wlasciciele(2,"Jaś Michnik","Wadowice"));

        Button dodaj = findViewById(R.id.dodaj);
        Button edytuj = findViewById(R.id.edytuj);
        Button dodajw = findViewById(R.id.dodajw);
        EditText tytul = findViewById(R.id.tytul);
        EditText autor = findViewById(R.id.autor);
        EditText cena = findViewById(R.id.cena);
        EditText strony = findViewById(R.id.strony);
        EditText rok = findViewById(R.id.rok);
        EditText iin = findViewById(R.id.wlasciciel);
        EditText adres = findViewById(R.id.adres);
        ListView listViewKsiazki = findViewById(R.id.ksiazki);
        ListView listViewWlasciciele = findViewById(R.id.wlasciciele);

        List<Ksiazki> wszystkieKsiazkiList = ksiazkiDatabase.zwrocKsiazkiDao().zwrocWszytkieKsiazkiZBazy();
        ArrayAdapter<Ksiazki> arrayAdapterK = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszystkieKsiazkiList);
        listViewKsiazki.setAdapter(arrayAdapterK);

        List<WlascicielZKsiazka> wszyscyWlascicieleList = ksiazkiDatabase.zwrocKsiazkiDao().zwrocKsiazkeIWlasciciela();
        ArrayAdapter<WlascicielZKsiazka> arrayAdapterw = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszyscyWlascicieleList);
        listViewWlasciciele.setAdapter(arrayAdapterw);


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
                        arrayAdapterK.notifyDataSetChanged();
                    }
                }
        );
        listViewKsiazki.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ksiazkiDatabase.zwrocKsiazkiDao().usunZBazy(wszystkieKsiazkiList.get(i));
                        wszystkieKsiazkiList.remove(i);
                        arrayAdapterK.notifyDataSetChanged();
                    }
                }
        );
        listViewKsiazki.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        modyfikowanaKsiazka = wszystkieKsiazkiList.get(i);
                        modyfikowaneid = modyfikowanaKsiazka.getId();
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
                        dodajw.setEnabled(true);
                        return false;
                    }
                }
        );
        edytuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String autori = String.valueOf(autor.getText());
                        String tytuli = String.valueOf(tytul.getText());
                        double cenai  = Double.parseDouble(String.valueOf(cena.getText()));
                        int stronyi = Integer.parseInt(String.valueOf(strony.getText()));
                        int roki = Integer.parseInt(String.valueOf(rok.getText()));
                        Toast.makeText(MainActivity.this, autori, Toast.LENGTH_SHORT).show();

                        wszystkieKsiazkiList.get(modyfikowaneid).setAutor(autori);
                        wszystkieKsiazkiList.get(modyfikowaneid).setTytul(tytuli);
                        wszystkieKsiazkiList.get(modyfikowaneid).setCena(cenai);
                        wszystkieKsiazkiList.get(modyfikowaneid).setIloscStron(stronyi);
                        wszystkieKsiazkiList.get(modyfikowaneid).setRokWydania(roki);

                        arrayAdapterK.notifyDataSetChanged();
                        ksiazkiDatabase.zwrocKsiazkiDao().zaktualizuj(wszystkieKsiazkiList.get(modyfikowaneid));
                        edytuj.setEnabled(false);
                    }
                }
        );
        dodajw.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id_k = modyfikowaneid;
                        String inicjaly = String.valueOf(iin.getText());
                        String adress = String.valueOf(adres.getText());

                        ksiazkiDatabase.zwrocKsiazkiDao().wstawWlascicielaDoBazy(new Wlasciciele(id_k, inicjaly, adress));
                        arrayAdapterw.notifyDataSetChanged();
                        dodajw.setEnabled(false);
                    }
                }
        );
        listViewWlasciciele.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        wszyscyWlascicieleList.get(i);
                        ksiazkiDatabase.zwrocKsiazkiDao().usunZBazyW();
                        wszystkieKsiazkiList.remove(i);
                        arrayAdapterK.notifyDataSetChanged();
                    }
                }
        );
    }
}