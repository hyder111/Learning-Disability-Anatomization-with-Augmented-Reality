package com.app.arAnotomization;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainPage extends AppCompatActivity {
    private Button startbtn,credits,exitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbtn = findViewById(R.id.start_btn);
        exitBtn=findViewById(R.id.exit);
        credits=findViewById(R.id.credits);
       final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

       credits.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog alertDialog = new AlertDialog.Builder(MainPage.this).create();
               alertDialog.setTitle("Credits");
               alertDialog.setMessage(Html.fromHtml("<p align=”justify><b><i>We would like to " +
                       "specially thank to:</b></i><br>" +
                       "\u25BA Lt Col Umbrin Koukab (Armed Forces Institute of Cardiology)<br>" +
                       "\u25BA Maj Ammar (Armed Forces Institute of Mental Health)<br>" +
                       "\u25BA Maj Fareeha (Armed Forces Institute of Mental Health)<br>" +
                       "\u25BA Dr Laila Khan (Medical Officer, Special Children Institute, H8)<br>" +
                       "\u25BA Dr Shamim Akhtar (Special Children Institute, H8)<br>" +
                       "\u25BA Dr Ghulam e Murtaza Bodla (NIRM-National Institute of Rehabilitation Medicine)<br>" +
                       "\u25BA Mr Zameer Haider Rizvi (Motor Skills Trainer, National Institute of Special Education NISE)<br>" +
                       "</p>"));
               alertDialog.setIcon(R.drawable.logo);

               alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });

               alertDialog.show();
               Button buttonbackground1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
               buttonbackground1.setTextColor(Color.GREEN);
               buttonbackground1.setBackgroundColor(Color.GRAY);
           }
       });
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent form = new Intent(MainPage.this,FormActivity.class);
                Intent form = new Intent(MainPage.this,FormActivity.class);
                delFile();
                startActivity(form);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                builder1.setCancelable(true);
                builder1.setTitle("Exit Confirmation!");
                builder1.setMessage("You are about to exit this application. Do you want to proceed?");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.exit(0);
                            }
                        });

                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                Button buttonbackground = alert11.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonbackground.setTextColor(Color.GREEN);

                Button buttonbackground1 = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonbackground1.setTextColor(Color.RED);

            }
        });

    }
    private void delFile() {
        File fileEvents = new File(getFilesDir(),"check");
        if(fileEvents.exists())
        {
            deleteFile("check");
        }

    }
    @Override
    public void onBackPressed()
    {

    }

    }

