package com.example.playon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.playon.Adapters.FilmListAdapter;
import com.example.playon.Domains.ListFilm;
import com.example.playon.R;
import com.google.gson.Gson;

public class FavouritesActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterFavouriteMovies,adapterFavouriteSeries;
    private RecyclerView recycleViewFavouriteMovies,recycleViewFavouriteSeries;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest,mStringRequest2;
    private ProgressBar loading1,loading2;
    private ImageView homeImg,profileImg,watchlistImg,explorerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        initView();
        sendRequestFavouriteMovies();
        sendRequestFavouriteSeries();
    }

    private void sendRequestFavouriteMovies() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        String url = "https://moviesapi.ir/api/v1/movies?page=4";
        mStringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterFavouriteMovies = new FilmListAdapter(items);
            recycleViewFavouriteMovies.setAdapter(adapterFavouriteMovies);
        }, error -> {
            loading1.setVisibility(View.GONE);
            Log.i("Play On", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequestFavouriteSeries() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        String url = "https://moviesapi.ir/api/v1/movies?page=3";
        mStringRequest2 = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterFavouriteSeries = new FilmListAdapter(items);
            recycleViewFavouriteSeries.setAdapter(adapterFavouriteSeries);
        }, error -> {
            loading2.setVisibility(View.GONE);
            Log.i("Play On", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void initView() {
        recycleViewFavouriteMovies=findViewById(R.id.view1);
        recycleViewFavouriteMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleViewFavouriteSeries=findViewById(R.id.view2);
        recycleViewFavouriteSeries.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);

        homeImg=findViewById(R.id.home);
        explorerImg=findViewById(R.id.explorer);
        watchlistImg=findViewById(R.id.watchlist);
        profileImg=findViewById(R.id.profile_pic);

        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavouritesActivity.this, MainActivity.class));
                Toast.makeText(FavouritesActivity.this, "Home", Toast.LENGTH_SHORT).show();
            }
        });

        watchlistImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavouritesActivity.this, WatchlistActivity.class));
                Toast.makeText(FavouritesActivity.this, "Watchlist", Toast.LENGTH_SHORT).show();
            }
        });
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavouritesActivity.this, ProfileActivity.class));
                Toast.makeText(FavouritesActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}