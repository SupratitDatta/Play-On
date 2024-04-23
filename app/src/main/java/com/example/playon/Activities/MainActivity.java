package com.example.playon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.playon.Adapters.CategoryListAdapter;
import com.example.playon.Adapters.FilmListAdapter;
import com.example.playon.Adapters.SliderAdapters;
import com.example.playon.Domains.GenresItem;
import com.example.playon.Domains.ListFilm;
import com.example.playon.Domains.SliderItems;
import com.example.playon.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterBestMovies,adapterUpComing,adapterCategory,adapterNew;
    private RecyclerView recycleViewBestMovies,recycleViewUpcoming,recycleViewCategory, recycleViewNew;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest,mStringRequest2,mStringRequest3, mStringRequest4;
    private ProgressBar loading1,loading2,loading3,loading4;
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    private ImageView profileImg, favouritesImg,watchlistImg,explorerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        banners();
        sendRequestCategory();
        sendRequestBestMovies();
        sendRequestUpComing();
        sendRequestNew();
    }

    private void sendRequestCategory() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest3=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", response -> {
            Gson gson=new Gson();
            loading2.setVisibility(View.GONE);
            ArrayList<GenresItem> catList=gson.fromJson(response, new TypeToken<ArrayList<GenresItem>>(){}.getType());
            adapterCategory=new CategoryListAdapter(catList);
            recycleViewCategory.setAdapter(adapterCategory);
        }, error -> {
            loading2.setVisibility(View.GONE);
            Log.i("Play On","onErrorResponse: "+error.toString());
        });
        mRequestQueue.add(mStringRequest3);
    }

    private void sendRequestBestMovies() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        Random random = new Random();
        int pageNumber = random.nextInt(5) + 6;
        String url = "https://moviesapi.ir/api/v1/movies?page=" + pageNumber;
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
        loading3.setVisibility(View.VISIBLE);
        Random random = new Random();
        int pageNumber = random.nextInt(5) + 11;
        String url = "https://moviesapi.ir/api/v1/movies?page=" + pageNumber;
        mStringRequest3 = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            loading3.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterUpComing = new FilmListAdapter(items);
            recycleViewUpcoming.setAdapter(adapterUpComing);
        }, error -> {
            loading3.setVisibility(View.GONE);
            Log.i("Play On", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest3);
    }

    private void sendRequestNew() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading4.setVisibility(View.VISIBLE);
        Random random = new Random();
        int pageNumber = random.nextInt(5) + 16;
        String url = "https://moviesapi.ir/api/v1/movies?page=" + pageNumber;
        mStringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            loading4.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterNew = new FilmListAdapter(items);
            recycleViewNew.setAdapter(adapterNew);
        }, error -> {
            loading4.setVisibility(View.GONE);
            Log.i("Play On", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }


    private void initView() {
        viewPager2 = findViewById(R.id.viewpageSlider);
        recycleViewBestMovies=findViewById(R.id.view1);
        recycleViewBestMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleViewUpcoming=findViewById(R.id.view2);
        recycleViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleViewCategory=findViewById(R.id.view3);
        recycleViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleViewNew=findViewById(R.id.view4);
        recycleViewNew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);
        loading3=findViewById(R.id.progressBar3);
        loading4=findViewById(R.id.progressBar4);

        explorerImg=findViewById(R.id.explorer);
        watchlistImg=findViewById(R.id.watchlist);
        favouritesImg=findViewById(R.id.favourites);
        profileImg=findViewById(R.id.profile_pic);


        favouritesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavouritesActivity.class));
                Toast.makeText(MainActivity.this, "Favourites", Toast.LENGTH_SHORT).show();
            }
        });

        watchlistImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WatchlistActivity.class));
                Toast.makeText(MainActivity.this, "Watchlist", Toast.LENGTH_SHORT).show();
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void banners() {
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.wide0));
        sliderItems.add(new SliderItems(R.drawable.wide2));
        sliderItems.add(new SliderItems(R.drawable.wide9));
        sliderItems.add(new SliderItems(R.drawable.wide4));
        sliderItems.add(new SliderItems(R.drawable.wide5));
        sliderItems.add(new SliderItems(R.drawable.wide6));
        sliderItems.add(new SliderItems(R.drawable.wide7));

        viewPager2.setAdapter(new SliderAdapters(sliderItems, viewPager2, this));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer(){
            @Override
            public void transformPage(@NonNull View page, float position){
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 2000);
    }
}