package com.app.arAnotomization;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileWriter;

public class ScoreActivity extends AppCompatActivity {
    private TextView scored,total,cmTimer;
    int finished;
    private Button donebtn;
    int facetime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scored= findViewById(R.id.scored);
        total= findViewById(R.id.total);
        cmTimer = findViewById(R.id.cmTimer);
        donebtn= findViewById(R.id.done_btn);
        String t= getIntent().getStringExtra("seconds");
        final String category = getIntent().getStringExtra("category");
        final String testSeconds = t.substring(0,4);
        String f = (getIntent().getStringExtra("faceseconds"));
        finished = (getIntent().getIntExtra("finished",0));
          facetime = Integer.parseInt(f.substring(3));
       int mins =  (Integer.parseInt(f.substring(0,2))) *60;
        facetime = facetime+mins;

        total.setText("Out Of "+(getIntent().getIntExtra("total",0)));
        cmTimer.setText(Html.fromHtml("<p align=”justify>Test was solved in "+testSeconds+" and face detected time was "+facetime+" seconds</p>"));
      final String score=(String.valueOf(getIntent().getIntExtra("scored",0)));
        scored.setText(score);

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(category.equalsIgnoreCase("IQ"))
                {
//

                    File file = new File(ScoreActivity.this.getFilesDir(), "text");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        File gpxfile = new File(file, "IQdata");
                        FileWriter writer = new FileWriter(gpxfile);
                        String fileContents= score+"**"+facetime+"**"+testSeconds;
                        writer.append(fileContents);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) { }

                }
                if(category.equalsIgnoreCase("Shape Test"))
                {
                    File file = new File(ScoreActivity.this.getFilesDir(), "text");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        File gpxfile = new File(file, "shapedata");
                        FileWriter writer = new FileWriter(gpxfile);
                        String fileContents= score+"**"+facetime+"**"+testSeconds;
                        writer.append(fileContents);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) { }
                }
                if(category.equalsIgnoreCase("Arithmetic Test"))
                {
                    File file = new File(ScoreActivity.this.getFilesDir(), "text");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        File gpxfile = new File(file, "arithmeticdata");
                        FileWriter writer = new FileWriter(gpxfile);
                        String fileContents= score+"**"+facetime+"**"+testSeconds;
                        writer.append(fileContents);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) { }
                }
                if(category.equalsIgnoreCase("Similarity Test"))
                {
                    File file = new File(ScoreActivity.this.getFilesDir(), "text");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        File gpxfile = new File(file, "similaritydata");
                        FileWriter writer = new FileWriter(gpxfile);
                        String fileContents= score+"**"+facetime+"**"+testSeconds;
                        writer.append(fileContents);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) { }
                }
                if(category.equalsIgnoreCase("Speech Test"))
                {
                    File file = new File(ScoreActivity.this.getFilesDir(), "text");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        File gpxfile = new File(file, "speechdata");
                        FileWriter writer = new FileWriter(gpxfile);
                        String fileContents= score+"**"+facetime+"**"+testSeconds;
                        writer.append(fileContents);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) { }
                }
                Intent intent = new Intent(ScoreActivity.this,CategoriesActivity.class);
                intent.putExtra("finished",finished+1);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed()
    {

    }
}
