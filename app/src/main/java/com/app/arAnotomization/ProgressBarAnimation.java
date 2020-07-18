package com.app.arAnotomization;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation
{

    private Context context;
    private ProgressBar progressBar;
    private TextView textView,movingText;
    private float to,from;

    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView percentage,TextView movingText, float to, float from) {

        this.context = context;
        this.progressBar = progressBar;
        this.textView = percentage;
        this.movingText=movingText;

        this.to = to;
        this.from = from;
    }
    public void applyTransformation(float interpolatedTime, Transformation t)
    {
        super.applyTransformation(interpolatedTime,t);
        float value = from +(to-from)* interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value+" %");
        if (value == to)
        {
            Intent intent = new Intent(context,DisclaimerActivity.class);
            context.startActivity(intent);



        }

    }
}
