package com.example.akamenov.a01grocery_store_items_through_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by AKamenov on 10/2/2016.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "grocery_store";

    // Table name
    private static final String  TABLE_FOOD_STUFF = "Foodstuff";

    // Table column
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL_STATEMENT = "CREATE TABLE " + this.TABLE_FOOD_STUFF + " ( " + this.KEY_ID + " INTEGER PRIMARY KEY, " + this.KEY_NAME + " TEXT )";

        db.execSQL(CREATE_TABLE_SQL_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLE_FOOD_STUFF);

        onCreate(db);
    }

    public void createTable() {
        SQLiteDatabase currentDb = this.getWritableDatabase();

        String CREATE_TABLE_SQL_STATEMENT = "CREATE TABLE " + this.TABLE_FOOD_STUFF + " ( " + this.KEY_ID + " INTEGER PRIMARY KEY, " + this.KEY_NAME + " TEXT )";

        currentDb.execSQL(CREATE_TABLE_SQL_STATEMENT);

        currentDb.close();
    }

    public void addFood(FoodStuff foodStuff) {
        SQLiteDatabase currentDb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(this.KEY_ID, foodStuff.get_id());
        values.put(this.KEY_NAME, foodStuff.get_name());

        currentDb.insert(this.TABLE_FOOD_STUFF, null, values);

        currentDb.close();
    }

    public ArrayList<FoodStuff> getFoodStuffLimit(int limitNumber) {
        SQLiteDatabase currentDb = this.getWritableDatabase();
        ArrayList<FoodStuff> foodStuffs = new ArrayList<>();

        String rawQuery = "SELECT " + this.KEY_ID + ", " + this.KEY_NAME + " FROM " + this.TABLE_FOOD_STUFF + " LIMIT " + limitNumber;

        Cursor cursorIterators = currentDb.rawQuery(rawQuery, null);

        if (cursorIterators != null) {
            while (cursorIterators.moveToNext()) {
                FoodStuff foodStuff = new FoodStuff(Integer.parseInt(cursorIterators.getString(0)), cursorIterators.getString(1));

                foodStuffs.add(foodStuff);
            }
        }

        currentDb.close();
        return foodStuffs;
    }
}
