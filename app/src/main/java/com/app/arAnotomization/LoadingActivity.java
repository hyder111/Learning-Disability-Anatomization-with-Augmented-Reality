package com.app.arAnotomization;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView percentage,movingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressBar);
        percentage = findViewById(R.id.percentage);

        movingText =(TextView) findViewById(R.id.Prediciting);
        movingText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        movingText.setSelected(true);
        movingText.animate();
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();

    }
    public void progressAnimation()
    {

        ProgressBarAnimation amin = new ProgressBarAnimation(this,progressBar,
               percentage,movingText,100f,0f);
        amin.setDuration(10000);
        progressBar.setAnimation(amin);


    }
    @Override
    public void onBackPressed()
    {

    }
}
