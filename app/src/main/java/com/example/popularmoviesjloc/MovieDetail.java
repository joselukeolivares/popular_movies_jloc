package com.example.popularmoviesjloc;

import android.content.Intent;
import android.os.Bundle;

import com.example.popularmoviesjloc.movies.movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmoviesjloc.R;
import com.example.popularmoviesjloc.movies.movie;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    TextView nameMovie;
    ImageView poster;
    TextView yearRelease;
    TextView rateMovie;
    TextView durationMovie;
    Button markAsFavorite;
    TextView sinopsis;
    movie movieObjt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent=getIntent();
        if(intent!=null){

            movieObjt=intent.getParcelableExtra("movie");
            nameMovie=(TextView)findViewById(R.id.tv_movieName);
            nameMovie.setText(movieObjt.getTitle());
            poster=(ImageView)findViewById(R.id.tv_posterMovie);
            Picasso.with(this).load(movieObjt.getPosterPath()).into(poster);
            yearRelease=(TextView)findViewById(R.id.tv_dateRelease);
            yearRelease.setText(movieObjt.getReleaseDate().toString());
            rateMovie=(TextView)findViewById(R.id.tv_rateMovie);
            rateMovie.setText(movieObjt.getVoteCount());
            sinopsis=(TextView)findViewById(R.id.tv_sinopsisMovie);
            sinopsis.setText(movieObjt.getOverView());


        }






    }

}
