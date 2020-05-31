package com.example.quizzer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {
    TextView date;
    EditText e_name,e_pname,e_age,e_headSize;
    private static final  String TAG = "FormActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Spinner spinner,spinnerFine,spinnerGross,spinnerExpressive,spinnerHygiene;
    Toolbar toolbar;
    private String file= "myfile";
    private String fileContents,name,Pname,age,gender,dob="",headSize
            ,fineMotor,grossMotor,expressive,hygiene;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button next;
    int check=0,fits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
       toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        date = (TextView) findViewById(R.id.date);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerFine = (Spinner) findViewById(R.id.spinnerFine);
        spinnerGross = (Spinner) findViewById(R.id.spinnergross);
        spinnerExpressive = (Spinner) findViewById(R.id.spinnerExpressive);
        spinnerHygiene = (Spinner) findViewById(R.id.spinnerHygiene);
        radioGroup= findViewById(R.id.radio);
        int radioID=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioID);
        if(radioButton.getText().equals("Yes"))
        {
            fits=1;

        }
        else
        {
            fits=0;
        }
        next = findViewById(R.id.next);
        e_name = findViewById(R.id.childname);
        e_pname = findViewById(R.id.parentname);
        e_age = findViewById(R.id.age);
        e_headSize=findViewById(R.id.headSize);





        ArrayAdapter<String>myadapter= new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.gender));
        myadapter.setDropDownViewResource
                (R.layout.spinner_layout);
        spinner.setAdapter(myadapter);



        ArrayAdapter<String>myadapter2= new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.range));
        myadapter.setDropDownViewResource
                (R.layout.spinner_layout);
        spinnerFine.setAdapter(myadapter2);
        spinnerGross.setAdapter(myadapter2);
        spinnerExpressive.setAdapter(myadapter2);
        spinnerHygiene.setAdapter(myadapter2);




      date.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Calendar calendar = Calendar.getInstance();
              int year = calendar.get(Calendar.YEAR);
              int month = calendar.get(Calendar.MONTH);
              int day = calendar.get(Calendar.DAY_OF_MONTH);

              DatePickerDialog dialog = new DatePickerDialog(
                      FormActivity.this,
                      android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                      mDateSetListener,
                      year,month,day);
              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
              dialog.show();


          }
      });
      mDateSetListener = new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              month=month+1;
              Log.d(TAG,"onDateSet: mm/dd/yyyy: "+month+"/"+dayOfMonth+"/"+year);
               dob  = dayOfMonth +"/" +month+"/"+year;
                date.setText(dob);
          }
      };
      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                name= e_name.getText().toString();
            Pname= e_pname.getText().toString();
            gender= spinner.getSelectedItem().toString();
            fineMotor=spinnerFine.getSelectedItem().toString();
            grossMotor=spinnerGross.getSelectedItem().toString();
            expressive=spinnerExpressive.getSelectedItem().toString();
            hygiene=spinnerHygiene.getSelectedItem().toString();
              age= e_age.getText().toString();
              headSize=e_headSize.getText().toString();

                  if (dob.equals("")||name.equals("")||gender.equals("")||
                          age.equals("")||Pname.equals("")||headSize.equals(""))
                  {
                      Toast.makeText(FormActivity.this, "Fill all fields"
                              , Toast.LENGTH_SHORT).show();
                      check=10;
                  }
                  else if(!dob.equals("")&&!name.equals("")&&!gender.equals("")&&
                          !age.equals("")&&!Pname.equals("")&&!headSize.equals(""))
                  {
                      check=0;
                  }




              fileContents= name+"**"+Pname+"**"+age+"**"+gender+"**"+dob+"**"+headSize+"**"+fits+
                      "**"+hygiene+"**"+fineMotor+"**"+grossMotor+"**"+expressive+ "\n";
              if(check==10)
              {

              }
              else
              {
                  try {
                      FileOutputStream  fout = openFileOutput(file,MODE_APPEND);
                      fout.write(fileContents.getBytes());
                      fout.close();
                      File fileDir = new File(getFilesDir(),file);
                      Toast.makeText(FormActivity.this, "File saved at "+fileDir, Toast.LENGTH_SHORT).show();
                  }
                  catch (Exception e)
                  {
                      e.printStackTrace();
                  }
                  Intent form = new Intent(FormActivity.this,CategoriesActivity.class);
                  startActivity(form);
              }

          }
      });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
