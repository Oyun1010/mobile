package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.myapplication.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import data.DatabaseHandler;
import model.Word;


public class MainActivity extends AppCompatActivity {

    private TextView engWordView, monWordView;

    SharedPreferences sharedPreferences;
    String preferences = "com.example.myapplication";
    SharedPreferences.Editor editor;
    DatabaseHandler DB;
    ArrayList<Word> words;

    int indexCurrent = 0;
    int updateWordIndex = -1;
    int viewModelMain = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.myapplication.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        words = new ArrayList<>();
        engWordView = findViewById(R.id.eng_word);
        monWordView = findViewById(R.id.mon_word);

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        viewModelMain = sharedPreferences.getInt("viewMode", 0);

        editor = sharedPreferences.edit();

        DB = new DatabaseHandler(this);
        words = DB.getWords();
        if (words.size() > 0) {
            displayWord();

        } else {
            engWordView.setText("No word");
            monWordView.setText("No word");
        }

        engWordView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                update_btn(view);
                return false;
            }
        });
        monWordView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                update_btn(view);
                return false;
            }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void displayWord() {
        if (words.size() == 0) {
            return;
        }
        if (viewModelMain == 0) {
            engWordView.setText(words.get(indexCurrent).getEngWord());
            monWordView.setText(words.get(indexCurrent).getMonWord());
        } else if (viewModelMain == 1) {
            engWordView.setText("");
            monWordView.setText(words.get(indexCurrent).getMonWord());
            engWordView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    engWordView.setText(words.get(indexCurrent).getEngWord());
                    editor.putInt("viewMode", 1);
                }
            });
        } else if (viewModelMain == 2) {
            engWordView.setText(words.get(indexCurrent).getEngWord());
            monWordView.setText("");
            monWordView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monWordView.setText(words.get(indexCurrent).getMonWord());
                    editor.putInt("viewMode", 2);
                }
            });
        }
    }

    public void create_btn(View view) {
        Intent intent = new Intent(getApplicationContext(), CU_Acttivity.class);
        intent.putExtra("create", true);
        intent.putExtra("indexCurrent", indexCurrent);
        startActivity(intent);
    }

    public void update_btn(View view) {
        if (words.size() != 0) {
            Intent intent = new Intent(getApplicationContext(), CU_Acttivity.class);
            intent.putExtra("create", false);
            intent.putExtra("engWord", words.get(indexCurrent).getEngWord());
            intent.putExtra("monWord", words.get(indexCurrent).getMonWord());
            updateWordIndex = words.get(indexCurrent).getItemID();
            intent.putExtra("updateIndex", updateWordIndex);
            Log.d("update Index", String.valueOf(updateWordIndex));

            startActivity(intent);
        } else {
            view.setEnabled(false);
        }
    }

    public void delete_btn(View view) {
        if (words.size() == 0) {
            view.setEnabled(false);
        } else {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setTitle("Устгах");
            alertBuilder.setMessage(words.get(indexCurrent).getEngWord() + " үгийг устгах уу ?");
            alertBuilder.setPositiveButton("Тийм", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Boolean check = DB.deleteData(words.get(indexCurrent));
                    if (check) {
                        displayToast(words.get(indexCurrent).getEngWord() + "- үг амжилттай устлаа");
                    }
                    indexCurrent--;
                    indexCurrent--;
                    if (indexCurrent < 0) {
                        indexCurrent = 0;
                    }
                    words = DB.getWords();
                    displayWord();
                    displayToast("Амжилттай устгалаа");
                }
            });
            alertBuilder.setNegativeButton("Үгүй", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    displayToast("Цуцлалаа");

                }
            });
            alertBuilder.show();
        }
    }

    public void prev_btn(View view) {
        if (words.size() == 0) {
            view.setEnabled(false);
        } else {
            indexCurrent--;
            if (indexCurrent < 0) {
                indexCurrent = words.size() - 1;
            }
            displayWord();
        }

    }

    public void next_btn(View view) {
        if (words.size() == 0) {
            view.setEnabled(false);
        } else {
            indexCurrent++;
            if (words.size() <= indexCurrent) {
                indexCurrent = 0;
            }
            displayWord();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings_Activity.class);
            intent.putExtra("viewMode", viewModelMain);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            activityResultLauncher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri uri = result.getData().getData();
                        BufferedReader reader;
                        try {
                            InputStream is = getContentResolver().openInputStream(uri);
                            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                            String line = reader.readLine();
                            Log.d("LINE : ", line);
                            while ((line = reader.readLine()) != null) {
                                String[] ugs = line.split(",");
                                for (int i = 0; i < ugs.length; i++) {
                                    Log.d("Ugs ", ugs[i]);
                                }
                                Word word = new Word();
                                word.setEngWord(ugs[0]);
                                word.setMonWord(ugs[1]);
                                if(words.size() == 0) {
                                    words.add( 0, word);
                                }
                                else {
                                    words.add( words.size() - 1, word);

                                }


                                boolean isInput = DB.insertData(word);
                                if (isInput) {
                                    displayToast("Амжилтай нэмлээ.");
                                    displayWord();
                                } else {
                                    displayToast("Алдаа гарлаа дахин оролдоно уу.");
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}
