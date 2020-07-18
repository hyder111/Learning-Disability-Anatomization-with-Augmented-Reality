package com.app.arAnotomization;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisclaimerActivity extends AppCompatActivity {
    TextView disclaimer;
    Button agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        disclaimer=findViewById(R.id.disclaimer);
        agree = findViewById(R.id.agree);
        String info= "<p align=”justify”>This evaluation and Tests are based " +
                "on <b>Diagnostic and Statistical Manual of Mental Disorders, " +
                "5th Edition: DSM-5.</b> THIS APPLICATION IS ONLY FOR MEDICAL PROFESSIONALS" +
                " and PRACTITIONERS. Evaluation at the end of these tests does not verify " +
                "that the child suffers from any form of disability. Professional consultation from a trained medical professional is <b>Mandatory</b>. </p><br>" +
                "<b>Instructions:</b><br>" +
                "\u25BA There are 5 Categories of tests.<br>" +
                "\u25BA Each test has total of 10 score.<br>" +
                "\u25BA The time in which each test is solved will be recorded and saved for evaluation purposes.<br>" +
                "\u25BA Face Detection Time will be recorded to check whether the child has paid attention while solving.<br>" +
                "\u25BA All tests must be completed for evaluation.<br>" +
                "\u25BA One test can be taken multiple times but only the last test will be scored.<br>";
        disclaimer.setText(Html.fromHtml(info));

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisclaimerActivity.this,MainPage.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    @Override
    public void onBackPressed()
    {

    }
}
