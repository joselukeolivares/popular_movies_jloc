package com.example.popularmoviesjloc;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.popularmoviesjloc.movies.movie;
import com.example.popularmoviesjloc.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import popular_movies_api.movie_db;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private movieAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

     String URLBase="https://api.themoviedb.org/3/discover/movie";

     String sortQuery="sort_by";
     String sortParam="vote_average.desc";
     public ArrayList<movie> movieList=new ArrayList<>();


     /*

     certification_country US
     certification=R
     sort_by=vote_average.desc

      */

    final private String apiKeyQuery="api_key";
    final private String apiKeyParam="b70d678e90b7b2248b8795db25cd8d26";
    String[] keysUri={sortQuery,apiKeyQuery};
    String[] keysParam={apiKeyQuery,apiKeyParam};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager=new GridLayoutManager(this,2);
        mAdapter=new movieAdapter(this.getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        getJsonString();
        updateDataAdapter();







        //Log.i("url",url.toString());
    }

    public void getJsonString(){
        Uri uri= NetworkUtils.getURI(URLBase,keysUri,keysParam);
        URL url=NetworkUtils.getURL(uri);
        new movie_db(this).execute(url);

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.movies_menu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optionPopular:
                sortParam="popularity.desc";
            case R.id.optionRate:
                sortParam="vote_average.desc";
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public  void stringToJson(String result){
Log.i("stringToJson",result);
        try{
            JSONObject results=new JSONObject(result);
            JSONArray movies=results.getJSONArray("results");
            Log.i("moviesLength:",movies.length()+"");
            for(int i=0;i<movies.length();i++){
                if(!movies.isNull(i)){
                    JSONObject movieFromDB=movies.getJSONObject(i);
                    movie movieX=new movie();
                    String title=movieFromDB.getString("title");
                    movieX.setTitle(title);
                    String pathPoster=movieFromDB.getString("poster_path");
                    movieX.setPosterPath(pathPoster);
                    movieList.add(movieX);
                }


            }

            Log.i("movieList:",movieList.size()+"");


        }catch (JSONException e){
            e.printStackTrace();
        }


    }

    public  void updateDataAdapter(){
        mAdapter.actualizandoData(movieList);


    }


}