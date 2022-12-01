package com.example.pickerfordate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public void processDatePickerResult(int year, int month, int day) {
        String month_str = Integer.toString(month + 1);
        String day_str = Integer.toString(day);
        String year_str = Integer.toString(year);
        String dateMessage = (month_str + "/" + day_str + "/" + year_str);
        Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();

    }
}