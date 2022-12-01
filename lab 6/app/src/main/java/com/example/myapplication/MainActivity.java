package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    WeatherList weatherList;


    int i;


    private LinkedList<WeatherList> weatherLists;
    private RecyclerView recyclerView;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        weatherLists = new LinkedList<>();
        weatherList = new WeatherList();





    }
    public void btn1(View view) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < 8; i++) {
            weather1(i);

        }
        weatherLists = new LinkedList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new WeatherAdapter(this, weatherLists);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void btn2(View view) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < 40; i = i + 8) {
            weather2(i);

        }
        weatherLists = new LinkedList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new WeatherAdapter(this, weatherLists);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void weather1(int i) {
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?q=Ulaanbaatar&appid=bb4f29c82622269cdae24ead4e02e7a4");
        SecondFragment a = new SecondFragment(this, url, i);
        a.execute();
    }

    @RequiresApi(api =  Build.VERSION_CODES.CUPCAKE)
    public  void weather2(int i) {
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?q=Ulaanbaatar&appid=bb4f29c82622269cdae24ead4e02e7a4");
        FirstFragment firstFragment = new FirstFragment(this, url, i);
        firstFragment.execute();
    }




    public void callBackData(WeatherList weatherList) {
        weatherLists.add(weatherList);
        adapter.notifyDataSetChanged();
    }
}