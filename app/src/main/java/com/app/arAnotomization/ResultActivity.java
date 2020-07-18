package com.app.arAnotomization;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResultActivity extends AppCompatActivity {
        BarChart mpbarChart;
        Button again,exit,save;
   String iqData,similarityData,speechData,infoData,arithmeticData,shapeData;
   TextView name,parentName,age,dob,gender,headSize,fits,hygiene,motor,gross,expressive,predict,iqSpan,arithSpan,speechSpan,shapeSpan,similaritySpan;
    ScrollView scrollView;
    LinearLayout ll_linear;
    private static int REQUEST_PERMISSIONS = 1;

    boolean boolean_permission;
    boolean boolean_save;
    boolean check;




   String[]iq=new String[3];
    String[]shapeArray=new String[3];
    String[]speechArray=new String[3];
    String[]similarityArray=new String[3];
    String[]arithmeticArray=new String[3];
    String[]info=new String[11];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scrollView = (ScrollView)findViewById(R.id.scrollView11);
        ll_linear = (LinearLayout) findViewById(R.id.linearLayout11);
        save=findViewById(R.id.save);
        fn_permission();

    iqData=readIQFile();
    shapeData=readShapeFile();
    speechData=readSpeechFile();
    similarityData=readSimilarityFile();
    infoData=readInfoFile();
    arithmeticData=readArithmeticFile();
        System.out.println(infoData);
        StringTokenizer infoTokens = new StringTokenizer(infoData,"**");
        StringTokenizer iqTokens = new StringTokenizer(iqData,"**");
        StringTokenizer similarityTokens = new StringTokenizer(similarityData,"**");
        StringTokenizer shapeTokens = new StringTokenizer(shapeData,"**");
        StringTokenizer arithmeticTokens = new StringTokenizer(arithmeticData,"**");
        StringTokenizer speechTokens = new StringTokenizer(speechData,"**");
        for (int i =0;i<11;i++)
        {
            info[i]=infoTokens.nextToken();
        }
        for (int i =0;i<3;i++)
        {
            iq[i]=iqTokens.nextToken();
            shapeArray[i]=shapeTokens.nextToken();
            speechArray[i]=speechTokens.nextToken();
            similarityArray[i]=similarityTokens.nextToken();
            arithmeticArray[i]=arithmeticTokens.nextToken();
        }


        name=findViewById(R.id.name);
        parentName=findViewById(R.id.mname);
        age=findViewById(R.id.age);
        dob=findViewById(R.id.dob);
        gender=findViewById(R.id.gender);
        headSize=findViewById(R.id.headsize);
        fits=findViewById(R.id.fits);
        hygiene=findViewById(R.id.hygiene);
        motor=findViewById(R.id.motor);
        gross=findViewById(R.id.gross);
        expressive=findViewById(R.id.expressive);
        predict=findViewById(R.id.prediction);
        again=findViewById(R.id.again);
        exit=findViewById(R.id.exit);
        iqSpan=findViewById(R.id.iq_span);
        shapeSpan=findViewById(R.id.shape_span);
        similaritySpan=findViewById(R.id.similarity_span);
        speechSpan=findViewById(R.id.speech_span);
       arithSpan=findViewById(R.id.arith_span);
        int a = (int) ((Float.parseFloat(iq[1])/Float.parseFloat(iq[2]))*100);
        int b = (int) ((Float.parseFloat(shapeArray[1])/Float.parseFloat(shapeArray[2]))*100);
        int c =(int) ((Float.parseFloat(similarityArray[1])/Float.parseFloat(similarityArray[2]))*100);
        int d = (int) ((Float.parseFloat(speechArray[1])/Float.parseFloat(speechArray[2]))*100);
        int e  =(int) ((Float.parseFloat(arithmeticArray[1])/Float.parseFloat(arithmeticArray[2]))*100);
        iqSpan.setText(a+"%");
        shapeSpan.setText(b+"%");
        similaritySpan.setText(c+"%");
        speechSpan.setText(d+"%");
        arithSpan.setText(e+"%");
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        name.setText(info[0]);
        parentName.setText(info[1]);
        age.setText(info[2]);
        gender.setText(info[3]);
        dob.setText(info[4]);
        headSize.setText(info[5]);
        fits.setText(info[6]);
        hygiene.setText(info[7]);
        motor.setText(info[8]);
        gross.setText(info[9]);
        expressive.setText(info[10]);

AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();
                Request request= new Request.Builder()
                        .url(" https://ardisability.herokuapp.com/ldml/"+info[2]+"/"+info[5]+"/"+
                                info[6]+"/"+info[7]+"/"+info[8]+"/" +
                                info[9]+"/"+info[10]+"/"+iq[0]+"/"+(int)(Float.parseFloat(iq[2]))+"/" +
                                iq[1]+"/"+similarityArray[0]+"/"+
                                (int)(Float.parseFloat(similarityArray[2]))+
                                "/"+similarityArray[1] +
                                "/"+arithmeticArray[0]+"/"+(int)(Float.parseFloat(arithmeticArray[2]))+"/"+arithmeticArray[1] +
                                "/"+shapeArray[0] +
                                "/"+(int)(Float.parseFloat(shapeArray[2]))+"/"+shapeArray[1] +
                                "/7/72" +
                                "/54/"+speechArray[0]+"/"+(int)(Float.parseFloat(speechArray[2]))+"/")
                        .build();
                Response response = null;
                try {
                    response= client.newCall(request).execute();
                    return  response.body().string();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }



                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(o.toString().substring(10,11).contains("1"))
                {
                    predict.setText(Html.fromHtml("<p align=”justify>According " +
                            "to the <b>DSM Edition 5</b>, the data provided by" +
                            " the Doctor/Practitioner and the all the results" +
                            " obtained from the tests performed by the child concludes " +
                            "<b> Child has Learning Disability</b><br>" +
                            "This application <b>Does not</b> verify that a" +
                            " child suffers from any disability. <b>Consultation from a trained medical professional is Mandatory</b>.</p>"));
                }
                else
                {
                    predict.setText(Html.fromHtml("<p align=”justify>According " +
                            "to the <b>DSM Edition 5</b>, the data provided by" +
                            " the Doctor/Practitioner and the all the results" +
                            " obtained from the tests performed by the child concludes " +
                            "<b> Child DOES NOT has Learning Disability</b><br>" +
                            "This application <b>Does not</b> verify that a" +
                            " child suffers from any disability. <b>Consultation from a trained medical professional is Mandatory</b>.</p>"));
                }
            }
        }.execute();






       mpbarChart = findViewById(R.id.barChart);
        BarDataSet barDataSet1 = new BarDataSet(scoreEntries(),"Score out of 10");
        barDataSet1.setColor(Color.MAGENTA);

        BarDataSet barDataSet2 = new BarDataSet(faceEntries(),"Facial Detection in Seconds");
        barDataSet2.setColor(Color.YELLOW);

        BarDataSet barDataSet3 = new BarDataSet(testEntries(),"Test Time");
        barDataSet3.setColor(Color.GREEN);


        BarData data = new BarData(barDataSet1,barDataSet2,barDataSet3);
        mpbarChart.setData(data);

        String[] tests = new String[]{"IQ","Arithmetic","Similarity","Speech","Shape"};
        XAxis xAxis = mpbarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(tests));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);


        mpbarChart.setDragEnabled(true);
        mpbarChart.setVisibleXRangeMaximum(3);

        float barSpace = 0.05f;
        float groupSpace = 0.37f;
        data.setBarWidth(0.16f);

        mpbarChart.getXAxis().setAxisMinimum(0);
        mpbarChart.getXAxis().setAxisMaximum(0+mpbarChart.getBarData().getGroupWidth(groupSpace,barSpace)*8);
        mpbarChart.getAxisLeft().setAxisMinimum(0);

        mpbarChart.groupBars(0,groupSpace,barSpace);

        mpbarChart.invalidate();

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder1.setCancelable(true);
                builder1.setTitle("Start Again!");
                builder1.setMessage("You are about to start tests again. Do you want to proceed?");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent form = new Intent(ResultActivity.this,MainPage.class);
                                startActivity(form);
                                finish();
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                buttonbackground.setTextColor(Color.RED);

                Button buttonbackground1 = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonbackground1.setTextColor(Color.GREEN);



            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap1 = loadBitmapFromView(ll_linear, ll_linear.getWidth(), ll_linear.getHeight());
                saveBitmap(bitmap1);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder1.setCancelable(true);
                builder1.setTitle("Exit Application!");
                builder1.setMessage("You are about to Exit the application. Have you saved the results? Do you want to proceed?");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finishAffinity();

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
                buttonbackground.setTextColor(Color.RED);

                Button buttonbackground1 = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonbackground1.setTextColor(Color.GREEN);



            }
        });


    }
