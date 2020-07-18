package com.app.arAnotomization;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.FotoapparatSwitcher;
import io.fotoapparat.facedetector.Rectangle;
import io.fotoapparat.facedetector.processor.FaceDetectorProcessor;
import io.fotoapparat.facedetector.view.RectanglesView;
import io.fotoapparat.parameter.LensPosition;
import io.fotoapparat.view.CameraView;

import static io.fotoapparat.log.Loggers.fileLogger;
import static io.fotoapparat.log.Loggers.logcat;
import static io.fotoapparat.log.Loggers.loggers;
import static io.fotoapparat.parameter.selector.LensPositionSelectors.lensPosition;

public class SimilarityActivity extends AppCompatActivity  {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private TextView question,no_indicator;
    private Dialog loadingDialog;
    private GridLayout options_container;
    private Button option1,option2;
    ImageView imageRurl,imageLurl;

    private int count = 0;
    private String category;
    private int setNo;
    List<SimilarityModel> list;
    private int position= 0;
    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);
    private boolean hasCameraPermission;
    private CameraView cameraView;
    private RectanglesView rectanglesView;

    private FotoapparatSwitcher fotoapparatSwitcher;
    private Fotoapparat frontFotoapparat;
    private Fotoapparat backFotoapparat;
    Time t;
    Chronometer cmTimer;
    Boolean resume = false;
    private long pauseOffset;
    private int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        question = findViewById(R.id.question);
        no_indicator = findViewById(R.id.no_indicator);
        options_container = findViewById(R.id.options_container);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        imageLurl = findViewById(R.id.imageLurl);
        imageRurl = findViewById(R.id.imageRurl);
        category= getIntent().getStringExtra("category");
        setNo= getIntent().getIntExtra("setNo",1);
        cmTimer = (Chronometer) findViewById(R.id.cmTimer);
        t = new Time();
        t.start();

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corners));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        list = new ArrayList<>();


        loadingDialog.show();
        myRef.child("SETS").child(category).child("questions").orderByChild("setNo")
                .equalTo(setNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    list.add(dataSnapshot1.getValue(SimilarityModel.class));
                }
                if (list.size()>0)
                {

                    playAnim(question,0,list.get(position).getQuestion());
                    option1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String a =(String)option1.getText();
                            if (a.equals(list.get(position).getCorrectANS()))
                            {
                                score++;
                            }
                            position++;


                            if (position == list.size())
                            {
                                Intent scoreIntent = new Intent(SimilarityActivity.this,ScoreActivity.class);
                                String s   =  cmTimer.getText().toString();
                                String timer=  t.toString();
                                scoreIntent.putExtra("seconds",timer);
                                scoreIntent.putExtra("category",category);
                                scoreIntent.putExtra("faceseconds",s);

                                scoreIntent.putExtra("scored",score);


                                scoreIntent.putExtra("total",list.size());
                                startActivity(scoreIntent);

                                return;
                            }
                            count=0;
                            playAnim(question,0,list.get(position).getQuestion());
                        }
                    });
                    option2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String a =(String)option2.getText();
                            if (a.equals(list.get(position).getCorrectANS()))
                            {
                                score++;
                            }
                            position++;

                            if (position == list.size())
                            {
                                Intent scoreIntent = new Intent(SimilarityActivity.this,ScoreActivity.class);
                                scoreIntent.putExtra("scored",score);
                                scoreIntent.putExtra("category",category);
                                String timer=  t.toString();
                                scoreIntent.putExtra("seconds",timer);
                                String s   =  cmTimer.getText().toString();


                                scoreIntent.putExtra("faceseconds",s);
                                scoreIntent.putExtra("total",list.size());
                                startActivity(scoreIntent);


                                return;
                            }
                            count=0;
                            playAnim(question,0,list.get(position).getQuestion());
                        }
                    });


                }
                else
                {

                    Toast.makeText(SimilarityActivity.this, "No Questions", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SimilarityActivity.this, databaseError.getMessage()
                        , Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();

            }
        });

//        for facedetection
        cameraView = (CameraView) findViewById(R.id.camera_view);
        rectanglesView = (RectanglesView) findViewById(R.id.rectanglesView);
        hasCameraPermission = permissionsDelegate.hasCameraPermission();

        if (hasCameraPermission) {
            cameraView.setVisibility(View.VISIBLE);
        } else {
            permissionsDelegate.requestCameraPermission();
        }

        frontFotoapparat = createFotoapparat(LensPosition.FRONT);
        backFotoapparat = createFotoapparat(LensPosition.BACK);
        fotoapparatSwitcher = FotoapparatSwitcher.withDefault(frontFotoapparat);


    }



    //facedetection methods












    private Fotoapparat createFotoapparat(LensPosition position) {
        return Fotoapparat
                .with(this)
                .into(cameraView)
                .lensPosition(lensPosition(position))
                .frameProcessor(
                        FaceDetectorProcessor.with(this)
                                .listener(new FaceDetectorProcessor.OnFacesDetectedListener() {
                                    @Override
                                    public void onFacesDetected(List<Rectangle> faces) {
                                        if(faces.size()>0)
                                        {
                                            if (!resume)
                                            {
                                                cmTimer.setBase(SystemClock.elapsedRealtime()- pauseOffset);
                                                cmTimer.start();
                                                resume = true;

                                            }
                                        }
                                        if(faces.size()==0)
                                        {
                                            if (resume) {
                                                cmTimer.stop();
                                                pauseOffset = SystemClock.elapsedRealtime() - cmTimer.getBase();
                                                resume = false;
                                            }
                                        }
                                        Log.d("&&&", "Detected faces: " + faces.size());

                                        rectanglesView.setRectangles(faces);
                                    }
                                })
                                .build()
                )
                .logger(loggers(
                        logcat(),
                        fileLogger(this)
                ))
                .build();
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (hasCameraPermission) {
            fotoapparatSwitcher.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (hasCameraPermission) {
            fotoapparatSwitcher.stop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            fotoapparatSwitcher.start();
            cameraView.setVisibility(View.VISIBLE);
        }
    }

    private void playAnim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).
                setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && count < 2) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getOption1();
                    } else if (count == 1) {
                        option = list.get(position).getOption2();
                    }
                    playAnim(options_container.getChildAt(count), 0, option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        Glide.with(view.getContext()).load(list.get(position).getImageLurl()).into(imageLurl);
                        Glide.with(view.getContext()).load(list.get(position).getImageRurl()).into(imageRurl);
                        no_indicator.setText(position + 1 + "/" + list.size());
                    } catch (ClassCastException e) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    public void onBackPressed()
    {

    }



}