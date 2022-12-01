package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import data.DatabaseHandler;
import model.Word;

public class CU_Acttivity extends AppCompatActivity {
    private EditText engWordEdit, monWordEdit;
    DatabaseHandler db;
    Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cu_acttivity);
        db = new DatabaseHandler(this);
        word = new Word();


        engWordEdit = (EditText) findViewById(R.id.eng_world_input);
        monWordEdit = (EditText) findViewById(R.id.mon_world_input);
        if(getIntent().getExtras().getBoolean("create") == false) {
            engWordEdit.setText(getIntent().getExtras().getString("engWord"));
            monWordEdit.setText(getIntent().getExtras().getString("monWord"));
        }

        Log.d("engWordEdit", engWordEdit.getText().toString());
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
    public void input_btn(View view) {

            word.setEngWord(engWordEdit.getText().toString());
            word.setMonWord(monWordEdit.getText().toString());
            if(getIntent().getExtras().getBoolean("create") == true) {
                boolean isInput = db.insertData(word);
                if(isInput) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    displayToast("Амжилтай хадгаллаа");
                }
                else {
                    displayToast("Алдаа гарлаа дахин оролдоно уу.");
                }
            }
            else {
//                engWordEdit.setText("HELLO");
//                monWordEdit.setText(getIntent().getExtras().getString("monWord"));
                word.setItemID(getIntent().getExtras().getInt("updateIndex"));
                boolean isUpdate = db.updateData(word);
                if(isUpdate) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    displayToast("Амжилтай шинэчлэлээ.");
                }
                else {
                    displayToast("Алдаа гарлаа дахин оролдоно уу.");
                }
            }
    }
    public void cancel_btn(View view) {
        Toast.makeText(getApplicationContext(), "Болих", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}