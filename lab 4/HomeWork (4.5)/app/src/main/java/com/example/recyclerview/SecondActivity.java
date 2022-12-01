package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView ingredientsView;
    private TextView procedureView;

    private String[] ingredients;
    private String[] procedure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView  = findViewById(R.id.image1);
        ingredientsView = findViewById(R.id.ingredients);
        procedureView = findViewById(R.id.procedure);

        ingredients = getResources().getStringArray(R.array.ingredients);
        procedure = getResources().getStringArray(R.array.procedure);


        imageView.setImageResource(getIntent().getExtras().getInt("image"));

        for(int i = 0; i < ingredients.length; i++) {
            if(i == getIntent().getExtras().getInt("index")) {
                ingredientsView.setText(ingredients[i]);
                procedureView.setText(procedure[i]);
            }
        }

    }
}