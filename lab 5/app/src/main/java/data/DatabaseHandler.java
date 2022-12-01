package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Word;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context;
    private final ArrayList<Word> wordArrayList = new ArrayList<>();

    public DatabaseHandler(@Nullable Context context) {
//        super(context, Constants.DATABASE_NAME, factory, version);
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Constants.TABLE_NAME +
                " (" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.ENG_WORD + " TEXT, " +
                Constants.MON_WORD + " TEXT) ;";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.ENG_WORD, word.getEngWord());
        contentValues.put(Constants.MON_WORD, word.getMonWord());
        long result = db.insert(Constants.TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Амжилтгүй", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Амжилттай нэмэглээ", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public boolean updateData(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.ENG_WORD, word.getEngWord());
        contentValues.put(Constants.MON_WORD, word.getMonWord());
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.KEY_ID + " = ?", new String[]{String.valueOf(word.getItemID())});
        if (cursor.getCount() > 0) {
            long result = db.update(Constants.TABLE_NAME, contentValues, Constants.KEY_ID + "= ?", new
                    String[]{String.valueOf(word.getItemID())});
            if(result == -1) {
                db.close();
                return false;
            } else {
                db.close();
                return true;
            }
        } else {
            db.close();
            return false;

        }

    }

    public boolean deleteData(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.KEY_ID + " = ?", new String[]{String.valueOf(word.getItemID())});
        if (cursor.getCount() > 0) {
            long result = db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "= ?", new
                    String[]{String.valueOf(word.getItemID())});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;

        }
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + Constants.TABLE_NAME, null);
        return  cursor;
    }
    public ArrayList<Word> getWords() {
        wordArrayList.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + Constants.TABLE_NAME, null);
        if(cursor.getCount() == 0) {
            Log.i("TAG", "getWords: empty error ");
        }
        else {
            while (cursor.moveToNext()) {
                Word word = new Word();
                word.setItemID(cursor.getInt(0));
                word.setEngWord(cursor.getString(1));
                word.setMonWord(cursor.getString(2));
                wordArrayList.add(word);
            }
        }
        db.close();
        return wordArrayList;
    }
}
