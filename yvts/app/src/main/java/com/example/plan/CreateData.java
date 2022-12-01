package com.example.plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Plan;

public class CreateData extends AppCompatActivity {

    private EditText dateTimeEditText;
    private EditText toDoEditText;
    DatabaseHandler db;
    Plan plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);
        db = new DatabaseHandler(this);
        plan = new Plan();

        toDoEditText = findViewById(R.id.insert_data);
        dateTimeEditText = findViewById(R.id.insert_dateTime);


    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void add_btn(View view) {
        if(toDoEditText.getText().toString() != "" && dateTimeEditText.getText().toString() != "") {
            plan.setToDo(toDoEditText.getText().toString());
            plan.setDateTime(dateTimeEditText.getText().toString());
            boolean isInput = db.insertData(plan);
            if(isInput) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                displayToast("Амжилттай нэмэллээ.");
            }
            else {
                displayToast("Алдаа гарлаа дахин оролдоно уу.");

            }

        }
        else {
            displayToast("Дээрх талбаруудыг оруулна уу.");
        }
    }
    public void cancel_btn(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePciker");
    }
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (year_string +
                "/" + month_string + "/" + day_string);
        Toast.makeText(this, "Date: " + dateMessage,
                Toast.LENGTH_SHORT).show();
        dateTimeEditText.setText(dateMessage);

    }

}