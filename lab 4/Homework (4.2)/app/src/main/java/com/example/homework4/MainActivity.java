package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String checkValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.chocolate_syrup:
                if (checked)
                    checkValue += " " + (getString(R.string.checkboxes_syrup));
                break;
            case R.id.sprinkles:
                if (checked)
                    checkValue += " " + (getString(R.string.sprinkles));
                break;
            case R.id.crushed_nuts:
                if (checked)
                    checkValue += " " + (getString(R.string.crushed_nuts));
                break;
            case R.id.cherries:
                if (checked)
                    checkValue += " " + (getString(R.string.cherries));
                break;
            case R.id.orio:
                if (checked)
                    checkValue += " " + (getString(R.string.orio_cookie_crumbles));
                break;
            default:
                break;
        }

    }

    public void showToast(View view) {
        Log.d("-----------", checkValue);
        Toast.makeText(getApplicationContext(), "Toppings:" + checkValue, Toast.LENGTH_SHORT).show();
        checkValue = "";
    }
}