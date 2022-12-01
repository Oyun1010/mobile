package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private TextView mshowCount;
    public  static  final  String EXTRA_VALUE = ".SecondActivity.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mshowCount = (TextView) findViewById(R.id.show_count);

    }
    public void countUp(View view) {
        count++;
        if(mshowCount != null) {
            mshowCount.setText(Integer.toString(count));
        }

    }
    public  void secondButton(View view) {
        Log.d("", "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String value = mshowCount.getText().toString();
        Log.d("value ", value);
        intent.putExtra(EXTRA_VALUE, value);
        Log.d("Extra val ", EXTRA_VALUE);
        startActivity(intent);



    }
}