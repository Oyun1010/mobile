package com.example.homework22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  int count = 0;
    private TextView showCount;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount = (TextView) findViewById(R.id.count_value);
        editText = (EditText) findViewById(R.id.edit_text);
        if(savedInstanceState != null) {
            Log.d("edit text", savedInstanceState.getString("edit_text"));
            showCount.setText(savedInstanceState.getString("count_val"));
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("edit_text", editText.getText().toString());
        outState.putString("count_val", showCount.getText().toString());
    }
    public void countUp(View view) {
        count++;
        if(showCount != null) {
            showCount.setText(Integer.toString(count));
        }

    }
}