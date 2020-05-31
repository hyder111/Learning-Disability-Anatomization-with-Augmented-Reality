package com.example.quizzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ScoreActivity extends AppCompatActivity {
    private TextView scored,total,cmTimer;
    int finished;
    private Button donebtn;
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
        String testSeconds = t.substring(0,4);
        String f = (getIntent().getStringExtra("faceseconds"));
        finished = (getIntent().getIntExtra("finished",0));
        int facetime = Integer.parseInt(f.substring(3));
       int mins =  (Integer.parseInt(f.substring(0,2))) *60;
        facetime = facetime+mins;

        total.setText("Out Of "+(getIntent().getIntExtra("total",0)));
        cmTimer.setText("Test was solved in "+testSeconds+" and face detected time was "+facetime+" seconds");
       scored.setText(String.valueOf(getIntent().getIntExtra("scored",0)));
        final QuestionActivity q = new QuestionActivity();
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







                Intent intent = new Intent(ScoreActivity.this,CategoriesActivity.class);
                intent.putExtra("finished",finished+1);
                startActivity(intent);
            }
        });


    }
}
