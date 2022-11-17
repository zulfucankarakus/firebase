package com.info.notuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.PriorityQueue;

public class NotKayitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Button buttonKaydet;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);

        toolbar = findViewById(R.id.toolbar);
        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);
        buttonKaydet = findViewById(R.id.buttonKaydet);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        toolbar.setTitle("Not Kayıt");
        setSupportActionBar(toolbar);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                Notlar not = new Notlar("",ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));

                myRef.push().setValue(not);

                startActivity(new Intent(NotKayitActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
