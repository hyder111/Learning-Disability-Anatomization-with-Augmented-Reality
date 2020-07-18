package com.app.arAnotomization;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    int finished;
    Button predict;
    String category;
    private  List<CategoryModel> list;
        private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Categories");

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corners));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
         category = getIntent().getStringExtra("category");
        recyclerView = findViewById(R.id.rv);
        predict=findViewById(R.id.predict);
    predict.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent resultIntent = new Intent(CategoriesActivity.this,LoadingActivity.class);

            startActivity(resultIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    });



            try {
                String file1="check";
                String fileContents=category+ "\n";
                FileOutputStream fout = openFileOutput(file1, MODE_APPEND);
                fout.write(fileContents.getBytes());
                fout.close();
                File fileDir = new File(getFilesDir(), file1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (readFile().contains("IQ")&&
                readFile().contains("Shape Test")&&
                    readFile().contains("Arithmetic Test")&&
                    readFile().contains("Similarity Test")&&
                    readFile().contains("Speech Test"))
            {
                predict.setText("Predict Result");
                predict.setEnabled(true);
            }
//        }





        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        temporary list created here
       list = new ArrayList<>();



        final CategoryAdaptor adaptor = new CategoryAdaptor(list,finished);
        recyclerView.setAdapter(adaptor);

        loadingDialog.show();
        myRef.child("Categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
               {
                   list.add(dataSnapshot1.getValue(CategoryModel.class));
               }
               adaptor.notifyDataSetChanged();
               loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoriesActivity.this,
                        databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });


    }
    @Override
    public void onBackPressed()
    {

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    private String readFile() {
        File fileEvents = new File(CategoriesActivity.this.getFilesDir()+"/check");
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
}
