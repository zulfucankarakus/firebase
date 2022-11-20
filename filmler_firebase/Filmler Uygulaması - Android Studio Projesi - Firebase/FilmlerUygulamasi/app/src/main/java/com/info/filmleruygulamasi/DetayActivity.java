package com.info.filmleruygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetayActivity extends AppCompatActivity {
    private ImageView ımageViewFilmResim;
    private TextView textViewFilmAd,textViewYil,textViewYonetmen;
    private Filmler film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        ımageViewFilmResim = findViewById(R.id.imageViewResim);
        textViewFilmAd = findViewById(R.id.textViewFilmAd);
        textViewYil = findViewById(R.id.textViewYil);
        textViewYonetmen = findViewById(R.id.textViewYonetmen);

        film = (Filmler) getIntent().getSerializableExtra("nesne");

        textViewFilmAd.setText(film.getFilm_ad());
        textViewYil.setText(String.valueOf(film.getFilm_yil()));
        textViewYonetmen.setText(film.getYonetmen_ad());

        String url = "http://kasimadalan.pe.hu/filmler/resimler/"+film.getFilm_resim()+".png";

        Picasso.get().load(url).resize(400,600).into(ımageViewFilmResim);

    }
}
