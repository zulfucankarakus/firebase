package com.info.filmleruygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView kategoriRv;
    private ArrayList<Kategoriler> kategorilerArrayList;
    private KategoriAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRefKategoriler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        kategoriRv = findViewById(R.id.kategoriRv);

        database = FirebaseDatabase.getInstance();
        myRefKategoriler = database.getReference("kategoriler");

        toolbar.setTitle("Kategoriler");
        setSupportActionBar(toolbar);

        kategoriRv.setHasFixedSize(true);
        kategoriRv.setLayoutManager(new LinearLayoutManager(this));


        tumKategoriler();


    }


    public void tumKategoriler(){

        myRefKategoriler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                kategorilerArrayList = new ArrayList<>();

                for(DataSnapshot d:dataSnapshot.getChildren()){
                    Kategoriler kategori = d.getValue(Kategoriler.class);
                    kategori.setKategori_id(d.getKey());

                    kategorilerArrayList.add(kategori);

                }

                adapter = new KategoriAdapter(MainActivity.this,kategorilerArrayList);

                kategoriRv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
