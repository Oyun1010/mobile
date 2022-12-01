package com.example.plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collection;

import data.DatabaseHandler;
import model.Plan;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Plan> planArrayList;
    private ArrayList<Plan> planArrayList1;
    private ArrayList<Plan> planArrayList2;

    private TodoListAdapter listAdapter;
    private RecyclerView recyclerView;

    private Button button1, button2, button3;

    SharedPreferences sharedPreferences;
    String preferences = "com.examlpe.plan";
    SharedPreferences.Editor editor;
    DatabaseHandler DB;
    int viewModelMain = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.containt_main);

        planArrayList = new ArrayList<Plan>();
        planArrayList1 = new ArrayList<Plan>();
        planArrayList2 = new ArrayList<Plan>();

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        viewModelMain = sharedPreferences.getInt("viewMode", 0);
        editor = sharedPreferences.edit();

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("Бүх төлөвлөгөөг харах");
                editor.putInt("viewMode", 0);
                editor.commit();
                displayItem();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("Хийж гүйцэтгэсэн төлөвлөгөөг харах");
                editor.putInt("viewMode", 1);
                editor.commit();
                displayItem();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("Хийгээгүй төлөвлөгөөг харах");
                editor.putInt("viewMode", 2);
                editor.commit();
                displayItem();
            }
        });

        DB = new DatabaseHandler(this);
        planArrayList = DB.getTodoList();
        if (planArrayList.size() > 0) {
            displayItem();
        } else {
            displayToast("no item");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void add_data(View view) {
        Intent intent = new Intent(MainActivity.this, CreateData.class);
        startActivity(intent);
        displayItem();
    }

    public void displayItem() {
        if (planArrayList.size() == 0) {
            return;
        } else if (viewModelMain == 0) {
            Log.d("1 : ", "kk");
            recyclerView = findViewById(R.id.recyclerview);
            listAdapter = new TodoListAdapter(this, planArrayList);
            recyclerView.setAdapter(listAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else if (viewModelMain == 1) {
            Log.d("2 : ", "kk");
            for (int i = 0; i < planArrayList.size(); i++) {
                if (planArrayList.get(i).getState() == 0) {
                    planArrayList1.add(planArrayList.get(i));

                }
            }
            recyclerView = findViewById(R.id.recyclerview);
            listAdapter = new TodoListAdapter(this, planArrayList1);
            recyclerView.setAdapter(listAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else if (viewModelMain == 2) {
                   Log.d("2 : ", "kk");
            for (int i = 0; i < planArrayList.size(); i++) {
                if (planArrayList.get(i).getState() == 1) {
                    planArrayList2.add(planArrayList.get(i));

                }
            }
            recyclerView = findViewById(R.id.recyclerview);
            listAdapter = new TodoListAdapter(this, planArrayList2);
            recyclerView.setAdapter(listAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}