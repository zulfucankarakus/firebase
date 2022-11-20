package com.info.filmleruygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilmlerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView filmlerRv;
    private ArrayList<Filmler> filmlerArrayList;
    private FilmlerAdapter adapter;
    private Kategoriler kategori;
    private FirebaseDatabase database;
    private DatabaseReference myRefFilmler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);

        toolbar = findViewById(R.id.toolbar);
        filmlerRv = findViewById(R.id.filmlerRv);

        database = FirebaseDatabase.getInstance();
        myRefFilmler = database.getReference("filmler");

        kategori = (Kategoriler) getIntent().getSerializableExtra("kategoriNesne");

        toolbar.setTitle(kategori.getKategori_ad());
        setSupportActionBar(toolbar);

        filmlerRv.setHasFixedSize(true);
        filmlerRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        filmlerArrayList = new ArrayList<>();

        adapter = new FilmlerAdapter(this,filmlerArrayList);

        filmlerRv.setAdapter(adapter);

        filmGetirByKategoriAd();

    }

    public void filmGetirByKategoriAd(){
        Query sorgu = myRefFilmler.orderByChild("kategori_ad").equalTo(kategori.getKategori_ad());

        sorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                filmlerArrayList.clear();

                for(DataSnapshot d:dataSnapshot.getChildren()){
                    Filmler film = d.getValue(Filmler.class);
                    film.setFilm_id(d.getKey());

                    filmlerArrayList.add(film);

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
