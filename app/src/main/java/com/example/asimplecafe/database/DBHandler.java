package com.example.asimplecafe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.asimplecafe.Utils;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TAG = "Database Handler";

    private static final String COLUMN_ID = "_id";

    private static final String PRODUCTS_TABLE_NAME = "products";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "product_price";
    private static final String PRODUCT_IMAGE = "product_image";

    // TABEL UNTUK PEMBELI NYA. DPT STRUK; yang sudah dibayar
    private static final String RECEIPT_TABLE_NAME = "receipts";
    private static final String RECEIPTS_TOTAL_PRICE = "receipts_total_price";
    private static final String RECEIPTS_PAID = "receipts_paid";
    private static final String RECEIPTS_KEMBALIAN = "receipts_kembalian";
    private static final String RECEIPTS_RESOLVED_TIME = "receipts_resolved_time";

    // TABEL UNTUK PEMBELI, PRODUK YG DIBELI
    private static final String RECEIPT_PRODUCT_TABLE_NAME = "receipts_product";
    private static final String RECEIPT_PRODUCT_RID = "receipts_id";
    private static final String RECEIPT_PRODUCT_PID = "product_id";
    private static final String RECEIPT_PRODUCT_QTY = "receipts_product_qty";

    // TABEL UNTUK KARYAWAN; menyimpan pesanan yg sudah ada
    private static final String STUB_TABLE_NAME = "stubs";
    private static final String STUBS_NAME = "stubs_name";
    private static final String STUBS_RESOLVED_TIME = "stubs_resolved_time";

    // TABEL UNTUK REFERENSI PRODUK DLM STUB; where stub id to product
    private static final String STUB_PRODUCT_TABLE_NAME = "stubs_product";
    private static final String STUB_PRODUCT_SID = "stub_id";
    private static final String STUBS_PRODUCT_PID = "product_id";
    private static final String STUBS_PRODUCT_QTY = "stubs_product_qty";

    private static final String DATABASE_NAME = "CafeLibrary.db";
    private static final int DATABASE_VERSION = 1;

    public DBHandler( @Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE "+ PRODUCTS_TABLE_NAME + " ("
                + COLUMN_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_NAME  + " TEXT, "
                + PRODUCT_PRICE + " INTEGER, "
                + PRODUCT_IMAGE + " TEXT);";
        db.execSQL(query);
        query = "CREATE TABLE "+ RECEIPT_TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RECEIPTS_TOTAL_PRICE   + " INTEGER, "
                + RECEIPTS_PAID          + " INTEGER, "
                + RECEIPTS_KEMBALIAN     + " INTEGER, "
                + RECEIPTS_RESOLVED_TIME + " DATETIME);";
        db.execSQL(query);
        query = "CREATE TABLE "+ RECEIPT_PRODUCT_TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RECEIPT_PRODUCT_RID + " INTEGER, "
                + RECEIPT_PRODUCT_PID + " INTEGER, "
                + RECEIPT_PRODUCT_QTY + " INTEGER);";
        db.execSQL(query);
        query = "CREATE TABLE "+ STUB_TABLE_NAME + " ("
                + COLUMN_ID           + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUBS_NAME          + " TEXT, "
                + STUBS_RESOLVED_TIME + " DATETIME);";
        db.execSQL(query);
        query = "CREATE TABLE "+ STUB_PRODUCT_TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUB_PRODUCT_SID  + " INTEGER, "
                + STUBS_PRODUCT_PID + " INTEGER, "
                + STUBS_PRODUCT_QTY + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECEIPT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECEIPT_PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUB_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUB_PRODUCT_TABLE_NAME);
        onCreate(db);
    }

    public int addReceipt(int total_price, int pembayaran, int kembalian) {
        SQLiteDatabase db = DBHandler.this.getWritableDatabase();
        ContentValues receiptValues = assignReceipt(total_price, pembayaran, kembalian);
        long result = db.insert(RECEIPT_TABLE_NAME, null, receiptValues);
        Utils.showCallback(TAG, result, "RECEIPT TABLE");
        return getReceiptId();
    }

    private ContentValues assignReceipt(int total_price, int pembayaran, int kembalian){
        ContentValues values = new ContentValues();
        values.put(RECEIPTS_TOTAL_PRICE, total_price);
        values.put(RECEIPTS_PAID, pembayaran);
        values.put(RECEIPTS_KEMBALIAN, kembalian);
        values.put(RECEIPTS_RESOLVED_TIME, Utils.getDateTime( ) );
        return values;
    }

    private int getReceiptId(){
        SQLiteDatabase db;
        Cursor cursor;

        db = DBHandler.this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + RECEIPT_TABLE_NAME
                , null );
        cursor.moveToLast();
        int result = cursor.getInt(0);
        cursor.close();

        return result;
    }
}