private ArrayList<BarEntry>scoreEntries()
{
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    barEntries.add(new BarEntry(1,Integer.parseInt(iq[0])));//scoreiq
    barEntries.add(new BarEntry(1,Integer.parseInt(arithmeticArray[0])));
    barEntries.add(new BarEntry(1,Integer.parseInt(similarityArray[0])));
    barEntries.add(new BarEntry(1,Integer.parseInt(speechArray[0])));
    barEntries.add(new BarEntry(1,Integer.parseInt(shapeArray[0])));

    return barEntries;
}
private ArrayList<BarEntry> faceEntries()
{

    ArrayList<BarEntry> barEntries = new ArrayList<>();
    barEntries.add(new BarEntry(1,Integer.parseInt(iq[1])));//faceiq
    barEntries.add(new BarEntry(1,Integer.parseInt(arithmeticArray[1])));
    barEntries.add(new BarEntry(1,Integer.parseInt(similarityArray[1])));
    barEntries.add(new BarEntry(1,Integer.parseInt(speechArray[1])));
    barEntries.add(new BarEntry(1,Integer.parseInt(shapeArray[1])));


    return barEntries;
}
    private ArrayList<BarEntry> testEntries()
    {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,Float.parseFloat(iq[2])));//testtime
        barEntries.add(new BarEntry(1,Float.parseFloat(arithmeticArray[2])));
        barEntries.add(new BarEntry(1,Float.parseFloat(similarityArray[2])));
        barEntries.add(new BarEntry(1,Float.parseFloat(speechArray[2])));
        barEntries.add(new BarEntry(1,Float.parseFloat(shapeArray[2])));


        return barEntries;
    }

    private String readIQFile() {
        File fileEvents = new File(ResultActivity.this.getFilesDir()+"/text/IQdata");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine())!= null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }
    private String readArithmeticFile() {
        File fileEvents = new File(ResultActivity.this.getFilesDir()+"/text/arithmeticdata");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine())!= null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }
    private String readShapeFile() {
        File fileEvents = new File(ResultActivity.this.getFilesDir()+"/text/shapedata");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine())!= null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }
    private String readSimilarityFile() {
        File fileEvents = new File(ResultActivity.this.getFilesDir()+"/text/similaritydata");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine())!= null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }
    private String readSpeechFile() {
        File fileEvents = new File(ResultActivity.this.getFilesDir()+"/text/speechdata");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine())!= null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }
    private String readInfoFile() {
        File fileEvents = new File(getFilesDir()+"/text/childinfo");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine())!= null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }

public void saveBitmap(Bitmap bitmap) {
    String root=Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS).toString();
    File dir = new File(root);
    if (!dir.exists()) {
        dir.mkdirs();
    }
    File imagePath = new File(dir,info[0]+".jpg");

    FileOutputStream fos;
    try {
        fos = new FileOutputStream(imagePath);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.flush();
        fos.close();

        if (check)
        {
            Toast.makeText(getApplicationContext(),"Result already saved.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Result saved at downloads folder",Toast.LENGTH_SHORT).show();
        }
        check=true;

        boolean_save = true;



    } catch (FileNotFoundException e) {

    } catch (IOException e) {

    }
}

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        c.drawPaint(paint);
        v.draw(c);

        return b;
    }

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(ResultActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(ResultActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;


            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }
    @Override
    public void onBackPressed()
    {

    }

}
