package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ShoppingCart.db";
    private static final String TABLE_NAME = "orders";
    public static  final String COL_1 = "ID";
    public static  final String COL_2 = "NAME";
    public static  final String COL_3 = "EMAIL";
    public static  final String COL_4 = "PHONE";
    public static  final String COL_5 = "ADDRESS";
    public static final String COL_6 = "ITEMNAME";
    public static final String COL_7 = "QUANTITY";
    public static final String COL_8 = "PAYMENTMODE";
    public static final String COL_9 = "AMOUNT";
    public static final String COL_10 = "CARDNUMBER";
    public static final String COL_11 = "CARDEXPIRY";
    public static final String COL_12 = "CARDCVV";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }
    //table definition
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PHONE TEXT, ADDRESS TEXT, ITEMNAME TEXT, QUANTITY TEXT, PAYMENTMODE TEXT, AMOUNT TEXT, CARDNUMBER TEXT, CARDEXPIRY TEXT, CARDCVV TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //insertion of data being handled
    public boolean insertData(String name, String email, String phone, String address, String itemname, String quantity, String paymentmode, String amount, String cardnumber, String cardexpiry, String cardcvv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, phone);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, itemname);
        contentValues.put(COL_7, quantity);
        contentValues.put(COL_8, paymentmode);
        contentValues.put(COL_9, amount);
        contentValues.put(COL_10, cardnumber);
        contentValues.put(COL_11, cardexpiry);
        contentValues.put(COL_12, cardcvv);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

}
