package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import data.DatabaseHandler;


public class Settings_Activity extends AppCompatActivity {
    private Intent intent;
    String preferences = "com.example.myapplication";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    int viewModelMain = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        intent = new Intent(getApplicationContext(), MainActivity.class);

        radioButton1 = findViewById(R.id.select1);
        radioButton2 = findViewById(R.id.select2);
        radioButton3 = findViewById(R.id.select3);

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        viewModelMain = sharedPreferences.getInt("viewMode", 0);
        editor = sharedPreferences.edit();

        if(viewModelMain == 0) {
            radioButton1.setChecked(true);
        }
        else if(viewModelMain == 1) {
            radioButton2.setChecked(true);

        }
        else if(viewModelMain == 2) {
            radioButton2.setChecked(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    public void save_btn(View view) {
         startActivity(intent);
        Toast.makeText(getApplicationContext(), "Амжилттай хадгаллаа", Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.select1:
                if (checked) {
                    editor.putInt("viewMode", 0);
                    editor.commit();
                }
                break;
            case R.id.select2:
                if (checked) {
                    editor.putInt("viewMode", 1);
                    editor.commit();
                }
                break;
            case R.id.select3:
                if (checked) {
                    editor.putInt("viewMode", 2);
                    editor.commit();
                }
                break;
            default:
                break;
        }

    }

    public void back_btn(View view) {
        Toast.makeText(getApplicationContext(), "Болих", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}