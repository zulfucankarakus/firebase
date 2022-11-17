package com.info.notuygulamasi;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class DetayActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Notlar not;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        toolbar = findViewById(R.id.toolbar);
        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        not = (Notlar) getIntent().getSerializableExtra("nesne");

        editTextDers.setText(not.getDers_adi());
        editTextNot1.setText(String.valueOf(not.getNot1()));
        editTextNot2.setText(String.valueOf(not.getNot2()));

        toolbar.setTitle("Not Detay");
        setSupportActionBar(toolbar);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbar,"Silinsin mi ?",Snackbar.LENGTH_SHORT)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                myRef.child(not.getNot_id()).removeValue();

                                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                                finish();
                            }
                        })
                        .show();
                return true;
            case  R.id.action_duzenle:

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                Map<String,Object> bilgiler = new HashMap<>();

                bilgiler.put("ders_adi",ders_adi);
                bilgiler.put("not1",Integer.parseInt(not1));
                bilgiler.put("not2",Integer.parseInt(not2));

                myRef.child(not.getNot_id()).updateChildren(bilgiler);

                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                finish();
                return true;
            default:
                return false;
        }
    }
}
