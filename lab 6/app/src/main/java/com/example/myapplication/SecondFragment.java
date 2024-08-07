package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class SecondFragment extends AsyncTask<String, Void, WeatherList> {
    public MainActivity mainActivity;
    private String url;
    int i;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected WeatherList doInBackground(String... strings) {
        InputStream inputStream = null;
        WeatherList weatherList = new WeatherList();
        ArrayList arrayList = new ArrayList();
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            inputStream = connection.getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject obj = new JSONObject(responseStrBuilder.toString());

            JSONArray larray = obj.getJSONArray("list");

            JSONObject o = larray.getJSONObject(i);
            JSONObject main = o.getJSONObject("main");
            JSONArray weather = o.getJSONArray("weather");
            JSONObject desc = weather.getJSONObject(0);
            weatherList.setDesc(desc.getString("description"));
            weatherList.setTmp(Double.parseDouble(main.getString("temp")));
            weatherList.setDateTime(o.getString("dt_txt"));
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherList;
    }


    protected void onPostExecute(WeatherList t) {
        mainActivity.callBackData(t);
    }


    public SecondFragment(MainActivity activity, String url, Integer i) {
        this.mainActivity = activity;
        this.url = url;
        this.i = i;
    }

}