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

import model.Plan;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context;
    private  final ArrayList<Plan> planArrayList = new ArrayList<>();
    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DB_TASK;
        CREATE_DB_TASK = "CREATE TABLE " + Constants.TABLE_NAME +
                " (" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.STATE + " INTEGER, " +
                Constants.TO_DO + " TEXT, " +
                Constants.DATE_TIME + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_DB_TASK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(Plan plan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TO_DO, plan.getToDo());
        contentValues.put(Constants.DATE_TIME, plan.getDateTime());
        long result = db.insert(Constants.TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Амжилтгүй", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Амжилттай нэмэглээ", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public boolean updateData(Plan plan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TO_DO, plan.getToDo());
        contentValues.put(Constants.DATE_TIME, plan.getDateTime());
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.KEY_ID + " = ?", new String[]{String.valueOf(plan.getItemID())});
        if (cursor.getCount() > 0) {
            long result = db.update(Constants.TABLE_NAME, contentValues, Constants.KEY_ID + "= ?", new
                    String[]{String.valueOf(plan.getItemID())});
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

    public boolean deleteData(Plan plan) {
        Log.d("Delete date ", "gggg");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.KEY_ID + " = ?", new String[]{String.valueOf(plan.getItemID())});
        if (cursor.getCount() > 0) {
            long result = db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "= ?", new
                    String[]{String.valueOf(plan.getItemID())});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;

        }
    }
    public ArrayList<Plan> getTodoList() {
        planArrayList.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + Constants.TABLE_NAME, null);
        if(cursor.getCount() == 0) {
            Log.i("TAG", "getWords: empty error ");
        }
        else {
            while (cursor.moveToNext()) {
                Plan plan = new Plan();
                plan.setItemID(cursor.getInt(0));
                plan.setState(cursor.getInt(1));
                plan.setToDo(cursor.getString(2));
                plan.setDateTime(cursor.getString(3));
                planArrayList.add(plan);

            }
        }
        db.close();
        return planArrayList;
    }
}
