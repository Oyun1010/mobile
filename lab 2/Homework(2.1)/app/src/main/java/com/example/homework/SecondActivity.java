package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        value = (TextView) findViewById(R.id.textVal);
        Intent intent = getIntent();
        String val = intent.getStringExtra(MainActivity.EXTRA_VALUE);
        value.setText(val);

    }
}