package com.example.quizzer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private int sets = 0;
    private String category;
    int finished;


    public GridAdapter(int sets,String category,int f) {
        this.category=category;
        this.sets = sets;
        this.finished=f;
    }

    public GridAdapter() {

    }

    @Override
    public int getCount() {
        return sets;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView ==null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item,parent,false);


        }
        else
        {
            view = convertView;

        }
        if (category.equals("Similarity Test"))
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent questionIntent = new Intent(parent.getContext(),SimilarityActivity.class);
                    questionIntent.putExtra("category",category);
                    questionIntent.putExtra("setNo",position+1);
                    questionIntent.putExtra("finished",finished);


                    parent.getContext().startActivity(questionIntent);
                }
            });
        }
        else {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent questionIntent = new Intent(parent.getContext(), QuestionActivity.class);
                    questionIntent.putExtra("category", category);


                    questionIntent.putExtra("setNo", position + 1);
                    questionIntent.putExtra("finished",finished);


                    parent.getContext().startActivity(questionIntent);
                }
            });
        }

        ((TextView)view.findViewById(R.id.textview)).setText(String.valueOf(position+1));
        return view;
    }
}
