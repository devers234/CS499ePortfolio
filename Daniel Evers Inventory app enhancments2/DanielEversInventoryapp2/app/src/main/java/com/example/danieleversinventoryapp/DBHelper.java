package com.example.danieleversinventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table for login system
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, " +
                "password TEXT)");


        db.execSQL("CREATE TABLE inventory (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL, " +
                "condition TEXT, " +
                "willing_to_trade INTEGER DEFAULT 0)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE inventory ADD COLUMN condition TEXT");
            db.execSQL("ALTER TABLE inventory ADD COLUMN willing_to_trade INTEGER DEFAULT 0");
        }

    }


    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean usernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public boolean addItem(String name, int quantity, String condition, boolean willingToTrade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        values.put("condition", condition);
        values.put("willing_to_trade", willingToTrade ? 1 : 0);
        long result = db.insert("inventory", null, values);
        return result != -1;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM inventory", null);
    }

    public Cursor getItemsSortedByName() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM inventory ORDER BY name COLLATE NOCASE ASC",
                null
        );
    }

    public Cursor getItemsSortedByQuantity(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM inventory ORDER BY quantity DESC",
                null
        );
    }

    public boolean updateItem(int id, String name, int quantity, String condition, boolean willingToTrade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        values.put("condition", condition);
        values.put("willing_to_trade", willingToTrade ? 1 : 0);
        int rows = db.update("inventory", values, "id = ?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    public boolean deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("inventory", "id = ?", new String[]{String.valueOf(id)});
        return rows > 0;
    }
}
