package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {

    Animation anim,bottomanim,anim2;
    ImageView imageView;
    TextView name,label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        imageView = findViewById(R.id.image);
        name = findViewById(R.id.name);
        label = findViewById(R.id.label);

        anim = AnimationUtils.loadAnimation(this,R.anim.anim);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottomanim);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.anim2);

        imageView.setAnimation(anim);
        name.setAnimation(bottomanim);
        label.setAnimation(anim2);


        Handler hi = new Handler();
        hi.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },4000);

    }
}