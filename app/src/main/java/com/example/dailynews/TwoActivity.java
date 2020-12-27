package com.example.dailynews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dailynews.Fragment.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TwoActivity extends AppCompatActivity {

    ImageView image;
    TextView title,pub,contenet,author;
    Button detail;
    FloatingActionButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_two);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = findViewById(R.id.selectimage);
        title = findViewById(R.id.title);
        pub = findViewById(R.id.publishedat);
        contenet = findViewById(R.id.content);
        detail = findViewById(R.id.url);
        author = findViewById(R.id.author);
        share = findViewById(R.id.share);

        Glide.with(TwoActivity.this).load(getIntent().getExtras().get("image")).into(image);
        title.setText(getIntent().getStringExtra("title"));
        pub.setText(getIntent().getStringExtra("publishat"));
        contenet.setText(getIntent().getStringExtra("content"));
        author.setText(getIntent().getStringExtra("author"));

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("url")));
                startActivity(Getintent);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,getIntent().getStringExtra("title")+"\n\n"+"\nHere is the Link to the full content :"+getIntent().getStringExtra("url")+"\n\nSent From Daily News App");
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}