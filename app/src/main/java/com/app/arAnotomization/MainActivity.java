package com.app.arAnotomization;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView logo;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo= findViewById(R.id.logo);
        text= findViewById(R.id.textview11);
        logo.setAnimation(topAnim);
        text.setAnimation(bottomAnim);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,DisclaimerActivity.class);
                startActivity(intent);

                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        },3500);
    }
}
