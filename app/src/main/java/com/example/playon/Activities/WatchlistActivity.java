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

public class WatchlistActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterBestMovies,adapterUpComing;
    private RecyclerView recycleViewBestMovies,recycleViewUpcoming;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest,mStringRequest3;
    private ProgressBar loading1,loading2;
    private ViewPager2 viewPager2;
    private ImageView homeImg,profileImg,favouritesImg,explorerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        initView();
        sendRequestBestMovies();
        sendRequestUpComing();
    }

    private void sendRequestBestMovies() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        String url = "https://moviesapi.ir/api/v1/movies?page=17";
        mStringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterBestMovies = new FilmListAdapter(items);
            recycleViewBestMovies.setAdapter(adapterBestMovies);
        }, error -> {
            loading1.setVisibility(View.GONE);
            Log.i("Play On", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequestUpComing() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        String url = "https://moviesapi.ir/api/v1/movies?page=18";
        mStringRequest3 = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterUpComing = new FilmListAdapter(items);
            recycleViewUpcoming.setAdapter(adapterUpComing);
        }, error -> {
            loading2.setVisibility(View.GONE);
            Log.i("Play On", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest3);
    }

    private void initView() {
        viewPager2 = findViewById(R.id.viewpageSlider);
        recycleViewBestMovies=findViewById(R.id.view1);
        recycleViewBestMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleViewUpcoming=findViewById(R.id.view2);
        recycleViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);

        homeImg=findViewById(R.id.home);
        explorerImg=findViewById(R.id.explorer);
        favouritesImg=findViewById(R.id.favourites);
        profileImg=findViewById(R.id.profile_pic);

        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchlistActivity.this, MainActivity.class));
                Toast.makeText(WatchlistActivity.this, "Home", Toast.LENGTH_SHORT).show();
            }
        });
        favouritesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchlistActivity.this, FavouritesActivity.class));
                Toast.makeText(WatchlistActivity.this, "Favourites", Toast.LENGTH_SHORT).show();
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchlistActivity.this, ProfileActivity.class));
                Toast.makeText(WatchlistActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}