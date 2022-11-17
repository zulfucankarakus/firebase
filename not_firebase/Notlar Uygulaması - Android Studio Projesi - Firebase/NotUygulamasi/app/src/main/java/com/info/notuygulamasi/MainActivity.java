package com.info.notuygulamasi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private NotlarAdapter adapter;
    private ArrayList<Notlar> notlarArrayList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        notlarArrayList = new ArrayList<>();

        adapter = new NotlarAdapter(this,notlarArrayList);

        rv.setAdapter(adapter);

        tumNotlar();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NotKayitActivity.class));
            }
        });

    }


    public void tumNotlar(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                notlarArrayList.clear();

                double toplam = 0.0;

                for(DataSnapshot d:dataSnapshot.getChildren()){
                    Notlar not = d.getValue(Notlar.class);
                    not.setNot_id(d.getKey());

                    notlarArrayList.add(not);

                    toplam = toplam +(not.getNot1()+not.getNot2())/2;

                }

                adapter.notifyDataSetChanged();

                toolbar.setSubtitle("Ortalama : "+toplam/notlarArrayList.size());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
