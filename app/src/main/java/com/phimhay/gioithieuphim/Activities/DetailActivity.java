package com.phimhay.gioithieuphim.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.phimhay.gioithieuphim.Adapters.ActorsListAdapter;
import com.phimhay.gioithieuphim.Adapters.CategoryEachFilmListApdater;
import com.phimhay.gioithieuphim.Adapters.FilmListApdater;
import com.phimhay.gioithieuphim.Domain.FilmItem;
import com.phimhay.gioithieuphim.R;

public class DetailActivity extends AppCompatActivity {
private RequestQueue mRequestQueue;
private StringRequest mStringRequest;
private ProgressBar progressBar;
private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryInfo, movieActorsInfo;
private int idFilm;
private ImageView pic2, backImg;
private RecyclerView.Adapter adapterActorList, adapterCategory;
private RecyclerView recyclerViewActors, recyclerViewCategory;
private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson  gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response, FilmItem.class);
            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);
            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryInfo.setText(item.getPlot());
            movieActorsInfo.setText(item.getActors());
            if (item.getImages()!=null){
                adapterActorList = new ActorsListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }
            if (item.getGenres()!=null){
                adapterCategory = new CategoryEachFilmListApdater(item.getGenres());
                recyclerViewCategory.setAdapter(adapterCategory);
            }
        }, error -> progressBar.setVisibility((View.GONE)));
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt = findViewById(R.id.movieNameTxt);
        progressBar = findViewById(R.id.progressBarDetail);
        scrollView = findViewById(R.id.scrollView2);
        pic2 = findViewById(R.id.picDetail);
        movieRateTxt = findViewById(R.id.movieStar);
        movieTimeTxt = findViewById(R.id.movieTime);
        movieSummaryInfo = findViewById(R.id.movieSummary);
        movieActorsInfo = findViewById(R.id.movieActorinfo);
        backImg = findViewById(R.id.backImg);
        recyclerViewCategory = findViewById(R.id.genreView);
        recyclerViewActors = findViewById(R.id.imagesRecycler);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());
    }
}